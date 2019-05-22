package microp;

/*
 * Based on SimpleWrite.java provided by Java Sun
 * Updated by Matthew Tang @QMUL 7 May 2017.
 * You need to have RXTX installed with Java
 * Link 1: http://rxtx.qbang.org/wiki/index.php/Main_Page
 * Link 2: http://fizzed.com/oss/rxtx-for-java
 */
import java.io.*;
import java.util.*;
import gnu.io.*; 

public class SimpleCommRxTx {
    static CommPortIdentifier portId;
    static CommPort com;
    static SerialPort ser;
    static String confirm;
    static String operation;
    static int position;
    static int i=0;
   // static String a="1";
    static byte[] receiveDataPackage=null;
    public static void main(String[] args) {
    	
    	
    		
    	
    	
        try {
			// TODO: identify the COM port from Windows' control panel
            portId = CommPortIdentifier.getPortIdentifier("COM3");/*端口列表*/
            com = portId.open("MCS51COM", 2000);/*程序名称和timeout*/
            ser = (SerialPort)com;/*转换成非抽象子类，serialport或者parallelport*/
			// Baud rate = 9600, Data bits = 8, 1 stop bit, Parity OFF
           
            
            ser.setSerialPortParams(9600, SerialPort.DATABITS_8, 
                                    SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
        } catch (Exception e){
            e.printStackTrace(System.out);
        }
        String ID;
		
		try {
				ID=receiveFromSerial();
				System.out.println(ID);
	            System.out.println(ID.length());
	            
            while(ID.length()!=9)
            {
            	
                	sendToSerial("N");
                	ID=receiveFromSerial();
            }
            sendToSerial("Y");
		} catch (IOException | InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
            
       
		

        try {
        		
        	 for(int k=0;k<10;k++)
         	{
                   
                    Thread.sleep(1000);
                    operation=receiveFromSerial();
                    System.out.println(operation);
                	   if(operation.equals("P"))
                	   {
                		   for(i=0;i<60;i++)/*判断60秒*/
                           {
                           	Thread.sleep(1000);
                           	InputStream comIn = ser.getInputStream();
                          	System.out.println("test");
                               if(comIn.available()!=0)
                               {
                               	comIn.close();
                               	confirm=receiveFromSerial();
                               //	System.out.println("test");
                               	if(confirm.equals("E"))
                               		return;
                               	break;
                               }
                               
                               
                           }
                		   if(i==60)
                           	return;
                		   
                	
                		   Dock Da = new Dock();
                		   Da=FileOpeDock.fetchOneDock("A");
                		   position=Da.releaseScooter();
                		   FileOpeDock.updateDock(Da);
                		   
                		   System.out.println("pick"+position+"complete");
                	   }
                	   else if(operation.equals("R"))
                	   {
                		   for(i=0;i<60;i++)/*判断60秒*/
                           {
                           	Thread.sleep(1000);
                           	InputStream comIn = ser.getInputStream();
                               if(comIn.available()!=0)
                               {
                               	comIn.close();
                               	confirm=receiveFromSerial();
                               	if(confirm.equals("E"))
                               		return;
                               	break;
                               }
                           }
                		   if(i==60)
                           	return;
                		   
                		   Dock Da1 = new Dock();
                		   Da1=FileOpeDock.fetchOneDock("A");
                		   position=Da1.retrieveScooter();
                		   FileOpeDock.updateDock(Da1);
                		   System.out.println("return"+position+"complete");
                	   }
                		
         	}  
                
                
                
                
                
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println("test");
        }
		// close the port
        ser.close(); 
    	
    }
    public static void sendToSerial(String s) throws IOException, InterruptedException
    {
    	OutputStream comOut = ser.getOutputStream();
        byte []datByte = s.getBytes();
        Thread.sleep(300);
       comOut.write(datByte);
        Thread.sleep(300);
        comOut.close();
    }
    
    public static String receiveFromSerial() throws IOException, InterruptedException
    {
    	InputStream comIn = ser.getInputStream();
    	
        while(comIn.available()==0);
        Thread.sleep(1000);
        int bufferLength=comIn.available();
        
      
        receiveDataPackage=new byte[bufferLength];
        
        byte[] t = new byte[bufferLength];
        comIn.read(t);
        String ttt=new String(t);
        comIn.close();
        return ttt;
    }
    
   
    
}