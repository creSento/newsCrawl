package com.sm.news.domain;

public class City {
    private int majorcity_code;
    private String majorcity_name;
    private int minorcity_code;
    private String minorcity_name;
    private int totalArticle;

    public City(String minorcity_name, int totalArticle) {
        this.minorcity_name = minorcity_name;
        this.totalArticle = totalArticle;
    }

    public City(int majorcity_code, String majorcity_name, int totalArticle) {
        this.majorcity_code = majorcity_code;
        this.majorcity_name = majorcity_name;
        this.totalArticle = totalArticle;
    }

    public City(int majorcity_code, String majorcity_name, int minorcity_code, String minorcity_name,
            int totalArticle) {
        this.majorcity_code = majorcity_code;
        this.majorcity_name = majorcity_name;
        this.minorcity_code = minorcity_code;
        this.minorcity_name = minorcity_name;
        this.totalArticle = totalArticle;
    }

    public City(int majorcity_code, String majorcity_name, int minorcity_code, String minorcity_name) {
        this.majorcity_code = majorcity_code;
        this.majorcity_name = majorcity_name;
        this.minorcity_code = minorcity_code;
        this.minorcity_name = minorcity_name;
    }

    public City(int minorcity_code) {
        this.minorcity_code = minorcity_code;
    }

    public int getMajorcity_code() {
        return majorcity_code;
    }

    public void setMajorcity_code(int majorcity_code) {
        this.majorcity_code = majorcity_code;
    }

    public String getMajorcity_name() {
        return majorcity_name;
    }

    public void setMajorcity_name(String majorcity_name) {
        this.majorcity_name = majorcity_name;
    }

    public int getMinorcity_code() {
        return minorcity_code;
    }

    public void setMinorcity_code(int minorcity_code) {
        this.minorcity_code = minorcity_code;
    }

    public String getMinorcity_name() {
        return minorcity_name;
    }

    public void setMinorcity_name(String minorcity_name) {
        this.minorcity_name = minorcity_name;
    }

    public int getTotalArticle() {
        return totalArticle;
    }

    public void setTotalArticle(int totalArticle) {
        this.totalArticle = totalArticle;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        City other = (City) obj;
        if (minorcity_code != other.minorcity_code)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "City [majorcity_code=" + majorcity_code + ", majorcity_name=" + majorcity_name + ", minorcity_code="
                + minorcity_code + ", minorcity_name=" + minorcity_name + "]";
    }

}
