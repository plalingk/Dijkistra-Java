/**
 * Author: Prasanna Lalingkar.
 * ID: 800936073.
 * 
 * This class defines a node to be used in the priority queue.
 * Contains the constructor and getter setter.
 */

package com.prasanna;

public class Node {

    Vertex vertex;
    double time;
 
    public Node(Vertex vertexName, double time)
    {
        this.vertex = vertexName;
        this.time = time; 
    }
    
    public void setTime(double time)
    {
        this.time = time; 
    }
    public double getTime()
    {
        return this.time; 
    }    
    public Vertex getVertex()
    {
        return this.vertex; 
    }    
    
}
