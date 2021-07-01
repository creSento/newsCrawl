package com.sm.news;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sm.news.domain.Article;

public class NewsCsvWriter {
    private BufferedWriter bw;
    private static final Path path = Paths.get(System.getProperty("user.dir"), "csvFiles");
    private String fileName;
    
    private static NewsCsvWriter instance;
    private NewsCsvWriter() {
        
    }
    public static NewsCsvWriter getInstance() {
        if (instance == null) {
            instance = new NewsCsvWriter();
        }
        return instance;
    }
    
    public void appendNewsCityOfDay(List<Article> articleListOfDay, LocalDate articleDate) {
        StringBuffer sbuf = new StringBuffer();
        fileName = String.format("%s\\database_%s.csv", path, articleDate.toString());
        Set<Article> articlesFilter = new HashSet<Article>(articleListOfDay);
        for(Article art : articlesFilter) {
            sbuf.append(art.getMinorcity_code());
            sbuf.append("\t" + art.getArticle_title()
            + "\t" + art.getArticle_press()
            + "\t" + art.getArticle_url()
            + "\t" + articleDate.toString()
            + "\n");
        }
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, true), "utf-8"));
            bw.append(sbuf.toString());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void logWriter(String line) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        fileName = String.format("%s\\log_%s.csv", path, sdf.format(new Date()));
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, true), "utf-8"));
            bw.append(line);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
