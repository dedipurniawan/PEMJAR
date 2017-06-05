package TB;

import java.io.*;
import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class Client {
	public static void main(String args[]) {
		try {
			String hostName;
			String portNum = "8080";
			String registryURL = "rmi://localhost:" + portNum + "/TB";
			Interface h = (Interface) Naming.lookup(registryURL);
			// invoke the remote method(s)
			String message = h.someMethod1();
			System.out.println(message);
			int i = h.someMethod2(12344);
			System.out.println(i);
			Struct someStructOut = new Struct(10, 100.0F);
			Struct someStructIn = new Struct(0, 0.0F);
			someStructIn = h.someStructTest(someStructOut);
			System.out.println(someStructIn.getInt());
			System.out.println(someStructIn.getFloat());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}