package Part2.ServerAndClient;

import java.rmi.Naming;

public class GraphServer {
    public static void main(String[] args) throws  Exception{
        GraphServer server = new GraphServer();
        server.start();
    }

    GraphServer(){}

    public void start() throws Exception{
        GraphServicesImpl graphServices = new GraphServicesImpl();
        Naming.rebind("GraphServices", graphServices);
        System.out.println("\n[SERVER]: 'GraphServices' are ready for use...");
    }
}
