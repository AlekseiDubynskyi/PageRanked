package com.solvd.pageranked.models;

public class PageInfo {
    private int id;
    private String name;
    private double pageRank;

    public PageInfo() {
    }

    public PageInfo(int id, String name, double pageRank) {
        this.id = id;
        this.name = name;
        this.pageRank = pageRank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPageRank() {
        return pageRank;
    }

    public void setPageRank(double pageRank) {
        this.pageRank = pageRank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PageInfo pageInfo = (PageInfo) o;

        if (id != pageInfo.id) return false;
        if (Double.compare(pageInfo.pageRank, pageRank) != 0) return false;
        return name.equals(pageInfo.name);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + name.hashCode();
        temp = Double.doubleToLongBits(pageRank);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pageRank=" + pageRank +
                '}';
    }
}
