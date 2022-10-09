package com.solvd.pageranked.memory;

import java.util.HashSet;
import java.util.Hashtable;

public class Graph {

    private final HashSet<Node> nodes = new HashSet<Node>();
    private final Hashtable<Node, HashSet<Node>> outgoing_links = new Hashtable<Node, HashSet<Node>>();
    private final Hashtable<Node, HashSet<Node>> incoming_links = new Hashtable<Node, HashSet<Node>>();
    private int count_links = 0;

    public void addNode(Node node) {
        if (nodes.contains(node)) return;

        nodes.add(node);
        if (!outgoing_links.containsKey(node)) {
            outgoing_links.put(node, new HashSet<Node>());
        }
        if (!incoming_links.containsKey(node)) {
            incoming_links.put(node, new HashSet<Node>());
        }
    }

    public void addLink(Node source, Node destination) {
        if (source.equals(destination)) return;

        addNode(source);
        addNode(destination);

        if (outgoing_links.get(source).contains(destination)) {
            return;
        }

        outgoing_links.get(source).add(destination);
        incoming_links.get(destination).add(source);
        count_links++;

    }

    public int countNodes() {
        return nodes.size();
    }

    public int countLinks() {
        return count_links;
    }

    public int countOutgoingLinks(Node node) {
        return outgoing_links.get(node).size();
    }

    public HashSet<Node> getIncomingLinks(Node node) {
        return incoming_links.get(node);
    }

    public HashSet<Node> getNodes() {
        return nodes;
    }

}
