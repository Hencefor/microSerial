package microp;

import java.util.ArrayList;

public class TestMain {
	public static void main(String args[])
	{
		Dock A= new Dock();
		A.dockId="A";
		Dock B= new Dock();
		B.dockId="B";
		Dock C= new Dock();
		C.dockId="C";
		
		ArrayList<Dock> create = new ArrayList<Dock>();
		create.add(A);
		create.add(B);
		create.add(C);
		FileOpeDock.writeFileDock(create);
	}
}
