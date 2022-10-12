package com.solvd.pageranked.dao;

import com.solvd.pageranked.models.Nodes;

import java.util.List;

public interface INodes {
    List<Nodes> getAllNodes();

    void addNode(Nodes nodes);

    void updateNode(Nodes nodes);

    void deleteNode(int id);
}
