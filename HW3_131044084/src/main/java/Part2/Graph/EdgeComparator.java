package Part2.Graph;

import java.util.Comparator;

public class EdgeComparator {

    /** A comparator class for comparing two edges */
    public static class CompareEdges implements Comparator<Edge> {
        public int compare(Edge edge1, Edge edge2){
            return Double.compare(edge1.getWeight(), edge2.getWeight());
        }
    }
}
