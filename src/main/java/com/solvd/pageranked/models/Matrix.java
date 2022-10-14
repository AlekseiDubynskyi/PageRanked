package com.solvd.pageranked.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.solvd.pageranked.dao.jdbc.mysql.Impl.LinksDAO;
import com.solvd.pageranked.dao.jdbc.mysql.Impl.NodesDAO;
import com.solvd.pageranked.services.mainLogic.MatrixCreator;

public class Matrix {
    private int dimension;
    private int[][] matrix;

    public Matrix() {
    }

    public Matrix(int dimension) {
        this.dimension = dimension;
    }

    public Matrix(int dimension, int[][] matrix) {
        this.dimension = dimension;
        this.matrix = matrix;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public void print() {
        for (int i = 0; i < dimension; i++) {
            List<Integer> integerList = new ArrayList<>();
            for (int j = 0; j < dimension; j++) {
                integerList.add(matrix[i][j]);
            }
            System.out.println(integerList);
        }
    }

    @Override
    public String toString() {
        return "Matrix{" +
                "dimension=" + dimension +
                ", matrix=" + Arrays.toString(matrix) +
                '}';
    }

    public Matrix matrixFilling() {
        NodesDAO nodesDAO = new NodesDAO();
        LinksDAO linksDAO = new LinksDAO();
        int[][] matrix1 = new int[nodesDAO.getAllNodes().size()][nodesDAO.getAllNodes().size()];

        for (int i = 1; i <= nodesDAO.getAllNodes().size(); i++) {
            Nodes node = nodesDAO.getById(i);
            for (int j = 1; j <= nodesDAO.getAllNodes().size(); j++) {
                Nodes node1 = nodesDAO.getById(j);
                Links link = linksDAO.getIdByLinkHref(node1.getName());
                Relations relations = new Relations(0, node.getId(), link.getId());
                if (MatrixCreator.find(relations) != null) {
                    matrix1[i - 1][j - 1] = 1;
                } else {
                    matrix1[i - 1][j - 1] = 0;
                }
            }
        }
        return new Matrix(nodesDAO.getAllNodes().size(), matrix1);
    }
}