/**
 * Author: Prasanna Lalingkar.
 * ID: 800936073.
 * 
 * This class defines a graph and its necessary methods.
 * Contains constructors, getter, setters and all relevant
 * functions for operations on the graph.
 */

package com.prasanna;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;



public class Graph{
	
	private List<Vertex> V;			// Vertices in the Graph
	private List<Edge> E;			// Edges in the Graph
	
	/**
	 * Graph Constructor
	 */
	public Graph(){
		V = new ArrayList<Vertex>( );
		E = new ArrayList<Edge>( );
	}
	
	/**
	 * Add a new edge to the graph.
	 */
	public void addEdge( String sourceName, String destName, float t )
	{
		int flagEdgeExists = 0;
		for(Edge e : this.E){
			if(e.getFromVertex().getName().equals(sourceName) && e.getToVertex().getName().equals(destName)){
				flagEdgeExists = 1;
				e.setTime(t);
			}
		}
		if(flagEdgeExists==0){
			Vertex v = getVertex(sourceName);
			Vertex w = getVertex( destName );
			Edge e = new Edge(v,w,t);
			E.add(e);
			v.addAdjacent(w);
			v.addEdge(e);
		}
	}
	
	/**
	 * Add a new bidirectional edge to the graph - during graph creation.
	 */
	public void addEdge(String sourceName, String destName, float t, boolean flag )
	{
		Vertex v = this.getVertex(sourceName);
		Vertex w = getVertex( destName );
		Edge e = new Edge(v,w,t);
		this.E.add(e);
		Edge e1 = new Edge(w,v,t);
		this.E.add(e1);
		v.addAdjacent(w);
		v.addEdge(e);
		w.addAdjacent(v);
		w.addEdge(e1);
	}

	/**
	 * If vertexName is not present, add it to vertexList.
	 * In either case, return the Vertex.
	 */
	public Vertex getVertex(String vertexName)
	{
		Vertex v = null;
		for(Vertex vertex : V){
			if(vertexName.equals(vertex.getName())){
				v = vertex;
			}		
		}
		
		if( v == null ){
			v = new Vertex(vertexName);
			V.add(v);
		}
		return v;
	}
	
	/**
	 * Get the corresponding edge if present in the graph
	 */
	public Edge getEdge(Vertex fromVertex, Vertex toVertex)
	{
		Edge ed = null;
		for(Edge e : E){
			if(fromVertex.getName().equals(e.getFromVertex().getName()) && toVertex.getName().equals(e.getToVertex().getName())){
				ed = e;
			}		
		}
		
		if( ed == null ){
			System.out.println("Edge does not exist");
			writer.println("Edge does not exist");
		}
		return ed;
	}
	
	/**
	 * Check if an edge is present, if yes get its status
	 */
	public String getEdgeStatus(Vertex fromVertex, Vertex toVertex)
	{
		Edge ed = null;
		for(Edge e : E){
			if(fromVertex.getName().equals(e.getFromVertex().getName()) && toVertex.getName().equals(e.getToVertex().getName())){
				ed = e;
			}		
		}
		
		if( ed == null ){
			return "not exists";
		}
		return ed.getStatus();
	}
	
	/**
	 * Set the status of the Edge as up
	 */
	public void edgeUp(String sourceName, String destName) {
		for(Edge e : this.E){
			if(e.getFromVertex().getName().equals(sourceName) && e.getToVertex().getName().equals(destName)){
				e.setStatus("up");
			}
		}
	}

	/**
	 * Set the status of the Edge as down
	 */
	public void edgeDown(String sourceName, String destName) {
		for(Edge e : this.E){
			if(e.getFromVertex().getName().equals(sourceName) && e.getToVertex().getName().equals(destName)){
				e.setStatus("down");
			}
		}
	}

	/**
	 * Set the status of the Vertex as up
	 */
	public void vertexUp(String vertexName) {
		for(Vertex v : this.V){
			if(v.getName().equals(vertexName)){
				v.setVertexUp();
			}
		}
	}

	/**
	 * Set the status of the Vertex as down
	 */
	public void vertexDown(String vertexName) {
		for(Vertex v : this.V){
			if(v.getName().equals(vertexName)){
				v.setVertexDown();
			}
		}		
	}

