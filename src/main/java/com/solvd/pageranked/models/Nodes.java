package com.solvd.pageranked.models;

public class Nodes {
    private int id;
    private int number;
    private int pageInfoId;

    public Nodes() {
    }

    public Nodes(int id, int number, int pageInfoId) {
        this.id = id;
        this.number = number;
        this.pageInfoId = pageInfoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPageInfoId() {
        return pageInfoId;
    }

    public void setPageInfoId(int pageInfoId) {
        this.pageInfoId = pageInfoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Nodes nodes = (Nodes) o;

        if (id != nodes.id) return false;
        if (number != nodes.number) return false;
        return pageInfoId == nodes.pageInfoId;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + number;
        result = 31 * result + pageInfoId;
        return result;
    }

    @Override
    public String toString() {
        return "Nodes{" +
                "id=" + id +
                ", number=" + number +
                ", pageInfoId=" + pageInfoId +
                '}';
    }
}
