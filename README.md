# Implementation of Prims graph algorithm library
------------

##### Technologies: Java 8, JUnit, Lombok.

========================

Requirements
------------
Install Java 8.65 version
http://www.oracle.com/ 

Install Apache Maven 
https://maven.apache.org

Introduction
------------

   A Minimum Spanning Tree is a tree whose sum of length/weight of edges is minimum as possible.
For example, if you want to setup communication between a set of cities, then you may want to
use the least amount of wire as possible. MST can be used to find the network path and wire cost
estimate.
   This library allows building the path between two vertices use Prims algorithm with weight edges.
Implementation of this library based on the Depth-First Search (DFS) algorithm.
The DFS algorithm is starting from starting point and go into the depth of the graph
until it reaches a dead end and then move up to the parent node.

Java classes
--------------------------

- Vertex - wrapper for user defined type.
- Edge - edge between two vertices.
- Graph - interface for working with graph instance.
- GraphImpl - Implementation of graphs.
- GraphsFactory - Factory for building directed and undirected graphs.
- GraphType - types of graphs.

Test java classes
--------------------------

GraphUnitTest  - unit test

How to build the project?
--------------------------

```
mvn clean install
```


How to run tests?
--------------------------

```
mvn test
```

This undirected graph with weight of edges used in tests.
--------------------------

![alt text](https://github.com/StoBrothers/prim_grah_library/blob/master/1.png)



This directed graph used in tests.
--------------------------

![alt text](https://github.com/StoBrothers/prim_grah_library/blob/master/2.png)


![alt text](https://github.com/StoBrothers/prim_grah_library/blob/master/3.png)

![alt text](https://github.com/StoBrothers/prim_grah_library/blob/master/4.png)

![alt text](https://github.com/StoBrothers/prim_grah_library/blob/master/5.png)

![alt text](https://github.com/StoBrothers/prim_grah_library/blob/master/6.png)

![alt text](https://github.com/StoBrothers/prim_grah_library/blob/master/7.png)



Example of test results
--------------------------

https://github.com/StoBrothers/graph_library/blob/master/test.log




author: Sergey Stotskiy

