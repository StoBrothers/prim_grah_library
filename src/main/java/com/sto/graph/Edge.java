package com.sto.graph;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;


@AllArgsConstructor
@ToString
public class Edge<T> implements Comparable<Edge<T>> {

	@Override
	public int compareTo(Edge<T> ext) {
		if (this.weight.compareTo(ext.weight) == 0) {
			return -1;
		} else return this.weight.compareTo(ext.weight);
		
	}

	@Getter
	@NonNull
	protected Vertex<T> startVertex;
	@Getter
	@NonNull
	protected Vertex<T> endVertex;
	@Getter
	@NonNull
	protected Integer weight;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endVertex == null) ? 0 : endVertex.hashCode());
		result = prime * result + ((startVertex == null) ? 0 : startVertex.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		Edge<T> other = (Edge<T>) obj;
		if (endVertex == null) {
			if (other.endVertex != null)
				return false;
		} else if (!endVertex.equals(other.endVertex))
			return false;
		if (startVertex == null) {
			if (other.startVertex != null)
				return false;
		} else if (!startVertex.equals(other.startVertex))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}
	
	
	
	
	
}
