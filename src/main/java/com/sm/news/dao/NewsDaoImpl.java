package com.sm.news.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sm.news.DatabaseConnection;
import com.sm.news.domain.Article;
import com.sm.news.domain.City;
import com.sm.news.domain.Pagination;

public class NewsDaoImpl implements NewsDao {
    private static Connection conn = DatabaseConnection.getConnection();

    private NewsDaoImpl() {
    }

    private static class LazyHolder {
        public static final NewsDaoImpl INSTANCE = new NewsDaoImpl();
    }

    public static NewsDaoImpl getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override
    public List<City> getCityList() {
        List<City> cityList = new ArrayList<City>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet cities = stmt.executeQuery("select * from captial_cities order by minorcity_code;");
            while (cities.next()) {
                cityList.add(new City(cities.getInt(1), cities.getString(2), cities.getInt(3), cities.getString(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cityList;
    }

    @Override
    public void insertDatabase(List<Article> articleListOfDay, LocalDate date) {
        Set<Article> articlesFilter = new HashSet<Article>(articleListOfDay);
        Date inputDate = Date.valueOf(date);
        String sql = "insert into articles (minorcity_code, article_press, article_title, article_url, article_input_date)"
                + "values (?, ?, ?, ?, ?);";
        try {
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            for (Article art : articlesFilter) {
                pstmt.setInt(1, art.getMinorcity_code());
                pstmt.setString(2, art.getArticle_press());
                pstmt.setString(3, art.getArticle_title());
                pstmt.setString(4, art.getArticle_url());
                pstmt.setDate(5, inputDate);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Article> articleList(int majorcity_code, Pagination pageBean) {
        List<Article> articleList = new ArrayList<>();
        String sql = "select distinct a.* "
                + "from (select article_id from articles where minorcity_code like concat(?, '%')"
                + "order by article_input_date desc limit ?, ?) b "
                + "join articles a on a.article_id = b.article_id;";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, majorcity_code);
            pstmt.setInt(2, pageBean.getOffset());
            pstmt.setInt(3, pageBean.getRowPerPage());
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                String title = "";
                if (rset.getString("article_title").length() > 50) {
                    title = rset.getString("article_title").substring(0, 48);
                    title += "...";
                } else {
                    title = rset.getString("article_title");
                }
                articleList.add(new Article(rset.getInt("article_id"), rset.getInt("minorcity_code"),
                        rset.getString("article_press"), title, rset.getString("article_url"),
                        rset.getDate("article_input_date")));
            }
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articleList;
    }
    
    @Override
    public Pagination getPageBean(int majorcity_code, String page) {
        int curPage = 0;
        if (page == null || page.equals("")) {
            curPage = 1;
        } else {
            curPage = Integer.parseInt(page);
        }
        return new Pagination(curPage, totalMajor(majorcity_code).getTotalArticle());
    }
    
    @Override
    public City totalMajor(int majorcity_code) {
        City city = null;
        String sql = "select majorcity_name, "
                + "(select count(*) from articles where minorcity_code like concat(?, '%')) "
                + "from captial_cities where majorcity_code = ? group by majorcity_code;";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, majorcity_code);
            pstmt.setInt(2, majorcity_code);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                city = new City(majorcity_code, rset.getString("majorcity_name"), rset.getInt(2));
            }
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

    @Override
    public List<City> totalMinor(int majorcity_code) {
        List<City> minorList = new ArrayList<>();
        String sql = "select a.minorcity_code, c.minorcity_name, count(*) as total "
                + "from captial_cities c, articles a "
                + "where c.minorcity_code = a.minorcity_code "
                + "group by a.minorcity_code "
                + "having a.minorcity_code like concat(?, '%') "
                + "order by 3";
        int totalMajor = totalMajor(majorcity_code).getTotalArticle();
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, majorcity_code);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                if ((rset.getInt(3) / (double)totalMajor) > 0.05) {
                    minorList.add(new City(rset.getString("minorcity_name"), rset.getInt(3)));
                }
            }
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return minorList;
    }
    
    @Override
    public City selectCity(int majorcity_code) {
        City selectCity = null;
        String sql = "select c.*, (select count(*) from articles where minorcity_code like concat(?, '%')) as total from captial_cities c where majorcity_code = ? group by c.majorcity_code;";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, majorcity_code);
            pstmt.setInt(2, majorcity_code);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                selectCity = new City(rset.getInt("majorcity_code"), rset.getString("majorcity_name"),
                        rset.getInt("total"));
            }
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return selectCity;
    }

}
