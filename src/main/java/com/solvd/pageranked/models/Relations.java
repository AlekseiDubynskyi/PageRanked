package com.solvd.pageranked.models;

public class Relations {
    private int id;
    private int nodesId;
    private int linksId;

    public Relations() {
    }

    public Relations(int id, int nodesId, int linksId) {
        this.id = id;
        this.nodesId = nodesId;
        this.linksId = linksId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNodesId() {
        return nodesId;
    }

    public void setNodesId(int nodesId) {
        this.nodesId = nodesId;
    }

    public int getLinksId() {
        return linksId;
    }

    public void setLinksId(int linksId) {
        this.linksId = linksId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Relations relations = (Relations) o;

        if (id != relations.id) return false;
        if (nodesId != relations.nodesId) return false;
        return linksId == relations.linksId;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + nodesId;
        result = 31 * result + linksId;
        return result;
    }

    @Override
    public String toString() {
        return "Relations{" +
                "id=" + id +
                ", nodesId=" + nodesId +
                ", linksId=" + linksId +
                '}';
    }
}
