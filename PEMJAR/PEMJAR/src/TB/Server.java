package TB;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.net.*;
import java.io.*;

public class Server  {
  public static void main(String args[]) {
    String portNum = "2222", registryURL;
    try{   
      Impl exportedObj = new Impl();
      startRegistry( Integer.parseInt(portNum) );
      // register the object under the name "TB"
      registryURL = "rmi://localhost:" + portNum + "/TB";
      Naming.rebind(registryURL, exportedObj);
      System.out.println("Server ready");
    } catch (Exception re) {
      System.out.println("Exception in Server.main: " + re);
    }
  }
  // This method starts a RMI registry on the local host, if it
  // does not already exist at the specified port number.
  private static void startRegistry(int rmiPortNum) throws RemoteException{
    try {
      Registry registry = LocateRegistry.getRegistry(rmiPortNum);
      registry.list( );  
      // The above call will throw an exception
      // if the registry does not already exist
    } catch (RemoteException ex) {
      // No valid registry at that port.
      System.out.println("RMI registry is not located at port " + rmiPortNum);
      Registry registry = LocateRegistry.createRegistry(rmiPortNum);
      System.out.println("RMI registry created at port " + rmiPortNum);
    }
  }
}