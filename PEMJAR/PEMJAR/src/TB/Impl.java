package TB;

import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;

public class Impl extends UnicastRemoteObject implements Interface {
	public Impl() throws RemoteException {
		super();
	}

	public String Method1() throws RemoteException {
		return "Hello";
	}

	public int Method2(float f) throws RemoteException {
		return (int) f + 1;
	}

	public Struct StructTest(Struct struct) throws RemoteException {
		int i = struct.getInt();
		float f = struct.getFloat();
		struct.setInt(i + 1);
		struct.setFloat(f + 1.0F);
		return struct;
	}

	private static final long serialVersionUID = 1L;
	private ArrayList<String> text;
	private String text1;

	public ArrayList<String> getText() {
		return text;
	}

	public void setText(ArrayList<String> text) {
		this.text = text;
	}

	@Override
	public String getText1() throws RemoteException {
		return text1;
	}

	@Override
	public void setText1(String text1) throws RemoteException {
		this.text1 = text1;
	}
}