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
            E e = edges.poll();                                 //Hämtar e från kön

            int from = e.from;
            int to = e.to;

            if(cc[from] != cc[e.to]){							//Om from och  to inte är samma lista
                if(cc[from].size() > cc[to].size()){		//Flyttar alla element från kortare listan till den längre
                    for(E edge : cc[to]){
                        cc[from].add(edge);
                        cc[edge.from] = cc[from];
                        cc[edge.to] = cc[from];

                    }
                    cc[to] = cc[from];
                }
                else{
                    for(E edge : cc[from]){
                        cc[to].add(edge);
                        cc[edge.from] = cc[to];
                        cc[edge.to] = cc[to];
                    }
                    cc[from] = cc[to];
                }
                cc[from].add(e);                          //Lägger e i den påfyllda listan
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
