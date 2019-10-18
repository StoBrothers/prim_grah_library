# Implementation of Prim's graph algorithm library
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


--------------------------------------------------------------------------------------
Procedure PrimsMST(Graph):     // here Graph is a non-empty connected weighted graph
Vnew[] = {x}                   // New subgraph Vnew with source node x
Enew[] = {}
while Vnew is not equal to V
    u -> a node from Vnew
    v -> a node that is not in Vnew such that edge u-v has the minimum cost
                               // if two nodes have same weight, pick any of them
    add v to Vnew
    add edge (u, v) to Enew
end while
Return Vnew and Enew
--------------------------------------------------------------------------------------


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

First step.  Define . 
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

