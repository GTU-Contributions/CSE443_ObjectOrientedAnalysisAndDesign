package Part2.Graph;

import java.util.*;

public class _MinimumSpanningTree {

    /** Prim's Minimum Spanning Tree algorithm.
     @param graph The weighted graph to be searched
     @param start The start vertex
     @return An ArrayList of edges that forms the MST
     */
    private static ArrayList<Edge> find(MatrixGraph graph, int start){
        ArrayList<Edge> result = new ArrayList<Edge>();
        int verticesNumber = graph.getVerticesNumber();

        // Use a HashSet to represent V-S.
        Set<Integer> vMinusS = new HashSet<Integer>(verticesNumber);

        // Declare the priority queue.
        Queue<Edge> pQ = new PriorityQueue<Edge>(verticesNumber, new EdgeComparator.CompareEdges());

        // Initialize V-S.
        for(int i=0; i < verticesNumber; i++){
            if(i != start){
                vMinusS.add(i);
            }
        }

        int current = start;
        // Main loop
        while (!vMinusS.isEmpty()){
            // Update priority queue.
            Iterator<Edge> iter = graph.edgeIterator(current);

            while (iter.hasNext()){
                Edge edge = iter.next();
                int dest = edge.getDest();
                if (vMinusS.contains(dest)) {
                    pQ.add(edge);
                }
            }

            // Find the shortest edge whose source is in S and
            // destination is in V-S.
            int dest;
            Edge edge;
            do {
                edge = pQ.remove();
                dest = edge.getDest();
            }
            while(!vMinusS.contains(dest));

            // Take dest out of vMinusS.
            vMinusS.remove(dest);

            // Add edge to result.
            result.add(edge);

            // Make this the current vertex.
            current = dest;
        }

        return result;
    }

    public static String getMST(MatrixGraph graph, int start){
        ArrayList<Edge> edgeArray = find(graph, start);

        // Build the string format of the Incedence Matrix
        StringBuilder result = new StringBuilder();

        for(int i=0; i<edgeArray.size(); ++i){
            result.append(edgeArray.get(i).getID()+1);
            result.append(" ");
        }

        return result.toString();
    }
}
