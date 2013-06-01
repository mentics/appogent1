package com.mentics.model.graph;

public class Node {
    public final int id;
    public final String name;
    public final Edge[] outgoingEdges;


    public Node(int id, String name, Edge[] outgoingEdges) {
        this.id = id;
        this.name = name;
        this.outgoingEdges = outgoingEdges;
    }

    public Node(int id, String name) {
        this(id, name, Edge.EDGES_EMPTY);
    }

    @Override
    public String toString() {
        return name;
    }
}
