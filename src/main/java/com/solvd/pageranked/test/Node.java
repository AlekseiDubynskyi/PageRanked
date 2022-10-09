package com.solvd.pageranked.test;

import java.util.List;

public class Node {
    private int id;
    private List<Integer> idChildren;

    public Node(String line) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getIdChildren() {
        return idChildren;
    }

    public void setIdChildren(List<Integer> idChildren) {
        this.idChildren = idChildren;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", idChildren=" + idChildren +
                '}';
    }
}
