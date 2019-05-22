package microp;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;



public class FileOpeDock  {
	public static ArrayList<Dock> fetchAllDocks() {/*读取所有用户信息，但不打印，可用于遍历(check)*/

		ArrayList<Dock> a= new ArrayList<Dock>();
		try {
			FileInputStream fileStream= new FileInputStream("Docks.ser");
			ObjectInputStream os= new ObjectInputStream(fileStream);
			Dock head =null;
			try {
					while((head=(Dock)os.readObject())!=null) {
					a.add(head);
					}	
				}
					catch(EOFException e) {}
					os.close();
			/*for(User b: a)
				{
					System.out.println("id="+ b.getId()+"\nemail="+b.getEmail()+"\nstate="+b.getAcState());
				}*/
			return a;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("error1");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("error2");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("error3");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Dock fetchOneDock(String Id)/*use id to find a dock, return that dock(check)*/
	{
		int tracker=0;
		Dock chosen = new Dock();
		ArrayList<Dock> temp=fetchAllDocks();
		for(Dock find : temp)
		{
			//System.out.println("test");
			if((find.dockId.equals(Id))==true)
			{
				chosen=find;
				//temp.remove(tracker);
				return chosen;
			}
			tracker=tracker+1;/*find the location of specific dock*/
		}
		System.out.println("dock find error,return null");
		return null;
		
	}
	
	public static void deleteDock(Dock del)/*use id to find a user, delete that user(check)*/
	{
		int i=0;
		ArrayList<Dock> a= new ArrayList<Dock>();/*将文件中所有对象存入链表*/
		try {
			FileInputStream fileStream= new FileInputStream("Docks.ser");
			ObjectInputStream os= new ObjectInputStream(fileStream);
			Dock head =null;
			try {
				
					while((head=(Dock)os.readObject())!=null) {
						a.add(head);
					}/*ATTENTION！！！！！ You can not write anything between this line and catchEOF*/
					
					
					
				}
			catch(EOFException e) {}
					os.close();
					
					for (Dock find:a) {
						if(find.dockId.equals(del.dockId))
						{
							a.remove(find);
							break;
						}
						i++;
						//System.out.println(i);
					}
					writeFileDock(a);
//					for(User b: a)
//					{
//						System.out.println("id="+ b.getId()+"\nemail="+b.getEmail()+"\nstate="+b.getAcState());
//					}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("error1");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("error2");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("error3");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public static void writeFileDock(ArrayList<Dock> a)/*receive an arraylist, write it into file(check)*/
	{
		try {
			FileOutputStream fs= new FileOutputStream("Docks.ser");
			ObjectOutputStream os=new ObjectOutputStream(fs);
			
			for(Dock b:a)
			{
				os.writeObject(b);
			}
			
			os.flush();
			os.close();
		} 
		catch (FileNotFoundException e) {
			System.out.println("error1");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("error2");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void updateDock(Dock a)/*update a dock's information,you can change the latch information but must not change the name(check)*/
	{
//		Dock d=fetchOneDock(dname);
//		deleteDock(d);
//		ArrayList<Dock> temp= fetchAllDocks();
//		temp.add(a);
//		writeFileDock(temp);
		
		deleteDock(a);/*delete user a*/
		ArrayList<Dock> temp= fetchAllDocks();
		temp.add(a);/*add user a to arraylist*/
		writeFileDock(temp);/*write the arraylist to file*/
		
	}
	
	
}


