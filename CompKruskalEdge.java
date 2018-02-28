import java.util.*;

/**
 * A class consisting of computing the minimum spanning tree according to Kruskal's algorithm.
 *
 * @author Eli Knoph & Linnéa Bark
 * */

public class CompKruskalEdge <E extends Edge> implements Comparator<E>{

    private List <E> [] edgeList; //en arraylist

    private int numberOfNodes;

    public CompKruskalEdge(List <E> [] edgeList, int numberOfNodes){

        this.edgeList = edgeList;
        this.numberOfNodes = numberOfNodes;
    }

    public Iterator<E> minimumSpanningTree() {

        List<E>[] cc = new List [numberOfNodes];   			//Skapar fältet cc
        PriorityQueue<E> edges = new PriorityQueue<E>(numberOfNodes,new CompKruskalEdge <E> (edgeList,numberOfNodes));		//Skapar en pq

        for(int i = 0; i < numberOfNodes; i++){						//Lägger in en tom lista på varje nod i cc
            cc[i] = new ArrayList<>();
        }

        for (List<E> anEdgeList : edgeList) {                //Lägger in alla bågar i pq
            edges.addAll(anEdgeList);
        }

        while(!edges.isEmpty() && cc.length > 1){				//Så länge pq inte är tom och och cc > 1
            E e = edges.poll();									//Hämtar e från kön
            if(cc[e.from] != cc[e.to]){							//Om from och  to inte är samma lista
                if(cc[e.from].size() > cc[e.to].size()){		//Flyttar alla element från kortare listan till den längre
                    for(E edge : cc[e.to]){
                        //E currentEdge = cc[e.to].get(i); 		//byt namn
                        cc[e.from].add(edge);
                        cc[edge.from] = cc[e.from];
                        cc[edge.to] = cc[e.from];

                    }
                    cc[e.to] = cc[e.from];						//Lägger e i den påfyllda listan
                }
                else{
                    for(E edge : cc[e.from]){
                       // E currentEdge = cc[e.from].get(i);
                        cc[e.to].add(edge);				//Va from här innan, funkar likadant, fett weird
                        cc[edge.from] = cc[e.to];
                        cc[edge.to] = cc[e.to];
                    }
                    cc[e.from] = cc[e.to];
                }
                cc[e.from].add(e);
            }
        }
        return cc[0].iterator();
    }

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
