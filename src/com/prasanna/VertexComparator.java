/**
 * Author: Prasanna Lalingkar.
 * ID: 800936073.
 * 
 * This class compares the vertices by their names to sort them.
 */

package com.prasanna;

import java.util.Comparator;

public class VertexComparator implements Comparator<Vertex> {

	@Override
	public int compare(Vertex v1, Vertex v2) {
        return v1.getName().compareTo(v2.getName());
	}

}
