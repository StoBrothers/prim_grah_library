package com.sto.graph;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Wrapper for every vertex.  
 * 
 * @author Sergey Stotskiy 
 *
 * @param <T>
 */

@AllArgsConstructor
@ToString
class Vertex <T> implements Comparable <T> {
    /**
     * Customer defined type of vertex.
     */
	@Getter
    private T vertex;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vertex == null) ? 0 : vertex.hashCode());
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
		Vertex<T> other = (Vertex<T>) obj;
		if (vertex == null) {
			if (other.vertex != null)
				return false;
		} else if (!vertex.equals(other.vertex))
			return false;
		return true;
	}

	@Override
	public int compareTo(T o) {
		if (o instanceof String) {
			String new_parameter = (String) o;
			((String)this.vertex).compareTo(new_parameter);
			
		}
		return 0;
	} 
	
	

}