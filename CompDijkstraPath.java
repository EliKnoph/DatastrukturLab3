
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class CompDijkstraPath <E extends Edge>{

    int from;
    int to;
    int cost;
    boolean [] visitedNodes;
    List<E> [] edgeList;



    public CompDijkstraPath(int from, int to, int cost, List<E> [] edgeList ){
        this.from = from;
        this.to = to;
        this.cost = cost;
        this.edgeList = edgeList;
    }


    public Iterator<E> shortestPath(int startNodeNumber, int endNodeNumber){ //kant from och to, index i en array

        boolean [] visitedNodes; //samlar alla besökta noder

        visitedNodes = new boolean[edgeList.length]; //sätter storleken på visitedNodes-arrayen, lika stor som antalet noder

        PriorityQueue<dNode> priorityQueue = new PriorityQueue<>(); //definierar priokön som ska bestå av nodes

        dNode currentNode = new dNode(startNodeNumber, 0, new ArrayList<>());
        priorityQueue.add(currentNode); //adderar noden du är på, kostnaden som är 0 och dess path

        while(!priorityQueue.isEmpty()){

            currentNode = priorityQueue.poll(); //sätter första noden

            if(!visitedNodes[currentNode.startNod]){ //om startnoden inte är besökt innan



                if(currentNode.startNod == endNodeNumber){
                    return currentNode.path.iterator();
                }else {

                    visitedNodes[currentNode.startNod] = true; //EL == efterföljarlista, v == väg, kant, v ?= vikt

                    for(E edge: this.edgeList[currentNode.startNod]){
                        if(!visitedNodes[edge.to]){
                            double totalDistance = currentNode.totalCost + edge.getWeight();
                            List <E> newPath = new ArrayList<E>(currentNode.path);
                            newPath.add(edge);
                            priorityQueue.add(new dNode(edge.to,totalDistance,newPath));
                        }
                    }
                }
            }
        }
        return null;

    }

    /*lägg (startnod, 0, tom väg) i en p-kö
    while p-kön inte är tom
        (nod, cost, path) = första elementet i p-kön
        if nod ej är besökt
            if nod är slutpunkt returnera path
        else
            markera nod besökt
            for every v on EL(nod)
        if v ej är besökt
            lägg in nytt köelement
                för v i p-kön*/


    class dNode implements Comparable<dNode>{

        int startNod; //the index of the node
        double totalCost; //the total cost of the path of this node
        List <E> path; //the path of the node

        public dNode(int startNod, double totalCost, List <E> path){

            this.startNod = startNod;
            this.totalCost = totalCost;
            this.path = path;
        }


        @Override
        public int compareTo(dNode o) {

            if(this.totalCost < o.totalCost){
                return -1;
            }else if(this.totalCost == o.totalCost){
                return 0;
            }
            return 1;
        }
    }
}

