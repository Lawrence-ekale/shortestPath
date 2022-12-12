package shortestPath;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {
	public static void main(String[] args) throws Exception {
		
		Graph graph = new Graph();
		int V = 0;//total number of nodes
		
		try {
			File inputFile = new File("src/inputs/infile-3.txt");
			try (BufferedReader buff = new BufferedReader(new FileReader(inputFile))) {
				String st;
				while ((st=buff.readLine()) != null)
				{
					graph.addNode(new Node(st,V));
					V++;
				}
			}
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
		ArrayList<LinkedList<Node>> toAddEdges = graph.getArrayList();
		
		for (int i = 0; i < toAddEdges.size(); i++)
		{
			for(int j = 0; j < toAddEdges.size(); j++)
			{
				if(i != j)
				{
					String nodeName = toAddEdges.get(i).get(0).name;
					char lastChar = nodeName.charAt(nodeName.length()-1);
					
					if(lastChar == toAddEdges.get(j).get(0).name.charAt(0)) {
						graph.addEdge(i, j);
					}
				}
			}
		}
		
		
		//graph.print();
		tellTheShortestDistance(graph.getArrayList(),0,V-1,V);

	}

	private static void tellTheShortestDistance(ArrayList<LinkedList<Node>> arrayList, int source, int destination, int v) {
		int predecessor[] = new int[v];
		int distance[] = new int[v];
		
		if(BFS(arrayList,source,destination,v,predecessor,distance)==false)
		{
			System.out.println(0);
			return ;
		}
		
		//Path stored in an LinkedList
		LinkedList<Integer> path = new LinkedList<Integer>();
		
		int goBackward = destination;
		path.add(goBackward);
		
		while(predecessor[goBackward] != -1) {
			path.add(predecessor[goBackward]);
			goBackward = predecessor[goBackward];
		}
		
		//To print the path but it is in indices , you can place here.(path)
		
		System.out.println((distance[destination]+1));
		
	}

	private static boolean BFS(ArrayList<LinkedList<Node>> arrayList, int source, int destination, int v,
			int[] predecessor, int[] distance) {
		LinkedList<Integer> queue = new LinkedList<Integer>();//This queue stores the indices
		boolean visited[] = new boolean[v];
		for(int i = 0; i < v; i++)
		{
			visited[i] = false;
			distance[i] = Integer.MAX_VALUE;
			predecessor[i] = -1;
		}
		
		//Mark the source first then set its distance to 0
		visited[source] = true;
		distance[source] = 0;
		
		queue.add(source);
		
		//Let us do a Breadth First Search algorithm
		while(!queue.isEmpty()) {
			int u = queue.remove();
			for(int i = 0; i < arrayList.get(u).size(); i++) {
				if(visited[arrayList.get(u).get(i).index] == false) {
					visited[arrayList.get(u).get(i).index] = true;
					distance[arrayList.get(u).get(i).index] = distance[u] +1;
					predecessor[arrayList.get(u).get(i).index] = u;
					queue.add(arrayList.get(u).get(i).index);
					
					if(arrayList.get(u).get(i).index == destination) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	

}
