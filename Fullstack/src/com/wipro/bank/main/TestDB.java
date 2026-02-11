package com.wipro.bank.main;

import java.sql.Connection;

import com.wipro.bank.util.DBUtil;

//TestDB.java
public class TestDB {
 public static void main(String[] args) {
     try (Connection con = DBUtil.getDBConnection()) {
         System.out.println("✅ Connected to XEPDB1!");
     } catch (Exception e) {
         System.out.println("❌ " + e.getMessage());
     }
 }
}
