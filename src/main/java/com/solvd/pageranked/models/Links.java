package com.solvd.pageranked.models;

public class Links {
    private int id;
    private int number;

    public Links() {
    }

    public Links(int id, int number) {
        this.id = id;
        this.number = number;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Links links = (Links) o;

        if (id != links.id) return false;
        return number == links.number;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + number;
        return result;
    }

    @Override
    public String toString() {
        return "Links{" +
                "id=" + id +
                ", number=" + number +
                '}';
    }
}
