
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class CompDijkstraPath <E extends Edge>{

    int from;
    int to;
    int cost;
    boolean [] visitedNodes;
    List<E> edgeList;


    public CompDijkstraPath(int from, int to, int cost, List<E> edgeList ){
        this.from = from;
        this.to = to;
        this.cost = cost;
        this.edgeList = edgeList;
    }


    public Iterator<E> shortestPath(int startNodeNumber, int endNodeNumber){ //kant from och to, index i en array

        visitedNodes = new boolean[edgeList.size()]; //sätter storleken på visitedNodes-arrayen

        PriorityQueue<node> priorityQueue = new PriorityQueue<>(); //definierar priokön som ska bestå av nodes

        priorityQueue.add(new node(startNodeNumber,0,null)); //adderar noden du är på, kostnaden


        node currentNode = new node(to, 0, visitedNodes);

        while(!priorityQueue.isEmpty()){


            if(!visitedNodes[currentNode.startNod]){

                currentNode = priorityQueue.poll(); //sätter första noden

                if(startNodeNumber == endNodeNumber){
                    return null;
                }

                visitedNodes[startNodeNumber] = true;
            }
        }

    }


    public class node {

        int startNod;
        double cost;
        boolean [] path;

        public node(int startNod, int cost, boolean [] booleanArray){

            this.startNod = startNod;
            this.cost = cost;
            this.path = booleanArray;
        }



    }
}

