package com.solvd.pageranked.services.test;

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
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        NodesDAO nodesDAO = new NodesDAO();
        LinksDAO linksDAO = new LinksDAO();
        RelationsDAO relationsDAO = new RelationsDAO();

        FileOutputStream fileOutputStream = new FileOutputStream("src/main/java/com/solvd/pageranked/services/test/result/nodes.json");
        mapper.writeValue(fileOutputStream, nodesDAO);
        fileOutputStream.close();

        FileOutputStream fileOutputStream1 = new FileOutputStream("src/main/java/com/solvd/pageranked/services/test/result/links.json");
        mapper.writeValue(fileOutputStream1, linksDAO);
        fileOutputStream1.close();

        FileOutputStream fileOutputStream2 = new FileOutputStream("src/main/java/com/solvd/pageranked/services/test/result/relations.json");
        mapper.writeValue(fileOutputStream2, relationsDAO);
        fileOutputStream2.close();
    }
}
