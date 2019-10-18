package com.sto.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.sto.graph.GraphImpl.GraphComparator;

public class Main {

	public static void main(String[] args) {
		GraphsFactory<String> fabric = new GraphsFactory<>();
		Graph<String> graph = fabric.createUndirectedGraph();
		
        List<Vertex<String>> vertexes = new ArrayList<>(); 
        vertexes.add(new Vertex<String>("1"));
        vertexes.add(new Vertex<String>("2"));
        vertexes.add(new Vertex<String>("3"));
        vertexes.add(new Vertex<String>("4"));
        vertexes.add(new Vertex<String>("5"));
        vertexes.add(new Vertex<String>("6"));
        vertexes.add(new Vertex<String>("7"));
        vertexes.add(new Vertex<String>("8"));

        
        graph.addVertex(vertexes.get(0));
        graph.addVertex(vertexes.get(1));
        graph.addVertex(vertexes.get(2));
        graph.addVertex(vertexes.get(3));
        graph.addVertex(vertexes.get(4));
        graph.addVertex(vertexes.get(5));
        graph.addVertex(vertexes.get(6));
        graph.addVertex(vertexes.get(7));


        graph.addEdge(vertexes, 1, 5, 2);//1-5
        graph.addEdge(vertexes, 1, 4, 10);//1-4
        graph.addEdge(vertexes, 1, 2, 4);//1-2
        graph.addEdge(vertexes, 2, 4, 8);//2-4
        graph.addEdge(vertexes, 5, 4, 5);//5-4
        graph.addEdge(vertexes, 5, 6, 51);//5-6
        graph.addEdge(vertexes, 6, 7, 1);//6-7
        graph.addEdge(vertexes, 4, 7, 11);//4-7
        graph.addEdge(vertexes, 6, 8, 2);//6-8
        graph.addEdge(vertexes, 7, 8, 23);//7-8
        graph.addEdge(vertexes, 4, 8, 9);//4-8
        graph.addEdge(vertexes, 3, 8, 19);//3-8
        graph.addEdge(vertexes, 4, 3, 11);//4-3
        graph.addEdge(vertexes, 2, 3, 18);//2-3

        
        System.out.println("---Result-graph-with-MIN--path:-----------------------------------------");
        
		Set<Edge<String>> graph_edges = graph.buildMinWeightGraph(vertexes.get(0)); //build min weight graph
		
		graph_edges.stream().sorted(new GraphComparator<String>()).forEach(t -> System.out.println(t));
		
		List<Edge<String>> path = graph.getPath(vertexes.get(6), vertexes.get(1));

		System.out.println("----Patch:--------------------------------------------------------------");
		
		path.stream().forEach(t -> System.out.println(t));
		
		System.out.println("------------------------------------------------------------------------");
	}

}
