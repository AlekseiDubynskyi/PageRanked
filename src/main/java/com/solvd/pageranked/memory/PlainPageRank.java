package com.solvd.pageranked.memory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.StringTokenizer;

public class PlainPageRank {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlainPageRank.class);
    private final Graph graph = new Graph();
    private final Map<Node, Double> pagerank_current = new HashMap<Node, Double>();
    private final Map<Node, Double> pagerank_new = new HashMap<Node, Double>();
    private final double dumping_factor;
    private final int iterations;

    public PlainPageRank(BufferedReader in, double dumping_factor, int iterations) {
        this.dumping_factor = dumping_factor;
        this.iterations = iterations;

        try {
            load_data(in);

            initialize_pagerank();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<Node, Double> compute() {
        double teleport = (1.0d - dumping_factor) / graph.countNodes();
        for (int i = 0; i < iterations; i++) {
            LOGGER.debug("iteration " + i);
            double dangling_nodes = 0.0d;
            for (Node node : graph.getNodes()) {
                if (graph.countOutgoingLinks(node) == 0) {
                    dangling_nodes += pagerank_current.get(node);
                }
            }
            dangling_nodes = (dumping_factor * dangling_nodes) / graph.countNodes();

            for (Node node : graph.getNodes()) {
                double r = 0.0d;
                for (Node source : graph.getIncomingLinks(node)) {
                    r += pagerank_current.get(source) / graph.countOutgoingLinks(source);
                }
                r = dumping_factor * r + dangling_nodes + teleport;
                pagerank_new.put(node, r);
            }
            for (Node node : graph.getNodes()) {
                pagerank_current.put(node, pagerank_new.get(node));
            }
        }

        return pagerank_current;
    }

    private void initialize_pagerank() {
        Double initial_pagerank = (1.0d / graph.countNodes());
        for (Node node : graph.getNodes()) {
            pagerank_current.put(node, initial_pagerank);
        }
    }

    private void load_data(BufferedReader in) throws IOException {
        long start = System.currentTimeMillis();

        String line;
        while ((line = in.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);
            Node source = new Node(st.nextToken());
            graph.addNode(source);
            HashSet<String> seen = new HashSet<String>();
            while (st.hasMoreTokens()) {
                String token = st.nextToken();
                if (!seen.contains(token)) { // no multiple links to the same page
                    Node destination = new Node(token);
                    if (destination != source) { // no self-links
                        graph.addNode(destination);
                        graph.addLink(source, destination);
                    }
                    seen.add(token);
                }
            }
        }
        in.close();

        LOGGER.debug(String.format("Loaded %d nodes and %d links in %d ms", graph.countNodes(), graph.countLinks(), (System.currentTimeMillis() - start)));
    }

}