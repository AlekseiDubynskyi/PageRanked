package com.solvd.pageranked.services.test;

import com.solvd.pageranked.dao.jdbc.mysql.Impl.LinksDAO;
import com.solvd.pageranked.dao.jdbc.mysql.Impl.NodesDAO;
import com.solvd.pageranked.models.Links;
import com.solvd.pageranked.models.Nodes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.Objects;


public class HTMLParser {
    private static final Logger LOGGER = LogManager.getLogger(HTMLParser.class);

    public static void main(String[] args) throws IOException {
       addNodesToDB();


//        NodesDAO nodesDAO = new NodesDAO();



//        nodesDAO.deleteNode(1);
//        nodesDAO.deleteNode(2);
//        nodesDAO.deleteNode(3);
//        nodesDAO.deleteNode(4);
//        nodesDAO.deleteNode(5);

//        LinksDAO linksDAO = new LinksDAO();
//
//        int numberLinks = 1;
//
//        File folder = new File("src/main/resources/html/");
//        for (File file : Objects.requireNonNull(folder.listFiles())) {
//            System.out.println("\n" + "For " + file.getName() + ":");
//
//            Document doc = Jsoup.parse(new File(folder + "/" + file.getName()), "UTF-8");
//            Elements links = doc.select("a");
//            for (Element link : links) {
//                int quantityIn = 0;
//                int quantityOut = 0;
//                String linkHref = link.attr("href");
//                String linkText = link.text();
//                System.out.println(linkHref + " - " + linkText);
//                if (linkHref =
//            }
//
//        }
//    }
    }

    public static void addNodesToDB() {
        NodesDAO nodesDAO = new NodesDAO();
        int numberNodes = 1;
        int qIn = 1;
        int qOut = 1;
        double page = 0.0;

        File folder = new File("src/main/resources/html/");
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            System.out.println("\n" + "For " + file.getName() + ":");

            nodesDAO.addNode(new Nodes(numberNodes, qIn, qOut, file.getName(), page));
            numberNodes++;
            qIn++;
            qOut++;
            page++;

        }
    }

}
