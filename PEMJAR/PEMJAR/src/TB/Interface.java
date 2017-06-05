package TB;

import java.rmi.*;
import java.util.ArrayList;
	  
	public interface Interface extends Remote {
	public String someMethod1() throws RemoteException;
	public int someMethod2(float someParameter) throws RemoteException;
	public Struct someStructTest(Struct struct) throws RemoteException;
	public ArrayList<String> getText()  throws RemoteException;
	public void setText(ArrayList<String> text) throws RemoteException;
	public String getText1()  throws RemoteException;
	public void setText1(String text) throws RemoteException;
	
}