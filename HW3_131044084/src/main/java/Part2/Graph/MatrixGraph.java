package Part2.Graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixGraph implements GraphInterface, Serializable {

    /** The number of vertices */
    private int verticesNumber;

    /** The edges stored in matrix */
    private double[][] edges;


    /** Constructs a graph with the specified number of vertices
     @param verticesNumber The number of vertices
     */
    public MatrixGraph(String inputFileName) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(inputFileName));

        // Read vertices information data from input file
        verticesNumber = getVerticesNumberFromFile(input);

        edges = new double[verticesNumber][];
        // Set infinity to all edges as initialization
        for(int i=0; i < verticesNumber; ++i){
            edges[i] = new double[verticesNumber];
            for(int j=0; j < verticesNumber; ++j){
                edges[i][j] = Double.POSITIVE_INFINITY;
            }
        }

        // Read edges information data from input file
        this.loadEdgesFromFile(input);
        Edge.resetID();
    }

    public void printWeightMatrix(){
        for(int i=0; i < edges.length; ++i){
            for(int j=0; j < edges.length; ++j){
                System.out.print(edges[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /** An iterator to the edges.  An Edge iterator is
     *  similar to an Iterator except that its
     *  next method will always return an edge
     */
    private class GraphIterator implements Iterator <Edge>{
        /** The source vertex for this iterator */
        private final int source;

        /** The current index for this iterator */
        private int index;

        /** Constructs an EdgeIterator for a given vertex
         *  @param source - The source vertex
         */
        public GraphIterator(int source){
            this.source = source;
            index = -1;
            advanceIndex();
        }

        /** Returns true if there are more edges
         *  @return true if there are more edges
         */
        public boolean hasNext(){
            return index != getVerticesNumber();
        }

        /** Returns the next edge if there is one
         *  @throws NoSuchElementException - there are no
         *  more edges
         *  @return the next Edge in the iteration
         */
        public Edge next(){
            if(index == getVerticesNumber()){
                throw new java.util.NoSuchElementException();
            }
            Edge returnValue = new Edge(source, index, getEdgeValue(source, index));
            advanceIndex();
            return returnValue;
        }

        /** Remove is not implemented
         *  @throws UnsupportedOperationExeption if called
         */
        public void remove(){
            throw new UnsupportedOperationException();
        }

        /** Advance the index to the next edge */
        private void advanceIndex(){
            do{
                index++;
            }
            while(index != getVerticesNumber() && Double.POSITIVE_INFINITY == getEdgeValue(source, index));
        }
    }

    /** Returns an iterator to the edges connected
     to a given vertex.
     @param source - The source vertex
     @return an EdgeIterator to the vertices
     connected to source
     */
    public Iterator<Edge> edgeIterator(int source){
        return new GraphIterator(source);
    }

    /** Returns the number of vertices from the data in an input file
     * @param bR - Buffered Reader Object
     * @return the number of the vertices in an input file
     * */
    private int getVerticesNumberFromFile(BufferedReader bR) throws IOException {
        int verticesNumber = 0;

        while(true)
        {
            String lineFromFile = bR.readLine();
            // If the file format is wrong
            if(lineFromFile == null){
                throw new IOException();
            }

            // Count every vertex
            if(lineFromFile.contains("<Vertex"))
                ++verticesNumber;

            // End of the vertices
            if(lineFromFile.contains("</Vertices>"))
                break;
        }

        return verticesNumber;
    }

    /** Method to set the value of an edge
     @param source - The source vertex
     @param dest - The destination vertex
     @param weight - The weight of the edge
     */
    private void setEdgeValue(int source, int dest, double weight) {
        edges[source][dest] = weight;
    }

    /** Returns the weight value of an edge
     *  @param source - The source vertex
     *  @param dest - The destination vertex
     *  @return The weight of this edge or
     *  POSITIVE_INFINITY if no edge exists
     */
    public double getEdgeValue(int source, int dest) {
        return edges[source][dest];
    }

    /** Determine if an edge exists
     @param source - The source vertex
     @param dest - The destination vertex
     @return true if there is an edge from source to dest
     */
    public boolean isEdge(int source, int dest) {
        if(source < 0 || source >= this.getVerticesNumber())
            return false;

        if(dest < 0 || dest >= this.getVerticesNumber())
            return false;

        return edges[source][dest] != Double.POSITIVE_INFINITY;
    }

    /** Insert a new edge into the graph
     @param edge - The new edge
     */
    public void insertEdge(Edge edge){
        edges[edge.getSource()][edge.getDest()] = edge.getWeight();
    }

    /** Returns the edge between two vertices.
     @param source - The source
     @param dest - The destination
     @return the edge between these two vertices
     */
    public Edge getEdge(int source, int dest){
        if(isEdge(source, dest))
            return new Edge(source, dest, edges[source][dest]);

        /* Target not found, return an edge with infinity weight */
        return new Edge(source, dest, Double.POSITIVE_INFINITY);
    }

    public double[][] getMatrix(){
        return this.edges;
    }

    /** Load the edges of a graph from the data in an input file.
     The file should contain a series of lines, each line
     with two or three data values. The first is the source,
     the second is the destination, and the third is the weight.
     @param bR The buffered reader containing the data
     @throws IOException if an I/O error occurs
     */
    public void loadEdgesFromFile(BufferedReader bR) throws IOException {
        int source = -1, dest = -1;
        double weight = Double.POSITIVE_INFINITY;
        int found, start, end;

        /** The next line after reading the vertices must contain <Edges>
         *  which indicates the starting point of the edge information data
         */
        String lineFromFile = bR.readLine();
        if(!lineFromFile.contains("<Edges>")){
            throw new IOException();
        }

        while(true)
        {
            lineFromFile = bR.readLine();
            // If the file format is wrong
            if(lineFromFile == null){
                throw new IOException();
            }

            if(lineFromFile.contains("<Edge"))
            {
                // Read the source vertex information
                found = lineFromFile.indexOf("V1=\"v");
                start = found + 5;
                end = lineFromFile.indexOf("\"", start);
                if(found != -1 && end != -1)
                    source = Integer.parseInt(lineFromFile.substring(start, end));

                // Read the destination vertex information
                found = lineFromFile.indexOf("V2=\"v");
                start = found + 5;
                end = lineFromFile.indexOf("\"", start);
                if(found != -1 && end != -1)
                    dest = Integer.parseInt(lineFromFile.substring(start, end));

                // Read the weight information of the edge
                found = lineFromFile.indexOf("Weight=\"");
                start = found + 8;
                end = lineFromFile.indexOf("\"", start);
                if(found != -1 && end != -1)
                    weight = Double.parseDouble(lineFromFile.substring(start, end));

                //System.out.println("S: " + source + " " + "D: " + dest + " " + "W: " + weight);
                this.insertEdge(new Edge(source-1, dest-1, weight));
            }

            if(lineFromFile.contains("</Edges>"))
                break;
        }
    }

    /** Returns the number of vertices.
     @return The number of vertices
     */
    public int getVerticesNumber(){ return verticesNumber; }

}
