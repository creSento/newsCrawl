package com.sm.news;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.sm.news.domain.City;

public class InsertCityTable {

    public static void main(String[] args) {
        Connection conn = DatabaseConnection.getConnection();
        String insertQuery = "insert into captial_cities values (?, ?, ?, ?);";
        int[] capitalMajorCode = { 11, 23, 31 };
        Set<City> capitals = new HashSet<City>();
        BufferedReader br = null;
        String line = null;
        String[] lineCols = null;
        
        // get source from csv file
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream("csvFiles/section.csv"), "utf-8"));
            int majorCode = 0;
            String majorName = "";
            int minorCode = 0;
            String minorName = "";
            while ((line = br.readLine()) != null) {
                lineCols = line.split(",");
                if (lineCols.length < 5) continue;
                if (lineCols[3].equals("") || lineCols[3] == null) continue;
                for (int j = 0; j < capitalMajorCode.length; j++) {
                    if (lineCols[1].matches(".*[0-9].*") 
                            && lineCols[1].equals(String.valueOf(capitalMajorCode[j]))) {
                        majorCode = Integer.parseInt(lineCols[1]);
                        majorName = lineCols[2];
                        minorCode = Integer.parseInt(lineCols[3]);
                        minorName = lineCols[4];
                        capitals.add(new City(majorCode, majorName, minorCode, minorName));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // insert into database
        try {
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            for (City city : capitals) {
                pstmt.setInt(1, city.getMajorcity_code());
                pstmt.setString(2, city.getMajorcity_name());
                pstmt.setInt(3, city.getMinorcity_code());
                pstmt.setString(4, city.getMinorcity_name());
                pstmt.addBatch();
                pstmt.clearParameters();
            }
            pstmt.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

}
