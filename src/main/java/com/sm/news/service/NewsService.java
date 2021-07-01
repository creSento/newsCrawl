package com.sm.news.service;

import java.time.LocalDate;
import java.util.List;

import com.sm.news.domain.Article;
import com.sm.news.domain.City;
import com.sm.news.domain.Pagination;

public interface NewsService {
    List<City> getCityList();

    void insertDatabase(List<Article> articleListOfDay, LocalDate articleDate);

    List<Article> articleList(int majorcity_code, Pagination pageBean);

    Pagination getPageBean(int majorcity_code, String page);

    City totalMajor(int majorcity_code);
    
    List<City> totalMinor(int majorcity_code);
    
    City selectCity(int majorcity_code);
}
