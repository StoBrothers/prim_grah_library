package com.sto.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of Prim's algorithm.
 * 
 * @author Sergey Stotskiy
 */
final class GraphImpl<T> implements Graph<T> {

	private static Logger logger = LoggerFactory.getLogger(GraphImpl.class);
	
	private MSTBuilder<T> MSTbuilder = new MSTBuilder<T>();
	

	/**
	 * Graph
	 */
	private Map<Vertex<T>, List<Edge<T>>> vertices = new HashMap<>();

	/**
	 * Graph with MIN path created after building path with minimal weight of edges.
	 */
	private Set<Edge<T>> MST = new HashSet<>();

	/**
	 * Constructor.
	 * 
	 * @param graphType - type of Graph.
	 */
	public GraphImpl() {

	}

	@Override
	public void addVertex(final Vertex<T> vertex) {
		this.vertices.putIfAbsent(vertex, new ArrayList<>());
	}

	@Override
	public void addEdge(final Edge<T> edge) {
		addUndirectedGraphEdge(edge);
	}

	@Override
	public void addEdge(List<Vertex<T>> inpVertices, int start, int end, int weight) {

		if (start > end) { // Order name of edge
			int temp = start;
			start = end;
			end = temp;
		}

		Vertex<T> vertex1 = inpVertices.get(start - 1);
		Vertex<T> vertex2 = inpVertices.get(end - 1);

		Edge<T> edge = new Edge<>(vertex1, vertex2, weight);

		addEdge(vertex1, edge);
		addEdge(vertex2, edge);
	}

	private void addEdge(Vertex<T> vertex1, Edge<T> edge) {
		List<Edge<T>> edges = this.vertices.get(vertex1);
		if (!edges.contains(edge)) {
			edges.add(edge);
		}
	}

	/**
	 * Add Edge to directed graph with duplicate check.
	 * 
	 * @param edge
	 */
	private void addDirectedGraphEdge(final Edge<T> edge) {
		List<Edge<T>> edges = this.vertices.get(edge.getStartVertex());
		if (!edges.contains(edge)) {
			edges.add(edge);
		}
	}

	/**
	 * Add Edge to undirected graph with duplicate check.
	 * 
	 * @param edge
	 */
	private void addUndirectedGraphEdge(final Edge<T> edge) {
		addDirectedGraphEdge(edge);
		List<Edge<T>> edges = this.vertices.get(edge.getEndVertex());
		if (!edges.contains(edge)) {
			edges.add(edge);
		}
	}

	@Override
	public List<Edge<T>> getPath(final Vertex<T> startVertex, final Vertex<T> endVertex) {
		return dfs(startVertex, endVertex);
	}

	private List<Edge<T>> dfs(final Vertex<T> startVertex, final Vertex<T> endVertex) {
		Set<Vertex<T>> visited = new HashSet<>();
		List<Edge<T>> path = new ArrayList<>();
		dfsRecursive(path, startVertex, endVertex, visited);
		Collections.reverse(path);
		return path;
	}

	private boolean dfsRecursive(final List<Edge<T>> path, final Vertex<T> startVertex, final Vertex<T> endVertex,
			final Set<Vertex<T>> visited) {
		visited.add(startVertex);

		logger.debug("Visit:" + startVertex.toString());

		List<Edge<T>> edges = this.MST.stream()
				.filter(t -> ((t.getStartVertex() == startVertex) || (t.getEndVertex() == startVertex)))
				.collect(Collectors.toList());//
		for (Edge<T> current : edges) {
			if (!visited.contains(current.getEndVertex())) {

				if (current.getEndVertex().equals(endVertex)) {
					path.add(current);
					return true;
				}

				if (dfsRecursive(path, current.getEndVertex(), endVertex, visited)) {
					path.add(current);
					return true;
				}
			} else if (!visited.contains(current.getStartVertex())) {
				if (current.getStartVertex().equals(endVertex)) {
					path.add(current);
					return true;
				}

				if (dfsRecursive(path, current.getStartVertex(), endVertex, visited)) {
					path.add(current);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Implementation of Prim's algorithm for building path with min weight of edges
	 * (MST).
	 */
	@Override
	public Set<Edge<T>> buildMST(final Vertex<T> startVertex) {
		this.MST = MSTbuilder.buildMST(startVertex, this.vertices);
		return this.MST;
	}


	/**
	 * Comparator by name of start vertex of edges.
	 *
	 * @param <T>
	 */
	public static class GraphComparator<T> implements Comparator<Edge<T>> {

		@Override
		public int compare(Edge<T> o1, Edge<T> o2) {
			return (((String) o1.getStartVertex().getVertex()).compareTo((String) o2.getStartVertex().getVertex()));
		}
	}

}
