package com.solvd.pageranked.services.test;

import com.solvd.pageranked.dao.jdbc.mysql.Impl.NodesDAO;
import com.solvd.pageranked.models.Matrix;

import java.io.IOException;
import java.util.Scanner;

import static com.solvd.pageranked.services.mainLogic.MatrixCreator.getMyMatrix;

public class PageRank {
    NodesDAO nodesDAO = new NodesDAO();
    public int N = nodesDAO.getAllNodes().size();
    public int path[][] = new int[N][N];
    public double pagerank[] = new double[N];

    public static void Start() throws IOException {
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
        p.Calculator(nodes);
    }

    public void Calculator(double totalNodes) {
        double InitialPageRank;
        double OutgoingLinks = 0;
        double DampingFactor = 0.85;
        double TempPageRank[] = new double[N];
        int ExternalNodeNumber;
        int InternalNodeNumber;
        int k = 1;
        int ITERATION_STEP = 1;
        InitialPageRank = 1 / totalNodes;
        System.out.printf(" Total Number of Nodes :" + totalNodes + "\t Initial PageRank  of All Nodes :" + InitialPageRank + "\n");

        for (k = 0; k < totalNodes; k++) {
            this.pagerank[k] = InitialPageRank;
        }

        System.out.printf("\n Initial PageRank Values , 0th Step \n");
        for (k = 0; k < totalNodes; k++) {
            System.out.printf(" Page Rank of " + k + " is :\t" + this.pagerank[k] + "\n");
        }

        while (ITERATION_STEP <= 2) // Iterations
        {
            for (k = 0; k < totalNodes; k++) {
                TempPageRank[k] = this.pagerank[k];
                this.pagerank[k] = 0;
            }

            for (InternalNodeNumber = 0; InternalNodeNumber < totalNodes; InternalNodeNumber++) {
                System.out.println(this.path[InternalNodeNumber][0] + "" + this.path[InternalNodeNumber][1] + ""
                        + this.path[InternalNodeNumber][2] + "" + this.path[InternalNodeNumber][3] + "" + this.path[InternalNodeNumber][4]);
                for (ExternalNodeNumber = 0; ExternalNodeNumber < totalNodes; ExternalNodeNumber++) {
                    if (this.path[ExternalNodeNumber][InternalNodeNumber] == 1) {
                        k = 0;
                        OutgoingLinks = 0;
                        while (k < totalNodes) {
                            if (this.path[ExternalNodeNumber][k] == 1) {
                                OutgoingLinks = OutgoingLinks + 1;
                            }
                            k = k + 1;
                        }

                        this.pagerank[InternalNodeNumber] += TempPageRank[ExternalNodeNumber] * (1 / OutgoingLinks);
                    }
                }
            }

            System.out.printf("\n After " + ITERATION_STEP + "th Step \n");

            for (k = 0; k < totalNodes; k++)
                System.out.printf(" Page Rank of " + k + " is :\t" + this.pagerank[k] + "\n");

            ITERATION_STEP = ITERATION_STEP + 1;
        }

        for (k = 0; k < totalNodes; k++) {
            this.pagerank[k] = (1 - DampingFactor) + DampingFactor * this.pagerank[k];
        }

        System.out.printf("\n Final Page Rank : \n");
        for (k = 0; k < totalNodes; k++) {
            int kk = k + 1;
            System.out.printf(" Page Rank of " + kk + " is :\t" + this.pagerank[k] + "\n");
        }
    }
}
