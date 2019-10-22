package com.sto.graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of Prim's algorithm for building path with min weight of edges
 * (MST).
 *
 * @author Sergey Stotskiy
 *
 * @param <T>
 */
public class MSTBuilder<T> {

	private static Logger logger = LoggerFactory.getLogger(MSTBuilder.class);

	/**
	 * Build MST.
	 * 
	 * @param startVertex - start vertex
	 * @param vertices    vertices
	 * 
	 * @return result MST
	 */
	public Set<Edge<T>> buildMST(final Vertex<T> startVertex, final Map<Vertex<T>, List<Edge<T>>> vertices) {

		List<Vertex<T>> arrayOfVertices = vertices.keySet().stream().filter(t -> !t.equals(startVertex))
				.collect(Collectors.toList());// collect all vertices except started vertex

		Set<Vertex<T>> notVisitedVertex = new HashSet<>();
		notVisitedVertex.addAll(arrayOfVertices);

		Set<Vertex<T>> visitedVertices = new HashSet<>();
		Set<Edge<T>> MSTgraph = new HashSet<>();// Result of Minimum Spanning Tree (MST)

		Vertex<T> currentVertex = startVertex;
		visitedVertices.add(currentVertex); // add vertex to visited set
		Set<Edge<T>> edges = new TreeSet<>(new EdgeComparator<>());
		edges.addAll(vertices.get(currentVertex)); // get edges for selected vertex)

		if (edges.size() == 0) {
			logger.error("This graph does not contain edges. Building MST is impossible.");
			return null;
		}

		while (notVisitedVertex.size() != 0) {// checking that not visited vertices existed
			Iterator<Edge<T>> iterator = edges.iterator();// iterator for sorted collection by MIN weight
			Edge<T> currentMinEdge = null; //
			while (iterator.hasNext()) {// check existing next edge with MIN weight
				currentMinEdge = iterator.next();// get edge with MIN weight for selected vertex
				Vertex<T> vertexStart = currentMinEdge.getStartVertex();// get first vertex
				Vertex<T> vertexEnd = currentMinEdge.getEndVertex();// get second vertex
				if (visitedVertices.contains(vertexStart) && visitedVertices.contains(vertexEnd)) {
					// if both of vertices visited early then get next MIN edge
					continue;// go to the next edge
				} else if (visitedVertices.contains(vertexStart)) {// if visited only first vertex not second
					visitedVertices.add(currentMinEdge.getEndVertex());// add new visited vertex
					notVisitedVertex.remove(currentMinEdge.getEndVertex());// remove this vertex from non visited
																			// vertices
					MSTgraph.add(currentMinEdge);// add this edge to the result graph
					edges.addAll(vertices.get(vertexEnd));// add all edges for new vertex to list
					break; // go to the new list of edges
				} else if (visitedVertices.contains(vertexEnd)) {// if visited only second vertex not first
					visitedVertices.add(currentMinEdge.getStartVertex());// add new visited vertex
					notVisitedVertex.remove(currentMinEdge.getStartVertex());// remove this vertex from non visited
					MSTgraph.add(currentMinEdge);// add this edge to the result graph
					edges.addAll(vertices.get(vertexStart));// get edges for selected vertex
					break; // go to the new list of edges
				} else {
					// if both of vertices is not visited and don't have link to start vertex
					continue; // go to the next edge
				}
			}
		}
		return MSTgraph;
	}

	/**
	 * Comparator by weight of edges.
	 *
	 * @param <T>
	 */
	@SuppressWarnings("hiding")
	private class EdgeComparator<T> implements Comparator<Edge<T>> {

		@Override
		public int compare(Edge<T> o1, Edge<T> o2) {
			if (o1.getWeight() == o2.getWeight()) {
				return -1;
			}
			return o1.getWeight().compareTo(o2.getWeight());
		}
	}

}
