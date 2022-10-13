package com.solvd.pageranked.services.test;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.Objects;


public class HTMLParser {
    private static final Logger LOGGER = LogManager.getLogger(HTMLParser.class);

    public static void main(String[] args) throws IOException {
        File folder = new File("src/main/resources/html/");
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            System.out.println("\n" + "For " + file.getName() + ":");
            Document doc = Jsoup.parse(new File(folder + "/" + file.getName()), "UTF-8");
            Elements links = doc.select("a");
            for (Element link : links) {
                String linkHref = link.attr("href");
                String linkText = link.text();
                System.out.println(linkHref + " - " + linkText);
            }
        }
    }
}
