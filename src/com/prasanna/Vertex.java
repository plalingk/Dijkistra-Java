/**
 * Author: Prasanna Lalingkar.
 * ID: 800936073.
 * 
 * This class defines a vertex and its necessary methods.
 * Contains constructors and getter setters.
 */

package com.prasanna;



import java.util.List;
import java.util.LinkedList;



public class Vertex {
	private String vertexName;   		// Vertex name
	private List<Vertex> adjacent;    	// Adjacent vertices
	private List<Edge> edges;			// List of edges from the vertex
	private float timeTaken = Float.MAX_VALUE;			// Time to get from vertex
	private String status;			// Status of the vertex (up or down)

	public Vertex(String name){ 
		vertexName = name; 
		adjacent = new LinkedList<Vertex>( );
		edges = new LinkedList<Edge>( );
		status = "up";
	}
	
	public String getName(){
		return this.vertexName;
	}
	
	public float getTimeTaken(){
		return timeTaken;
	}
	
	public void setTimeTaken(float time){
		this.timeTaken = time;
	}
	
	public void setVertexDown(){
		this.status="down";
	}
	
	public void setVertexUp(){
		this.status = "up";
	}
	
	public void addAdjacent(Vertex v){
		this.adjacent.add(v);
	}
	
	public void addEdge(Edge e){
		this.edges.add(e);
	}
	
	public List<Edge> getEdgeList(){
		return this.edges;
	}
	
	public void deleteEdge(Edge e){
		edges.remove(edges.indexOf(e));
	}
 
	public List<Vertex> getVertexList(){
		return this.adjacent;
	}
	
	public String getVertexStatus(){
		return this.status;
	}
	
}
