package com.mentics.model.graph;

import com.esotericsoftware.kryo.DefaultSerializer;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

@DefaultSerializer(Node.NodeSerializer.class)
public class Node {
    public static class NodeSerializer extends Serializer<Node> {
        public void write(Kryo kryo, Output output, Node obj) {
            output.writeLong(obj.id);
            output.writeString(obj.name);
            kryo.writeObject(output, obj.outgoingEdges);
        }

        public Node read(Kryo kryo, Input input, Class<Node> type) {
            long id = input.readLong();
            String name = input.readString();
            Edge[] edges = kryo.readObject(input, Edge[].class);
            return new Node(id, name, edges);
        }
    }

    public final long id;
    public final String name;
    public final Edge[] outgoingEdges;


    public Node(long id, String name, Edge[] outgoingEdges) {
        this.id = id;
        this.name = name;
        this.outgoingEdges = outgoingEdges;
    }

    public Node(long id, String name) {
        this(id, name, Edge.EDGES_EMPTY);
    }

    @Override
    public String toString() {
        return name;
    }
}
