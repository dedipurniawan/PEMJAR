package TB;

import java.rmi.*;
import java.util.ArrayList;
	  
	public interface Interface extends Remote {
	public String Method1() throws RemoteException;
	public int Method2(float Parameter) throws RemoteException;
	public Struct StructTest(Struct struct) throws RemoteException;
	public ArrayList<String> getText()  throws RemoteException;
	public void setText(ArrayList<String> text) throws RemoteException;
	public String getText1()  throws RemoteException;
	public void setText1(String text) throws RemoteException;
	
}