package com.mentics.model.graph;


public class Edge {
    public static final Edge[] EDGES_EMPTY = new Edge[0];

    public final Node target;
    public final Node[] annotationNodes;

    public Edge(Node target, Node... annotationNodes) {
        this.target = target;
        this.annotationNodes = annotationNodes;
    }
}
