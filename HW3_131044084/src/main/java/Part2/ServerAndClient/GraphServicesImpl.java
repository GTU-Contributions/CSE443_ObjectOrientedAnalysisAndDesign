package Part2.ServerAndClient;

import Part2.Graph.MatrixGraph;
import Part2.Graph._IncedenceMatrix;
import Part2.Graph._MinimumSpanningTree;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class GraphServicesImpl extends UnicastRemoteObject implements GraphServicesI{
    private static final long serialVersionUID = 1L;

    ArrayList<ClientProfile> registeredUsers = new ArrayList<ClientProfile>();
    // Price for Minimum Spanning Tree Service
    private final static int MST_PRICE = 2;
    // Price for Incedence Matrix Service
    private final static int IM_PRICE = 1;

    protected GraphServicesImpl() throws RemoteException {
        super();
    }

    // Checks if the user is registered and have enough credits to use a service of the server
    private synchronized boolean checkPayment(String userName, String password, int amount){
        for(ClientProfile user : registeredUsers){
            if(user.userIdentification(userName, password)){
                if(user.getCredits() >= amount){
                    user.decreaseCredits(amount);
                    return true;
                }
                return false;
            }
        }

        return false;
    }

    // Get Minimum Spanning Tree Service
    public String getMSTService(MatrixGraph graph, int start, String userName, String password) throws RemoteException {
        long startTime = System.currentTimeMillis();
        StringBuilder result = new StringBuilder();

        if(checkPayment(userName, password, MST_PRICE)){
            result.append(_MinimumSpanningTree.getMST(graph, start));
        } else {
            result.append("[Current Time]: User identification is unsuccessful or you don't have enough credits for this service!\n");
        }

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("[Current Time]: Minimum Spanning Tree Service Invocated and completed in " + totalTime + "ms");

        return result.toString();
    }

    // Get Incidence Matrix Service
    public String getIMService(MatrixGraph graph, String userName, String password) throws RemoteException{
        long startTime = System.currentTimeMillis();
        StringBuilder result = new StringBuilder();

        if(checkPayment(userName, password, IM_PRICE)){
            result.append(_IncedenceMatrix.getIM(graph));
        } else {
            result.append("[Current Time]: User identification is unsuccessful or you don't have enough credits for this service!\n");
        }

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("[Current Time]: Incidencec Matrix Service Invocated and completed in " + totalTime + "ms");

        return result.toString();
    }

    // Create New Account
    public String createAccount(String userName, String password){
        StringBuilder result = new StringBuilder();

        for(ClientProfile user : registeredUsers){
            if(user.getUserName().equals(userName)){
                result.append("An account with user name '");
                result.append(userName);
                result.append("' already exists! Try to register with another user name!\n");
                return result.toString();
            }
        }

        registeredUsers.add(new ClientProfile(userName, password));
        result.append("You have successfully registered to the GraphServer with an user name '");
        result.append(userName);
        result.append("'. Add some credits to your account before requesting a service!\n");

        return result.toString();
    }

    // Add Credits to Existing Account
    public synchronized String addCreditsToAccount(String creditCardNumber, String userName, String password, int creditAmount){
        StringBuilder result = new StringBuilder();

        for(ClientProfile user : registeredUsers){
            if(user.userIdentification(userName, password)){
                user.increaseCredits(creditAmount);

                // Process here credit card Information to get money from the bank account
                int ignoreMe = creditCardNumber.hashCode();

                // Build the result text
                result.append("You have successfully added ");
                result.append(creditAmount);
                result.append(" credits to the '");
                result.append(userName);
                result.append("' account!");
                return result.toString();
            }
        }

        result.append("Wrong identification information! Either your user name or password is incorrect!\n");

        return result.toString();
    }

}