	/**
	 * Delete Edge
	 */
	public void deleteEdge(String sourceName, String destName) {
		for(Edge e : this.E){
			if(e.getFromVertex().getName().equals(sourceName) && e.getToVertex().getName().equals(destName)){
				e.getFromVertex().deleteEdge(e);
				this.E.remove(this.E.indexOf(e));
				e=null;
				break;
			}
		}
	}
	
	/**
	 * Used to print reachable vertices. Modified version of DFS
	 */
	public static HashMap<Vertex,String> col;
	public static List<Vertex> names;
	public static PrintWriter writer;

	public void printReachable(){
		Collections.sort(names, new VertexComparator());		
		for(Vertex v : names){
			System.out.println("  "+v.getName());
			writer.println("  "+v.getName());
		}
	}	
	
	public void reachable(Vertex v){
	      col.put(v, "grey");
	      for(Vertex x : this.V){
	         if(x.getVertexStatus().equals("up") && col.get(x).equals("white")){
	        	 col.put(x, "grey");
	        	 names.add(x);
	        	 reachable(x);
	         }
	      }
	   }
	
	/**
	 * Dijktra's Shortest Path Algorithm from a source to the given destination 
	 */
	private void pathDijkstra(String source, String destination) {
		Vertex start = null, dest= null;
		HashMap<Vertex, Double> distance = new HashMap<>();
		HashMap<Vertex, Vertex> predecessor = new HashMap<>();
		HashSet<Vertex> visited = new HashSet<Vertex>();
		HashSet<Vertex> unvisited = new HashSet<Vertex>(V);
		Heap pq = new Heap(V.size());

		for(Vertex v : this.V){
			distance.put(v, Double.MAX_VALUE);
			pq.insert(new Node(v, Double.MAX_VALUE));
			predecessor.put(v, null); 	
			if(v.getName().equals(source)){
				start = v;
			}
			if(v.getName().equals(destination)){
				dest = v;
			}
		}
		distance.put(start,0.0);
		pq.decreasePriority(start,0);
		visited.add(start);
		unvisited.remove(start);

		Vertex u=null;
		List<Edge>edg = new LinkedList<Edge>( );
		
		// While the Priority Queue is not Empty
		while(!pq.isEmpty()){
			u = pq.extraxtMin().getVertex();
			edg = u.getEdgeList();
			double dist;
			// Check status of vertices and edges
			for(Vertex v : u.getVertexList()){
				if(v.getVertexStatus().equals("up") && u.getVertexStatus().equals("up") && unvisited.contains(v) && this.getEdgeStatus(u,v).equals("up") ){
					if(distance.get(v)>distance.get(u)+getEdge(u,v).getEdgeweight()){
						dist = distance.get(u)+getEdge(u,v).getEdgeweight();
						distance.put(v,dist);
						pq.decreasePriority(v,dist);
						predecessor.put(v, u);
					}
				}
			}
			visited.add(u);
			unvisited.remove(u);		
		}
		ArrayList<String> names = new ArrayList<String>();
		names.add(dest.getName());
		double dist = distance.get(dest);
		while(predecessor.get(dest).getName()!= start.getName()){
			dest = predecessor.get(dest);
			names.add(dest.getName());
		}
		names.add(start.getName());
		for(int i=names.size()-1; i>=0; i--){
			System.out.print(names.get(i)+" ");
			writer.print(names.get(i)+" ");
		}
		System.out.println(String.format("%.2f",dist));		
		writer.println(String.format("%.2f",dist));
	}
	
