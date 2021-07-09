package Part2.Graph;

import java.util.ArrayList;

public class _IncedenceMatrix {

    private static int[][] find(MatrixGraph graph){
        // Get the number of vertices in the graph
        int verticesNumber = graph.getVerticesNumber();

        // Get the graph in maxtrix format
        double[][] edges = graph.getMatrix();

        // Allocate space for Incidence Matrix
        int[][] incidenceMatrix = new int[verticesNumber][];
        for(int i=0; i < verticesNumber; ++i){
            incidenceMatrix[i] = new int[verticesNumber];
        }

        // Evaluate the Incedence Matrix
        for(int i=0; i < verticesNumber; ++i){
            for(int j=0; j < verticesNumber; ++j){
                if(edges[i][j] != Double.POSITIVE_INFINITY)
                    incidenceMatrix[i][j] = 1;
                else
                    incidenceMatrix[i][j] = 0;
            }
        }

        return incidenceMatrix;
    }

    public static String getIM(MatrixGraph graph){
        int[][] incidenceMatrix = find(graph);

        // Build the string format of the Incedence Matrix
        StringBuilder result = new StringBuilder();

        for(int i=0; i < incidenceMatrix.length; ++i){
            for(int j=0; j < incidenceMatrix.length; ++j){
                result.append(incidenceMatrix[i][j]);
                result.append(" ");
            }
            result.append("\n");
        }

        return result.toString();
    }
}
