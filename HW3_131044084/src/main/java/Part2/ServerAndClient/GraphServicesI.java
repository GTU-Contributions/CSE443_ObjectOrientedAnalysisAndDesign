package Part2.ServerAndClient;

import Part2.Graph.MatrixGraph;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GraphServicesI extends Remote {
    String getMSTService(MatrixGraph graph, int start, String userName, String password) throws RemoteException;
    String getIMService(MatrixGraph graph, String userName, String password) throws RemoteException;
    String createAccount(String userName, String password) throws RemoteException;
    String addCreditsToAccount(String creditCardNumber, String userName, String password, int creditAmount) throws RemoteException;
}
