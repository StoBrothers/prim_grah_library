package com.sto.graph;

import java.util.List;
import java.util.Set;

public interface Graph <T> {
	
	/** Adds vertex to the graph.
	 * 
	 * @param vertex vertex for add to the graph
	 */
	void addVertex(Vertex<T> vertex);

	/**
	 * Adds edge to the graph.
	 * 
	 * @param edge edge for add to the graph.
	 */
	void addEdge(Edge<T> edge);

	/**
	 * Adds edge to the graph. This approach used For filling with test data.
	 * 
	 * @param vertexes
	 * @param start
	 * @param end
	 * @param weight
	 */
	void addEdge(List<Vertex<T>> vertexes, int start, int end, int weight);
	
	/**
	 * Get list of edges between 2 vertices (startVertex and endVertex).
	 *  
	 * @param startVertex start.
	 * @param endVertex end. 
	 * @return List of Edges between 2 vertices.
	 */
	List<Edge<T>> getPath(Vertex<T> startVertex, Vertex<T> endVertex);
	
	/**
	 * Build graph with minimal path by weight. Implementation of Prims algorithm.
	 * 
	 * @param startVertex
	 * @return
	 */
	public Set<Edge<T>> buildMinWeightGraph(final Vertex<T> startVertex);


}
