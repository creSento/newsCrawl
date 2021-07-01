package com.sm.news;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.sm.news.domain.Article;

public class NewsCrawler {

    private static List<Article> articleListOfDay = new ArrayList<Article>();

    public static List<Article> getArticleListOfDay() {
        return articleListOfDay;
    }

    private static NewsCrawler instance;

    public static NewsCrawler getInstance() {
        if (instance == null) {
            instance = new NewsCrawler();
        }
        return instance;
    }

    private NewsCrawler() {
    }

    public void getArticlesByCity(int minorCityCode, String minorCityName, LocalDate date) {
        String fromTo = date.toString().replace("-", "");
        boolean isLastPage = false;
        int page = 1;
        while (!isLastPage) {
            try {
                String url = "https://search.naver.com/search.naver?where=news&sm=tab_jum&query=%22" + minorCityName
                        + "%22&nso=so%3Ar%2Cp%3Afrom" + fromTo + "to" + fromTo + "&start=" + page;
                Document doc = Jsoup.connect(url).header("Content-Type", "application/json;charset=UTF-8")
                        .userAgent(Const.USER_AGENT).timeout(5000).get();
                Elements nextButton = doc.select("a.btn_next");
                // next page
                if (isLastPage(nextButton)) {
                    isLastPage = true;
                    System.out.printf("%d : %dpage\n", minorCityCode, (int) (page / 10.0 + 1));
                    Thread.sleep(5000);
                    break;
                } else {
                    // get html document
                    List<Article> articlesInPage = articlesByPage(minorCityCode, doc);
                    articleListOfDay.addAll(articlesInPage);
                    page += 10;
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private List<Article> articlesByPage(int minorCityCode, Document doc) {
        List<Article> articlesByPage = new ArrayList<Article>();
        String pressName = null;
        String title = null;
        String articleUrl = null;
        Elements ariticles = doc.select("div.news_area");
        Elements press = ariticles.select(".info.press");
        Elements articleTitles = doc.select("a.news_tit");

        for (int i = 0; i < ariticles.size(); i++) {
            if (articleTitles.get(i).attr("title").length() > 100) {
                title = articleTitles.get(i).attr("title").substring(0, 100);
            } else {
                title = articleTitles.get(i).attr("title");
            }
            pressName = press.get(i).text();
            articleUrl = articleTitles.get(i).attr("href");
            Article art = new Article(minorCityCode, pressName, title, articleUrl);
            articlesByPage.add(art);
        }
        return articlesByPage;
    }

    private boolean isLastPage(Elements nextButton) {
        boolean hasNext = false;
        if (nextButton.attr("aria-disabled").equals("true")) {
            hasNext = true;
        }
        return hasNext;
    }

}
