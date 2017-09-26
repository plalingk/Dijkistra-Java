/**
 * Author: Prasanna Lalingkar.
 * ID: 800936073.
 * 
 * This class defines an Edge and its necessary methods.
 * Contains constructors and getter setters.
 */

package com.prasanna;

public class Edge {
	
	private Vertex fromVertex, toVertex;
	private float time;
	private String status; 
	
	public Edge(Vertex from, Vertex to, float wt){
		fromVertex = from;			// The source vertex in the edge
		toVertex = to;				// The destination vertex in the edge
		time = wt;					// Time taken for data packet to travel that edge
		status = "up";				// Status of the Edge
	}
	
	public float getEdgeweight(){
		return this.time;
	}
	
	public String getStatus(){
		return this.status;
	}
	
	public Vertex getToVertex(){
		return this.toVertex;
	}
	
	public Vertex getFromVertex(){
		return this.fromVertex;
	}
	
	public void setTime(float t){
		this.time = t;
	}
	
	public void setStatus(String status){
		this.status = status;
	}	

}
