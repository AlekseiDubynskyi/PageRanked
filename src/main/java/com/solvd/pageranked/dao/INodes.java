package com.solvd.pageranked.dao;

import com.solvd.pageranked.models.Nodes;
import com.solvd.pageranked.models.Relations;

import java.util.List;

public interface INodes {
    Nodes getByName(String name);

    Nodes getQuantityByNodes(int nodesId);

    Nodes getQuantityByLinks(int linksId);

    List<Nodes> getAllNodes();

    void deleteAllNodes();

    void addNode(Nodes nodes);

    void updateNode(Nodes nodes);

    void deleteNode(int id);
}
