package com.solvd;

import com.solvd.memory.Node;
import com.solvd.memory.PlainPageRank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class TestPageRank {

    public static void main(String[] args) throws IOException {
        testPageRank();
    }

    public static void testPageRank() throws IOException {
        File input = new File ("src/main/resources/test/test.dat");
        BufferedReader in = new BufferedReader(new FileReader (input)) ;

        PlainPageRank pagerank1 = new PlainPageRank (in, 0.85d, 30) ;
        Map<Node, Double> result1 = pagerank1.compute() ;

        print(result1);
    }


    private static void print(Map<Node, Double> result) {
        Map<Node, Double> sorted = sortByValue(result);
        for (Node node : sorted.keySet()) {
            System.out.printf("%10s : %1.20f\n", node.getId(), sorted.get(node));
        }
    }

    private static Map<Node, Double> sortByValue(Map<Node, Double> map) {
        List<Entry<Node, Double>> list = new LinkedList<Entry<Node, Double>>(map.entrySet());

        Collections.sort(list, new Comparator<Entry<Node, Double>>() {
            @Override
            public int compare(Entry<Node, Double> o1, Entry<Node, Double> o2) {
                return - o1.getValue().compareTo(o2.getValue());
            }
        });

        Map<Node, Double> result = new LinkedHashMap<Node, Double>();
        for (Entry<Node, Double> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }


}