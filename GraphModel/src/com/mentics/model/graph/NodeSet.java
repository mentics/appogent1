package com.mentics.model.graph;

public class NodeSet {
    public final int id;
    public final Node[] nodes;
    public final Edge[] outgoingEdges;

    public NodeSet(int id, Node[] nodes, Edge[] outgoingEdges) {
        this.id = id;
        this.nodes = nodes;
        this.outgoingEdges = outgoingEdges;
    }
}
