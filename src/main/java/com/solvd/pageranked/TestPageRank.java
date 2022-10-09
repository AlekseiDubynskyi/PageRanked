package com.solvd.pageranked;

import com.solvd.pageranked.memory.Node;
import com.solvd.pageranked.memory.PlainPageRank;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class TestPageRank {
    private static final Logger LOGGER = LogManager.getLogger(TestPageRank.class);

    public static void main(String[] args) throws IOException {
        testPageRank();
    }

    public static void testPageRank() throws IOException {
        File input = new File("src/main/resources/test/test.txt");
        BufferedReader in = new BufferedReader(new FileReader(input));

        PlainPageRank pagerank = new PlainPageRank(in, 0.85d, 5);
        Map<Node, Double> result = pagerank.compute();

        print(result);
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
                return -o1.getValue().compareTo(o2.getValue());
            }
        });

        Map<Node, Double> result = new LinkedHashMap<Node, Double>();
        for (Entry<Node, Double> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}