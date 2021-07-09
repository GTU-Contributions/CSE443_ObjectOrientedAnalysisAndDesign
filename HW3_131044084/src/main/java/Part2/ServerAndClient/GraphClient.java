package Part2.ServerAndClient;

import Part2.Graph.MatrixGraph;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class GraphClient {
    public static void main(String[] args) throws Exception {

        if(args.length == 1){
            String serverIP = args[0];

            GraphClient client = new GraphClient(serverIP);

            client.readGraphFromFile("TestGraph.xml");

            System.out.println("Incedence Matrix:");
            System.out.println(client.getIMService("mehmed", "sifremYok"));
            System.out.println("Minimum Spanning Tree:");
            System.out.println(client.getMSTService("mehmed", "sifremYok"));

            System.out.println("Creating Account:");
            System.out.println(client.createAccount("mehmed", "sifremYok"));

            System.out.println("Incedence Matrix:");
            System.out.println(client.getIMService("mehmed", "sifremYok"));
            System.out.println("Minimum Spanning Tree:");
            System.out.println(client.getMSTService("mehmed", "sifremYok"));

            System.out.println("Buying Credits:");
            // Load 3 credits to the account
            System.out.println(client.addCreditsToAccount("4910 6760 2492 3842", "mehmed", "sifremYok", 3));

            System.out.println("Incedence Matrix:");
            System.out.println(client.getIMService("mehmed", "sifremYok"));
            System.out.println("Minimum Spanning Tree:");
            System.out.println(client.getMSTService("mehmed", "sifremYok"));
        }
        else {
            System.err.println("Usage: GraphClient <serverIP>");
        }
    }

    private MatrixGraph currentGraph;
    private GraphServicesI graphServices;
    private boolean isGraphLoaded;

    GraphClient(String serverIP) throws RemoteException, NotBoundException, MalformedURLException {
        String url = "rmi://" + serverIP + "/GraphServices";
        graphServices = (GraphServicesI) Naming.lookup(url);
        isGraphLoaded = false;
        System.out.println("\n[INFO]: You have successfully connected to the GraphServer!");
    }
    public void readGraphFromFile(String inputFileName) throws IOException {
        currentGraph = new MatrixGraph(inputFileName);
        isGraphLoaded = true;
    }
    public String getIMService(String userName, String password) throws RemoteException {
        if(isGraphLoaded == false){
            return "[WARNING]: You haven't choosed your matrix file! See 'help' command how to continue.\n";
        }

        if(graphServices != null)
            return graphServices.getIMService(currentGraph, userName, password);

        return "[ERROR]: Incedence Matrix Service is currently unavailable!\n";
    }
    public String getMSTService(String userName, String password) throws RemoteException {
        if(isGraphLoaded == false){
            return "[WARNING]: You haven't choosed your matrix file! See 'help' command how to continue\n";
        }

        if(graphServices != null)
            return graphServices.getMSTService(currentGraph, 0, userName, password);

        return "[ERROR]: Minimum Spanning Tree Service is currently unavailable!\n";
    }
    public String createAccount(String userName, String password) throws RemoteException {
        if(isGraphLoaded)

        if(graphServices != null)
            return graphServices.createAccount(userName, password);

        return "[ERROR]: Create Account Service is currently unavailable!";
    }
    public String addCreditsToAccount(String creditCardNumber, String userName, String password, int amount) throws RemoteException {
        if(graphServices != null)
            return graphServices.addCreditsToAccount(creditCardNumber, userName, password, amount);

        return "[ERROR]: Add Credits Service is currently unavailable!";
    }

    /* Uncompleted
    public void processUserInputs() {
        boolean isTrueCommand = false;

        while(true){
            System.out.println("\nPlease enter your command> ");
            String userInput = System.console().readLine();

            try {
                isTrueCommand = processUserCommands(userInput);
            } catch(IOException error){
                System.out.println("The path of the matrix file is incorrect! Give proper path!");
            }

            if(!isTrueCommand){
                System.out.println("Your input is invalid! Please try 'help' to get more information about the services.");
            }
        }
    }

    public boolean processUserCommands(String userInput) throws IOException {
        String [] tokens = userInput.split("\\s+");

        switch(tokens.length){
            case 2:
                if(tokens[0].equals("readGraphFromFile")){
                    readGraphFromFile(tokens[1]);
                }
                break;
            case 3:
                if(tokens[0].equals("")){

                }
                break;
            case 4:
                if(tokens[0].equals("")){

                }

                break;
            case 5:
                if(tokens[0].equals("buyCredits")){
                    this.addCreditsToAccount(tokens[1], tokens[2], tokens[3], new Integer(tokens[4]));
                }

                break;
            default:
                return false;
        }

        return true;
    }

*/

}
