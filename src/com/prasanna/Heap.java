/**
 * Author: Prasanna Lalingkar.
 * ID: 800936073.
 * 
 * This class contains the implementation for a min Heap.
 * We use this min heap as a priority queue in Dijkstra's Algorithm.
 */

package com.prasanna;

class Heap
{
    private Node[] heap; 					// Array of Nodes
    private int heapSize, capacity;			// Capacity and Size of the Heap

    /**
     * Constructor.
     * We do not use the 0th location.
     */
    public Heap(int capacity){    
        this.capacity = capacity + 1;
        heap = new Node[this.capacity];
        heapSize = 0;
    }
    public boolean isEmpty(){
        return heapSize == 0;
    }
    
    public void printValues(){
    	for(int i = 1; i<=this.heapSize; i++){
    		System.out.println(heap[i].getVertex().getName()+" "+heap[i].getTime());
    	}
    }

    public int left(int index){
    	return 2*index;
    }
    
    public int right(int index){
    	return (2*index)+1;
    }
    
    public int parent(int index){
    	return (index/2);
    }
    
    public boolean isFull(){
        return heapSize == capacity - 1;
    }
    
    public int size(){
        return heapSize;
    }

    public void insert(Node n){
    	heapSize++;
    	heap[heapSize] = n;
    }

    public void clear(){
        heap = new Node[capacity];
        heapSize = 0;
    }
    
    public void builMinHeap(){
    	for(int i = this.heapSize; i>=1; i--){
    		minHeapify(i);
    	}
    }
    
    public void minHeapify(int index){
    	int l = left(index);
    	int r = right(index);
    	int largest;
    	
    	if(l<=heapSize && heap[l].getTime() < heap[index].getTime()){
    		largest = l;
    	}
    	else{
    		largest = index;
    	}
    	if(r<=heapSize && heap[r].getTime() < heap[largest].getTime()){
    		largest = r;
    	}
    	if(largest != index){
    		Node temp = heap[index];
    		heap[index] = heap[largest];
    		heap[largest] = temp; 
    		minHeapify(largest);
    	}
    }
    
    public Node extraxtMin(){
    	if(heapSize<1){
    		System.out.println("Heap Underflow");
    	}
    	Node min = heap[1];
    	heap[1] = heap[heapSize];
    	heapSize--;
    	this.minHeapify(1);
    	return min;
    }
    
    public void decreasePriority(Vertex val, double priority){
    	Vertex vertex = null;
    	double oldPriority = 0;
    	int index = 0;
    	
    	for(int i=1; i<=heapSize; i++){
    		if(heap[i].getVertex().getName().equals(val.getName())){
    			vertex = heap[i].getVertex();
    			oldPriority = heap[i].getTime();
    			index = i;
    		}
    	}
    	
    	if(priority>oldPriority){
    		System.out.println("Priority value cannot be increases");
    	}
    	heap[index].setTime(priority);
    	
    	while(index > 1 && heap[parent(index)].getTime() >= heap[index].getTime()){
    		Node temp = heap[index];
    		heap[index] = heap[parent(index)];
    		heap[parent(index)] = temp;
    		index = parent(index);
    	}	
	}	
}
