package Part2.Graph;

import java.util.Iterator;

public interface GraphInterface {

    /** Return the number of vertices.
     @return The number of vertices
     */
    int getVerticesNumber();

    /** Insert a new edge into the graph.
     @param edge The new edge
     */
    void insertEdge(Edge edge);

    /** Get the edge between two vertices.
     @param source The source vertex
     @param dest The destination vertex
     @return The Edge between these two vertices
     or an Edge with a weight of
     Double.POSITIVE_INFINITY if there is no edge
     */
    Edge getEdge(int source, int dest);

    /** Determine whether an edge exists.
     @param source The source vertex
     @param dest The destination vertex
     @return true if there is an edge from source to dest
     */
    boolean isEdge(int source, int dest);

    /** Return an iterator to the edges connected
     to a given vertex.
     @param source The source vertex
     @return An Iterator to the vertices
     connected to source
     */
    Iterator<Edge> edgeIterator(int source);
}
