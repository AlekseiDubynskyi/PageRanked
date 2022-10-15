package com.solvd.pageranked.services.mainLogic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.solvd.pageranked.dao.jdbc.mysql.Impl.LinksDAO;
import com.solvd.pageranked.dao.jdbc.mysql.Impl.NodesDAO;
import com.solvd.pageranked.dao.jdbc.mysql.Impl.RelationsDAO;
import com.solvd.pageranked.models.Relations;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class JacksonPojoToJson {
    public static void JacksonStart() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        NodesDAO nodesDAO = new NodesDAO();
        LinksDAO linksDAO = new LinksDAO();
        RelationsDAO relationsDAO = new RelationsDAO();

        FileOutputStream fileOutputStreamNodes = new FileOutputStream("src/main/resources/result/nodes.json");
        mapper.writeValue(fileOutputStreamNodes, nodesDAO);
        fileOutputStreamNodes.close();

        FileOutputStream fileOutputStreamLinks = new FileOutputStream("src/main/resources/result/links.json");
        mapper.writeValue(fileOutputStreamLinks, linksDAO);
        fileOutputStreamLinks.close();

        FileOutputStream fileOutputStreamRelations = new FileOutputStream("src/main/resources/result/relations.json");
        mapper.writeValue(fileOutputStreamRelations, relationsDAO);
        fileOutputStreamRelations.close();
    }
}
