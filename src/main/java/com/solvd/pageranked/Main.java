package com.solvd.pageranked;

import com.solvd.pageranked.dao.INodes;
import com.solvd.pageranked.dao.jdbc.mysql.Impl.NodesDAO;
import com.solvd.pageranked.models.Matrix;
import com.solvd.pageranked.services.mainLogic.HTMLParser;
import com.solvd.pageranked.services.mainLogic.JacksonPojoToJson;
import com.solvd.pageranked.services.mainLogic.MatrixCreator;
import com.solvd.pageranked.services.mainLogic.PageRankService;
import com.solvd.pageranked.services.mainLogic.PageRank;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        HTMLParser.HTMLParsing();
        Matrix matrix = MatrixCreator.getMatrix();
        //matrix.print();
        INodes iNodes = new NodesDAO();
        PageRank pageRank = new PageRank(matrix);
        matrix.print();
        PageRankService pageRankService = new PageRankService(pageRank, iNodes);
        pageRankService.updatePageRank();
        pageRankService.showResults();
        
        JacksonPojoToJson.JacksonStart();
    }
}
