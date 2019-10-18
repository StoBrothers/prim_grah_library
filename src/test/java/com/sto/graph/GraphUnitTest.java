package com.sto.graph;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sto.graph.GraphImpl.GraphComparator;

public class GraphUnitTest {

	private static Logger logger = LoggerFactory.getLogger(GraphUnitTest.class);

	private List<Vertex<String>> vertexes = null;

	private Graph<String> graph = null;
	
	private Set<Edge<String>> graph_edges = null;
	

	public GraphUnitTest() {
		
		GraphsFactory<String> fabric = new GraphsFactory<>();
		
		graph = fabric.createUndirectedGraph();

		vertexes = new ArrayList<>();
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

		graph.addEdge(vertexes, 1, 5, 2);// 1-5
		graph.addEdge(vertexes, 1, 4, 10);// 1-4
		graph.addEdge(vertexes, 1, 2, 4);// 1-2
		graph.addEdge(vertexes, 2, 4, 8);// 2-4
		graph.addEdge(vertexes, 5, 4, 5);// 5-4
		graph.addEdge(vertexes, 5, 6, 51);// 5-6
		graph.addEdge(vertexes, 6, 7, 1);// 6-7
		graph.addEdge(vertexes, 4, 7, 11);// 4-7
		graph.addEdge(vertexes, 6, 8, 2);// 6-8
		graph.addEdge(vertexes, 7, 8, 23);// 7-8
		graph.addEdge(vertexes, 4, 8, 9);// 4-8
		graph.addEdge(vertexes, 3, 8, 19);// 3-8
		graph.addEdge(vertexes, 4, 3, 11);// 4-3
		graph.addEdge(vertexes, 2, 3, 18);// 2-3

		
		this.graph_edges = graph.buildMST(vertexes.get(0)); //build min weight graph

		logger.info("Result graph with MIN path:" + this.graph_edges.size());

	}

	@Test
	public void test_building_min_graph_when_expected_MIN_graph_thenPrintPath_ver1() {

		logger.info("Building and checking graph with MIN path:");
		this.graph_edges = graph.buildMST(vertexes.get(0)); //build min weight graph
		List<Edge<String>> buildedGraph = graph_edges.stream().sorted(new GraphComparator<String>()).collect(Collectors.toList());
		
		logger.debug("Buided min path graph with edges:");
		buildedGraph.stream().forEach(t -> logger.debug(t.toString()));

		List<Edge<String>> expectedList = new ArrayList<>(7);
		expectedList.add(new Edge<String>(new Vertex<>("1"), new Vertex<>("5"), 2));
		expectedList.add(new Edge<String>(new Vertex<>("1"), new Vertex<>("2"), 4));
		expectedList.add(new Edge<String>(new Vertex<>("3"), new Vertex<>("4"), 11));
		expectedList.add(new Edge<String>(new Vertex<>("4"), new Vertex<>("5"), 5));
		expectedList.add(new Edge<String>(new Vertex<>("4"), new Vertex<>("8"), 9));
		expectedList.add(new Edge<String>(new Vertex<>("6"), new Vertex<>("7"), 1));
		expectedList.add(new Edge<String>(new Vertex<>("6"), new Vertex<>("8"), 2));

		assertArrayEquals("Builded min graph is not right", expectedList.toArray(), buildedGraph.toArray());
	}

	@Test
	public void test_min_path_when_expected_path_thenPrintPath_ver1() {

		logger.info("Result graph with MIN path for 7-2:");

		List<Edge<String>> resultList = graph.getPath(vertexes.get(6), vertexes.get(1));// 7 - 2
		List<Edge<String>> expectedList = new ArrayList<>(7);

		expectedList.add(new Edge<String>(new Vertex<>("6"), new Vertex<>("7"), 1));
		expectedList.add(new Edge<String>(new Vertex<>("6"), new Vertex<>("8"), 2));
		expectedList.add(new Edge<String>(new Vertex<>("4"), new Vertex<>("8"), 9));
		expectedList.add(new Edge<String>(new Vertex<>("4"), new Vertex<>("5"), 5));
		expectedList.add(new Edge<String>(new Vertex<>("1"), new Vertex<>("5"), 2));
		expectedList.add(new Edge<String>(new Vertex<>("1"), new Vertex<>("2"), 4));

		logger.debug("Result path:" + resultList.toString());

		assertArrayEquals("Result list is wrong", expectedList.toArray(), resultList.toArray());

	}

	
	@Test
	public void test_second_min_path_when_expected_path_thenPrintPath_ver2() {

		logger.info("Result graph with MIN path for 1-6:");

		List<Edge<String>> resultList = graph.getPath(vertexes.get(0), vertexes.get(5));// 6 - 1
		List<Edge<String>> expectedList = new ArrayList<>(4);

		expectedList.add(new Edge<String>(new Vertex<>("1"), new Vertex<>("5"), 2));
		expectedList.add(new Edge<String>(new Vertex<>("4"), new Vertex<>("5"), 5));
		expectedList.add(new Edge<String>(new Vertex<>("4"), new Vertex<>("8"), 9));
		expectedList.add(new Edge<String>(new Vertex<>("6"), new Vertex<>("8"), 2));

		logger.debug("Result path:" + resultList.toString());

		assertArrayEquals("Result list is wrong", expectedList.toArray(), resultList.toArray());

	}

	
	@Test
	public void test_second_min_path_when_expected_path_thenPrintPath_ver3() {

		logger.info("Result graph with MIN path for 6-1:");

		List<Edge<String>> resultList = graph.getPath(vertexes.get(5), vertexes.get(0));// 6 - 1

		List<Edge<String>> expectedList = new ArrayList<>(4);

		expectedList.add(new Edge<String>(new Vertex<>("6"), new Vertex<>("8"), 2));
		expectedList.add(new Edge<String>(new Vertex<>("4"), new Vertex<>("8"), 9));
		expectedList.add(new Edge<String>(new Vertex<>("4"), new Vertex<>("5"), 5));
		expectedList.add(new Edge<String>(new Vertex<>("1"), new Vertex<>("5"), 2));

		logger.debug("Result path:" + resultList.toString());

		assertArrayEquals("Result list is wrong", expectedList.toArray(), resultList.toArray());

	}

	
	@Test
	public void test_next_min_path_when_expected_path_thenPrintPath() {

		logger.info("Result graph with MIN path for 3-1:");

		List<Edge<String>> resultList = graph.getPath(vertexes.get(2), vertexes.get(0));// 3 - 1
		
		List<Edge<String>> expectedList = new ArrayList<>(3);

		expectedList.add(new Edge<String>(new Vertex<>("3"), new Vertex<>("4"), 11));
		expectedList.add(new Edge<String>(new Vertex<>("4"), new Vertex<>("5"), 5));
		expectedList.add(new Edge<String>(new Vertex<>("1"), new Vertex<>("5"), 2));

		logger.debug("Result path:" + resultList.toString());

		assertArrayEquals("Result is wrong", expectedList.toArray(), resultList.toArray());

	}

}
