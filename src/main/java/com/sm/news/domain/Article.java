package com.sm.news.domain;

import java.util.Date;

public class Article {
    private int article_id;
    private int minorcity_code;
    private String article_press;
    private String article_title;
    private String article_url;
    private Date article_input_date;

    public Article(int minorcity_code, String article_press, String article_title, String article_url) {
        this.minorcity_code = minorcity_code;
        this.article_title = article_title;
        this.article_press = article_press;
        this.article_url = article_url;
    }

    public Article(int article_id, int minorcity_code, String article_press, String article_title, String article_url,
            Date article_input_date) {
        this.article_id = article_id;
        this.minorcity_code = minorcity_code;
        this.article_title = article_title;
        this.article_press = article_press;
        this.article_url = article_url;
        this.article_input_date = article_input_date;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public int getMinorcity_code() {
        return minorcity_code;
    }

    public void setMinorcity_code(int minorcity_code) {
        this.minorcity_code = minorcity_code;
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_press(String article_press) {
        this.article_press = article_press;
    }

    public String getArticle_url() {
        return article_url;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getArticle_press() {
        return article_press;
    }

    public void setArticle_url(String article_url) {
        this.article_url = article_url;
    }

    public Date getArticle_input_date() {
        return article_input_date;
    }

    public void setArticle_input_date(Date article_input_date) {
        this.article_input_date = article_input_date;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((article_url == null) ? 0 : article_url.hashCode());
        result = prime * result + minorcity_code;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Article other = (Article) obj;
        if (article_url == null) {
            if (other.article_url != null)
                return false;
        } else if (!article_url.equals(other.article_url))
            return false;
        if (minorcity_code != other.minorcity_code)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Article [article_id=" + article_id + ", minorcity_code=" + minorcity_code + ", article_title="
                + article_title + ", article_press=" + article_press + ", article_url=" + article_url
                + ", article_input_date=" + article_input_date + "]";
    }

}
