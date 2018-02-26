
import java.util.*;

/**
 * A class containing the objects of computation adn redirection of shortest path and minimum spanning tree.
 * The nodes are represented as indexes in an array, and every index is a list object.
 * @author Eli Knoph & Linnéa Bark
 * */

public class DirectedGraph<E extends Edge> {

	private List <E> [] edgeList; //list array

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
  