	/**
	 * Print the Graph in the required pattern
	 */
	public void printGraph(){
		List<Vertex> tempV = new ArrayList<Vertex>(V);
		Collections.sort(tempV, new VertexComparator());
		for(int i=0; i<tempV.size(); i++){
			if(tempV.get(i).getVertexStatus().equals("down")){
				System.out.print(tempV.get(i).getName());
				writer.print(tempV.get(i).getName());
				System.out.println(" DOWN");
				writer.println(" DOWN");
			}
			else{
				System.out.println(tempV.get(i).getName());
				writer.println(tempV.get(i).getName());
			}
			List<Edge> ed = new ArrayList<Edge>(tempV.get(i).getEdgeList());
			Collections.sort(ed, new EdgeComparator());
			for(int j = 0; j<ed.size(); j++){
				if(ed.get(j).getStatus().equals("down")){
					System.out.print("  "+ed.get(j).getToVertex().getName()+" "+String.format("%.2f",ed.get(j).getEdgeweight()));
					writer.print("  "+ed.get(j).getToVertex().getName()+" "+String.format("%.2f",ed.get(j).getEdgeweight()));
					System.out.println(" DOWN");
					writer.println(" DOWN");
				}
				else{
					System.out.println("  "+ed.get(j).getToVertex().getName()+" "+ed.get(j).getEdgeweight());
					writer.println("  "+ed.get(j).getToVertex().getName()+" "+ed.get(j).getEdgeweight());
				}
			}
		}		
	}

	
	public static void main( String [ ] args ) throws FileNotFoundException, UnsupportedEncodingException
	{
		Graph g = new Graph( );
		FileReader fin = new FileReader( args[0] );
		Scanner graphFile = new Scanner( fin );
		writer = new PrintWriter("output.txt", "UTF-8");
		
		// Read the edges and insert
		String line;
		while( graphFile.hasNextLine( ) )
		{
			line = graphFile.nextLine( );
			StringTokenizer st = new StringTokenizer( line );
			if( st.countTokens( ) != 3 ){
				System.err.println( "Skipping ill-formatted line " + line );
				continue;
			}
			String source  = st.nextToken( );
			String dest    = st.nextToken( );
			float time = Float.parseFloat(st.nextToken());
			g.addEdge(source,dest,time,true);
		}
		graphFile.close();

		// Read and work on the queries file
		System.out.println("Graph created from given input file.");
		System.out.println("Enter command for the graph: ");		
		Scanner keyboard = new Scanner(System.in);
		String comm;
		while( keyboard.hasNextLine( ) )
		{
			line = keyboard.nextLine( );
			StringTokenizer st = new StringTokenizer( line );
			comm = st.nextToken();
			switch(comm){
			case "path":
				if(st.countTokens() == 2){
					g.pathDijkstra(st.nextToken(),st.nextToken());
					System.out.println(" ");
					writer.println(" ");
				}
				else{
					System.out.println("Enter correct query format");
				}
				break;
				
			case "reachable":
				List<Vertex> tempV = new ArrayList<Vertex>(g.V);
				Collections.sort(tempV, new VertexComparator());
				for(Vertex v: tempV){
					if(v.getVertexStatus().equals("up")){
						col = new HashMap<Vertex,String>(g.V.size());
						names = new ArrayList<Vertex>(g.V.size());
						for(Vertex w : g.V){
							col.put(w, "white");
						}
						System.out.println(v.getName());
						writer.println(v.getName());
						g.reachable(v);	
						g.printReachable();
					}
				}
				System.out.println("");
				writer.println(" ");
				break;
				
			case "addedge":
				if(st.countTokens() == 3){
					g.addEdge(st.nextToken(),st.nextToken(),Float.parseFloat(st.nextToken()));
				}
				else{
					System.out.println("Enter correct query format");					
				}
				break;
				
			case "deleteedge":
				if(st.countTokens() == 2){
					g.deleteEdge(st.nextToken(),st.nextToken());
				}
				else{
					System.out.println("Enter correct query format");					
				}
				break;
				
			case "edgedown":
				if(st.countTokens() == 2){
					g.edgeDown(st.nextToken(),st.nextToken());
				}
				else{
					System.out.println("Enter correct query format");					
				}
				break;
				
			case "edgeup":
				if(st.countTokens() == 2){
					g.edgeUp(st.nextToken(),st.nextToken());
				}
				else{
					System.out.println("Enter correct query format");					
				}
				break;
				
			case "vertexdown":
				if(st.countTokens() == 1){
					g.vertexDown(st.nextToken());
				}
				else{
					System.out.println("Enter correct query format");					
				}
				break;
				
			case "vertexup":
				if(st.countTokens() == 1){
					g.vertexUp(st.nextToken());
				}
				else{
					System.out.println("Enter correct query format");					
				}
				break;
				
			case "print":
				g.printGraph();
				System.out.println(" ");
				writer.println(" ");
				break;
				
			case "quit":
				System.exit(0);
				break;
				
			default:
				System.out.println("Please check the query formats and enter correct query");
				break;
			}
			System.out.println(" \n Enter query for the graph: ");
		}		
		keyboard.close();
		writer.close();
	}
}