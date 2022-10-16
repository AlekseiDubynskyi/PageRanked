package com.solvd.pageranked.services.mainLogic;

import com.solvd.pageranked.dao.INodes;
import com.solvd.pageranked.models.Nodes;

import java.util.List;

public class PageRankService {
    private PageRank pageRank;
    private double[] pageRankResults;
    private INodes iNodes;

    public PageRankService(PageRank pageRank, INodes iNodes) {
        this.pageRank = pageRank;
        this.iNodes = iNodes;

    }

    public void updatePageRank() {
        this.pageRankResults = pageRank.calculate();
        for (int i = 0; i < pageRankResults.length; i++) {
            iNodes.updateNodePageRank(pageRankResults[i], i + 1);

        }
    }

    public void showResults() {
        List<Nodes> allNodes = iNodes.getAllNodes();
        System.out.print("\n Final Page Rank : \n");
        for (Nodes node : allNodes) {
            System.out.printf(" Page Rank of " + node.getName() + " is :\t" + node.getPageRank() + "\n");
        }
    }

}
