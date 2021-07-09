package Part2.Graph;

import java.io.IOException;

public class main {
    public static void main(String[] args) {

        try {
            MatrixGraph graphTest = new MatrixGraph("TestGraph.xml");
            System.out.println("-------------------------------TextFile: TestGraph.xml--------------------------------");
            System.out.println("Number of Vertices: " + graphTest.getVerticesNumber());
            System.out.println();

            System.out.println("Weight Matrix");
            graphTest.printWeightMatrix();
            System.out.println();

        } catch (IOException e) {
            System.out.println("Input file error!");
            e.printStackTrace();
        }



    }

}
