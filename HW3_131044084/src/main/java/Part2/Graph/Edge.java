package Part2.Graph;

public class Edge {

    /** The source vertex */
    private int source;
    /** The destination vertex */
    private int dest;
    /** The weight of this edge */
    private double weight;
    /** The id of this edge */
    private int edge_id;
    /** static counter to assign different ID's to each edge */
    private static int idCounter = 0;

    /** Constructs an Edge with a source of from
     and a destination of to. Set the weight
     to 1.0.
     @param source - The source vertex
     @param dest - The destination vertex
     */
    public Edge(int source, int dest){
        this.source = source;
        this.dest = dest;
        this.weight = 1.0;
        edge_id = idCounter++;
    }

    /** Constructs a weighted edge with a source
     of from and a destination of to. Set the
     weight to w.
     @param source - The source vertex
     @param dest - The destination vertex
     @param weight - The weight of the edge
     */
    public Edge(int source, int dest, double weight){
        this.source = source;
        this.dest = dest;
        this.weight = weight;
        edge_id = idCounter++;
    }

    /** Get the source
     @return The value of source vertex
     */
    public int getSource(){ return source; }

    /** Get the destination
     @return The value of destination vertex
     */
    public int getDest(){ return dest; }

    /** Get the weight
     @return The value of the edge's weight
     */
    public double getWeight(){ return weight; }

    /** Get the edge id
     @return The value of the edge's id
     */
    public int getID(){ return edge_id; }

    public static void resetID(){ idCounter = 0; }

    /** Returns a String representation of the edge
     @return A String representation of the edge
     */
    public String toString(){
        String result = "ID: " + edge_id + ", Source: " + source + ", Destination: " + dest + ", Weight: " + weight;
        return result;
    }

    /** Returns true if two edges are equal.
     @param o The object to compare to
     @return true if the edges have the same source
     and destination
     */
    public boolean equals(Object o){
        if(this == o)
            return true;

        if(o == null || !(o instanceof Edge))
            return false;

        Edge other = (Edge)o;
        return ((this.source == other.source) && (this.dest == other.dest));
    }

}
