package com.sto.graph;

public class GraphsFactory<T> {

	public Graph<T> createUndirectedGraph() {
		return new GraphImpl<T>(); 
	};
	
	public Graph<T> createDirectedGraph() {
		return new GraphImpl<T>();
	};

}
