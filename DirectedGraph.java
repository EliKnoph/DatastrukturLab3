import java.util.PriorityQueue;
import java.util.*;

public class DirectedGraph<E extends Edge> {

	private List <E> [] edgeList; //en arraylist

	private int numberOfNodes;


	public DirectedGraph(int numberOfNodes) {

		edgeList = new List[numberOfNodes]; //en list-array som representerar varje nod i form av index
		this.numberOfNodes = numberOfNodes;

		for(int i = 0; i < numberOfNodes; i++){ //för varje nod (index) skapas en linked list där kanterna ska vara
			edgeList[i] = new LinkedList<>();
		}

	}

	public void addEdge(E e) {

		if (e != null) {

			edgeList[e.getSource()].add(e); //adderar edgen på från-noden
		}

	}

	public Iterator<E> shortestPath(int startNode, int endNode){ //kant from och to, index i en array

		CompDijkstraPath<E> comp = new CompDijkstraPath<>(startNode, endNode, 0, edgeList);

		return comp.shortestPath(startNode, endNode);
	}
		
	public Iterator<E> minimumSpanningTree() {

		CompKruskalEdge<E> kruskal = new CompKruskalEdge<>(edgeList, numberOfNodes);

		return kruskal.minimumSpanningTree();
	}

}
  
