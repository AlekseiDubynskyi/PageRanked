package com.solvd.pageranked.services.test;

import com.solvd.pageranked.services.matrix.Matrix;

import java.io.IOException;
import java.util.Scanner;

import static com.solvd.pageranked.services.matrix.IMatrixCreator.getMyMatrix;

public class Main {
    public static void main(String[] args) throws IOException {
        Matrix matrix = getMyMatrix();
        int nodes, i, j;
        Scanner in = new Scanner(System.in);
        System.out.println("The Number of WebPages \n" + matrix.getDimension());
        nodes = matrix.getDimension();
        PageRank p = new PageRank();
        for (i = 0; i < nodes; i++)
            for (j = 0; j < nodes; j++) {
                p.path[i][j] = matrix.getMatrix()[i][j];
                if (j == i)
                    p.path[i][j] = 0;
            }
        p.calc(nodes);
    }
}

