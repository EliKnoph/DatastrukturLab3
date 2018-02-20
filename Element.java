import java.util.PriorityQueue;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Iterator;
//import java.io.FileNotFoundException;
import java.awt.Point;
import java.awt.Color;
import java.text.DecimalFormat;

public class Element {


    private static final double infinity = 1.0E300;




    public Element(Node node, int cost, Node [] path ){



    }

    private static class Node  implements Comparable<Node>{

        Point p = null;
        double imp = infinity;  //importance of the point
        int nbr = -1;   // original position in list, only for debug

        private Node next = null; // The link to the next node.
        private Node prev = null; // The link to the previous node.

        private Node( Point p, int nbr ) { // nbr stores the original position in list
            this.p = p;
            this.nbr = nbr;
        }

        public int compareTo( Node n ) {
            double diff= imp-n.imp;
            if (diff<0) { return -1;
            } else if (diff > 0) { return +1;
            } else { return 0; }
        }
    }
}
