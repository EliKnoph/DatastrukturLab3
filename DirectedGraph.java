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
		List <E> [] cc = new List[numberOfNodes];   			//Skapar fältet cc
		PriorityQueue<E> edges = new PriorityQueue<>(100,new CompKruskalEdge());		//Skapar en pq
		for(int i = 0; i < cc.length; i++){						//Lägger in en tom lista på varje nod i cc
			cc[i] = new ArrayList<>();
		}
		for(int i = 0; i < edgeList.length; i++){				//Lägger in alla bågar i pq
			edges.addAll(edgeList[i]);
		}
		while(!edges.isEmpty() && cc.length > 1){				//Så länge pq inte är tom och och cc > 1
			E e = edges.poll();									//Hämtar e från kön
			if(cc[e.from] != cc[e.to]){							//Om from och  to inte är samma lista
				if(cc[e.from].size() > cc[e.to].size()){		//Flyttar alla element från kortare listan till den längre
					for(int i = 0; i < cc[e.to].size(); i++){
						E currentEdge = cc[e.to].get(i); 		//byt namn
						cc[e.from].add(currentEdge);
						cc[currentEdge.from] = cc[e.from];
						cc[currentEdge.to] = cc[e.to];

					}
					cc[e.from] = cc[e.to];						//Lägger e i den påfyllda listan
				}
			else if(cc[e.to].size() > cc[e.from].size()){
				for(int i = 0; i < cc[e.from].size(); i++){
					E currentEdge = cc[e.from].get(i);
					cc[e.to].add(currentEdge);				//Va from här innan, funkar likadant, fett weird
					cc[currentEdge.to] = cc[e.to];
					cc[currentEdge.from] = cc[e.from];
				}
				cc[e.to] = cc[e.from];
			}
			}
		}


		return cc[0].iterator();
	}

	/*
	public void add(Element element){

	}*/

	private class CompKruskalEdge implements Comparator<E>{

		@Override
		public int compare(E o1, E o2) {
			if(o1.getWeight() < o2.getWeight()){
				return -1;
			}else if(o1.getWeight() == o2.getWeight()){
				return 0;
			}
			return 1;
		}
	}
}
  
