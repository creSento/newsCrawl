package com.sm.news;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.sm.news.domain.Article;
import com.sm.news.domain.City;
import com.sm.news.service.NewsService;
import com.sm.news.service.NewsServiceImpl;

public class NewsCrawlMain {

    public static void main(String[] args) {
        NewsCrawler crawl = NewsCrawler.getInstance();
        NewsCsvWriter writer = NewsCsvWriter.getInstance();
        NewsService service = NewsServiceImpl.getInstance();
        LocalDate articleDate = LocalDate.now().minusDays(1L);
        long startCityTime = 0;
        long endCityTime = 0;
        writer.logWriter(LocalDateTime.now().toString() + "\n");
        writer.logWriter(String.format("\narticleDate : %s\n", articleDate.toString()));
        List<City> cities = service.getCityList();
        try {
            for (int i = 0; i < cities.size(); i++) {
                City city = cities.get(i);
                startCityTime = System.currentTimeMillis();
                crawl.getArticlesByCity(city.getMinorcity_code(), city.getMinorcity_name(), articleDate);
                writer.appendNewsCityOfDay(NewsCrawler.getArticleListOfDay(), articleDate);
                writer.logWriter(
                        String.format("%d(%s) csv completed\n", city.getMinorcity_code(), city.getMinorcity_name()));
                service.insertDatabase(NewsCrawler.getArticleListOfDay(), articleDate);
                endCityTime = System.currentTimeMillis();
                writer.logWriter(String.format("%d(%s) db completed\nworking : %d m\n", city.getMinorcity_code(),
                        city.getMinorcity_name(), (int) (endCityTime - startCityTime) / 60 / 60));
                for (Article a : NewsCrawler.getArticleListOfDay()) {
                    System.out.println(a.toString());
                }
                NewsCrawler.getArticleListOfDay().clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
