package com.solvd.pageranked.services.test;

import java.io.IOException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException {
        int nodes;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the Adjacency Matrix with 1->PATH & 0->NO PATH Between two WebPages: \n");
        PageRank pageRank = new PageRank();
        pageRank.parseFile();
        nodes = pageRank.getNodes();
        for (int i = 0; i < nodes; i++) {

            for (int j = 0; j < nodes; j++) {
                System.out.print(pageRank.path[i][j] + " ");
            }
            System.out.println();
        }
        pageRank.calc();

    }
}

