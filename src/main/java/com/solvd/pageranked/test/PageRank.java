package com.solvd.pageranked.test;

import java.util.*;
import java.io.*;

public class PageRank {
    private int nodes;
    public int[][] path;
    public double[] pagerank;

    public int getNodes() {
        return nodes;
    }

    public void parseFile() throws IOException {
        String fileName = "src/main/resources/test/matrix.txt";
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        int currentLine = 0;
        String[] sourceArray = line.split(" ");
        nodes = sourceArray.length;
        path = new int[nodes][nodes];
        pagerank = new double[nodes];
        do {
            sourceArray = line.split(" ");
            for (int j = 0; j < nodes; j++) {
                path[currentLine][j] = Integer.parseInt(sourceArray[j]);
            }
            currentLine++;
        }
        while ((line = br.readLine()) != null);
    }


    public void calc() {
        double totalNodes = nodes;
        double InitialPageRank;
        double OutgoingLinks = 0;
        double DampingFactor = 0.85;
        double TempPageRank[] = new double[nodes];
        int ExternalNodeNumber;
        int InternalNodeNumber;
        int k; // For Traversing
        int ITERATION_STEP = 0;
        InitialPageRank = 1 / totalNodes;
        System.out.printf(" Total Number of Nodes :" + totalNodes + "\t Initial PageRank  of All Nodes :" + InitialPageRank + "\n");

        // 0th ITERATION  _ OR _ INITIALIZATION PHASE //

        for (k = 0; k < totalNodes; k++) {
            this.pagerank[k] = InitialPageRank;
        }

        System.out.printf("\n Initial PageRank Values , 0th Step \n");
        for (k = 0; k < totalNodes; k++) {
            System.out.printf(" Page Rank of " + (k + 1) + " is :\t" + this.pagerank[k] + "\n");
        }

        while (ITERATION_STEP <= 1) // Iterations
        {
            // Store the PageRank for All Nodes in Temporary Array
            for (k = 0; k < totalNodes; k++) {
                TempPageRank[k] = this.pagerank[k];
                this.pagerank[k] = 0;
            }

            for (InternalNodeNumber = 0; InternalNodeNumber < totalNodes; InternalNodeNumber++) {
                for (ExternalNodeNumber = 0; ExternalNodeNumber < totalNodes; ExternalNodeNumber++) {
                    if (this.path[ExternalNodeNumber][InternalNodeNumber] == 1) {
                        k = 0;
                        OutgoingLinks = 0; // Count the Number of Outgoing Links for each ExternalNodeNumber
                        while (k < totalNodes) {
                            if (this.path[ExternalNodeNumber][k] == 1) {
                                OutgoingLinks = OutgoingLinks + 1; // Counter for Outgoing Links
                            }
                            k = k + 1;
                        }
                        // Calculate PageRank
                        this.pagerank[InternalNodeNumber] += TempPageRank[ExternalNodeNumber] * (1 / OutgoingLinks);
                    }
                }
            }

            System.out.printf("\n After " + (ITERATION_STEP + 1) + "th Step \n");

            for (k = 0; k < totalNodes; k++)
                System.out.printf(" Page Rank of " + (k + 1) + " is :\t" + this.pagerank[k] + "\n");

            ITERATION_STEP = ITERATION_STEP + 1;
        }
        // Add the Damping Factor to PageRank
        for (k = 0; k < totalNodes; k++) {
            this.pagerank[k] = (1 - DampingFactor) + DampingFactor * this.pagerank[k];
        }

        // Display PageRank
        System.out.printf("\n Final Page Rank : \n");
        for (k = 0; k < totalNodes; k++) {
            System.out.printf(" Page Rank of " + (k + 1) + " is :\t" + this.pagerank[k] + "\n");
        }

    }
}