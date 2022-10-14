package com.solvd.pageranked.services.test;

import com.solvd.pageranked.dao.jdbc.mysql.Impl.LinksDAO;
import com.solvd.pageranked.dao.jdbc.mysql.Impl.NodesDAO;
import com.solvd.pageranked.dao.jdbc.mysql.Impl.RelationsDAO;
import com.solvd.pageranked.models.Links;
import com.solvd.pageranked.models.Nodes;
import com.solvd.pageranked.models.Relations;
import com.solvd.pageranked.services.mainLogic.Initialization;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class HTMLParser {
    private static final Logger LOGGER = LogManager.getLogger(HTMLParser.class);

    public static void main(String[] args) throws IOException {
        Initialization.deleteAllFromDB();
        addNodesAndLinksToDB();

        NodesDAO nodesDAO = new NodesDAO();
        LinksDAO linksDAO = new LinksDAO();
        RelationsDAO relationsDAO = new RelationsDAO();
        List<String> linksList = linksDAO.getAllLinks().stream().map(Links::getLinkHref).collect(Collectors.toList());
        int numberRelations = 1;

        File folder = new File("src/main/resources/html/");
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            int idNode = nodesDAO.getByName(file.getName()).getId();
            Document document = Jsoup.parse(new File(folder + "/" + file.getName()), "UTF-8");
            Elements links = document.select("a");
            int quantityIn = 0;
            int quantityOut = 0;
            for (Element link : links) {
                String linkHref = link.attr("href");
                String linkText = link.text();
                System.out.println(linkHref + " - " + linkText);
                if (linksList.contains(linkHref)){
                    int idLink = linksDAO.getByLinkHref(linkHref).getId();
                    relationsDAO.addRelations(new Relations(numberRelations, idNode, idLink));
                    numberRelations++;
                }
            }

        }
    }

    public static void addNodesAndLinksToDB() {
        NodesDAO nodesDAO = new NodesDAO();
        LinksDAO linksDAO = new LinksDAO();
        int numberNodes = 1;
        int numberLinks = 1;

        File folder = new File("src/main/resources/html/");
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            LOGGER.info(file.getName() + " was added to database.");
            nodesDAO.addNode(new Nodes(numberNodes, file.getName()));
            linksDAO.addLink(new Links(numberLinks, file.getName(), file.getName()));
            numberNodes++;
            numberLinks++;
        }
    }
}
