package shortestPath;

import java.util.ArrayList;
import java.util.LinkedList;


public class Graph {
	ArrayList<LinkedList<Node>> adjacencyList;
	Graph(){
		this.adjacencyList=new ArrayList<>();
	}
	
	public void addNode(Node node) {
		LinkedList<Node> currentList = new LinkedList<>();
		currentList.add(node);
		this.adjacencyList.add(currentList);
	}
	
	public void addEdge(int src, int dest) {
		LinkedList<Node> currentList = this.adjacencyList.get(src);
		Node dstNode = this.adjacencyList.get(dest).get(0);
		currentList.add(dstNode);
	}
	
	public boolean checkEdge(int src, int dest) {
		LinkedList<Node> currentList = this.adjacencyList.get(src);
		Node dstNode = this.adjacencyList.get(dest).get(0);
		
		for(Node node: currentList) {
			if(node==dstNode)
				return true;
		}
		return false;
	}
	
	public void print() {
		for(LinkedList<Node> list : this.adjacencyList)
		{
			for(Node node: list)
			{
				System.out.print(node.name+" -> ");
			}
			System.out.println();
		}
	}
	
	public ArrayList<LinkedList<Node>> getArrayList()
	{
		return this.adjacencyList;
	}
}
