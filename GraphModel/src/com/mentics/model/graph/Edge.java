package com.mentics.model.graph;


import com.esotericsoftware.kryo.DefaultSerializer;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

@DefaultSerializer(Edge.EdgeSerializer.class)
public class Edge {
    public static final Edge[] EDGES_EMPTY = new Edge[0];

    public static class EdgeSerializer extends Serializer<Edge> {
        public void write(Kryo kryo, Output output, Edge obj) {
            kryo.writeObject(output, obj.target);
            kryo.writeObject(output, obj.annotationNodes);
        }

        public Edge read(Kryo kryo, Input input, Class<Edge> type) {
            Node n = kryo.readObject(input, Node.class);
            Node[] anns = kryo.readObject(input, Node[].class);
            return new Edge(n, anns);
        }
    }

    public final Node target;
    public final Node[] annotationNodes;

    public Edge(Node target, Node... annotationNodes) {
        this.target = target;
        this.annotationNodes = annotationNodes;
    }
}
