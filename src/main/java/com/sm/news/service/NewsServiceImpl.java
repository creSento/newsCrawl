package com.sm.news.service;

import java.time.LocalDate;
import java.util.List;

import com.sm.news.dao.NewsDao;
import com.sm.news.dao.NewsDaoImpl;
import com.sm.news.domain.Article;
import com.sm.news.domain.City;
import com.sm.news.domain.Pagination;

public class NewsServiceImpl implements NewsService {
    NewsDao dao = NewsDaoImpl.getInstance();

    private NewsServiceImpl() {
    }

    private static class LazyHolder {
        public static final NewsServiceImpl INSTANCE = new NewsServiceImpl();
    }

    public static NewsServiceImpl getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override
    public List<City> getCityList() {
        return dao.getCityList();
    }

    @Override
    public void insertDatabase(List<Article> articleListOfDay, LocalDate articleDate) {
        dao.insertDatabase(articleListOfDay, articleDate);
    }

    @Override
    public List<Article> articleList(int majorcity_code, Pagination pageBean) {
        return dao.articleList(majorcity_code, pageBean);
    }

    @Override
    public Pagination getPageBean(int majorcity_code, String page) {
        return dao.getPageBean(majorcity_code, page);
    }
    
    @Override
    public City totalMajor(int majorcity_code) {
        return dao.totalMajor(majorcity_code);
    }
    
    @Override
    public List<City> totalMinor(int majorcity_code) {
        return dao.totalMinor(majorcity_code);
    }

    @Override
    public City selectCity(int majorcity_code) {
        return dao.selectCity(majorcity_code);
    }

}
