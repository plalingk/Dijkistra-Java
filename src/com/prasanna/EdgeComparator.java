/**
 * Author: Prasanna Lalingkar.
 * ID: 800936073.
 * 
 * This class compares the edges by their names to sort them.
 */

package com.prasanna;


import java.util.Comparator;

public class EdgeComparator implements Comparator<Edge> {

	@Override
	public int compare(Edge e1, Edge e2) {
        return e1.getToVertex().getName().compareTo(e2.getToVertex().getName());
	}

}
