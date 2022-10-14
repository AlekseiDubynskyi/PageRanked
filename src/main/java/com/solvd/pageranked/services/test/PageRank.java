package com.solvd.pageranked.services.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class PageRank {
    public int N = 5;
    public int path[][] = new int[N][N];
    public double pagerank[] = new double[N];

    /*    public void parseFile() throws IOException {
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
            System.out.println(Arrays.toString(sourceArray));
            for (int j = 0; j < nodes; j++) {
                path[currentLine][j] = Integer.parseInt(sourceArray[j]);
            }
            currentLine++;
        }
        while ((line = br.readLine()) != null);
    }*/

    public void calc(double totalNodes) {

        double InitialPageRank;
        double OutgoingLinks = 0;
        double DampingFactor = 0.85;
        double TempPageRank[] = new double[N];
        int ExternalNodeNumber;
        int InternalNodeNumber;
        int k = 1; // For Traversing
        int ITERATION_STEP = 1;
        InitialPageRank = 1 / totalNodes;
        System.out.printf(" Total Number of Nodes :" + totalNodes + "\t Initial PageRank  of All Nodes :" + InitialPageRank + "\n");

        // 0th ITERATION  _ OR _ INITIALIZATION PHASE //

        for (k = 0; k < totalNodes; k++) {
            this.pagerank[k] = InitialPageRank;
        }

        System.out.printf("\n Initial PageRank Values , 0th Step \n");
        for (k = 0; k < totalNodes; k++) {
            System.out.printf(" Page Rank of " + k + " is :\t" + this.pagerank[k] + "\n");
        }

        while (ITERATION_STEP <= 2) // Iterations
        {
            // Store the PageRank for All Nodes in Temporary Array
            for (k = 0; k < totalNodes; k++) {
                TempPageRank[k] = this.pagerank[k];
                this.pagerank[k] = 0;
            }

            for (InternalNodeNumber = 0; InternalNodeNumber < totalNodes; InternalNodeNumber++) {
                System.out.println(this.path[InternalNodeNumber][0] + ""+  this.path[InternalNodeNumber][1] + ""
                        +this.path[InternalNodeNumber][2] +""+ this.path[InternalNodeNumber][3] + ""+  this.path[InternalNodeNumber][4]);
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
                        //System.out.println("L "+ OutgoingLinks);
                        // Calculate PageRank
                        this.pagerank[InternalNodeNumber] += TempPageRank[ExternalNodeNumber] * (1 / OutgoingLinks);
                    }
                }
            }

            System.out.printf("\n After " + ITERATION_STEP + "th Step \n");

            for (k = 0; k < totalNodes; k++)
                System.out.printf(" Page Rank of " + k + " is :\t" + this.pagerank[k] + "\n");

            ITERATION_STEP = ITERATION_STEP + 1;
        }
        // Add the Damping Factor to PageRank
        for (k = 0; k < totalNodes; k++) {
            this.pagerank[k] = (1 - DampingFactor) + DampingFactor * this.pagerank[k];
        }

        // Display PageRank
        System.out.printf("\n Final Page Rank : \n");
        for (k = 0; k < totalNodes; k++) {
            System.out.printf(" Page Rank of " + k + " is :\t" + this.pagerank[k] + "\n");
        }

    }
}