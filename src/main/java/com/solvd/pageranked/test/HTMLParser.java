package com.solvd.pageranked.test;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;


public class HTMLParser {
    private static final Logger LOGGER = LogManager.getLogger(HTMLParser.class);
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.parse(new File("src/main/resources/html/Third.html"), "UTF-8");
        String htmlFile = null;
        Elements link = doc.select("a");
        htmlFile = link.attr("href");


        System.out.println(htmlFile);
    }
}
