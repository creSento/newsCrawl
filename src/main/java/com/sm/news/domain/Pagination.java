package com.sm.news.domain;

import java.io.Serializable;

public class Pagination implements Serializable{
    
    private static final long serialVersionUID = 1L;
    int offset;
    int currentPage;
    int rowPerPage = 20;
    int total;
    int pagePerBlock = 10;
    int totalPage;
    int startPage;
    int endPage;
    
    public Pagination(int currentPage, int total) {
        this.currentPage = currentPage;
        this.total = total;
        totalPage = (int) (Math.ceil((double)total / rowPerPage));
        startPage = currentPage - (currentPage - 1) % pagePerBlock;
        endPage = startPage + (pagePerBlock - 1);
        if (endPage > totalPage) {
            endPage = totalPage;
        }
        offset = (currentPage - 1) * rowPerPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRowPerPage() {
        return rowPerPage;
    }

    public void setRowPerPage(int rowPerPage) {
        this.rowPerPage = rowPerPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPagePerBlock() {
        return pagePerBlock;
    }

    public void setPagePerBlock(int pagePerBlock) {
        this.pagePerBlock = pagePerBlock;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }
    @Override
    public String toString() {
        return "Pagination [offset=" + offset + ", currentPage=" + currentPage + ", rowPerPage=" + rowPerPage
                + ", total=" + total + ", pagePerBlock=" + pagePerBlock + ", totalPage=" + totalPage + ", startPage="
                + startPage + ", endPage=" + endPage + "]";
    }

}
