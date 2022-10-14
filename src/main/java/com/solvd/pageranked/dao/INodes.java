package com.solvd.pageranked.dao;

import com.solvd.pageranked.models.Nodes;

import java.util.List;

public interface INodes {
    Nodes getByName(String name);

    List<Nodes> getAllNodes();
    void deleteAllNodes();

    void addNode(Nodes nodes);

    void updateNode(Nodes nodes);

    void deleteNode(int id);
}
