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
    static String a="1";
    static byte[] receiveDataPackage=null;
    public static void main(String[] args) {
        try {
			// TODO: identify the COM port from Windows' control panel
            portId = CommPortIdentifier.getPortIdentifier("COM3");/*�˿��б�*/
            com = portId.open("MCS51COM", 2000);/*�������ƺ�timeout*/
            ser = (SerialPort)com;/*ת���ɷǳ������࣬serialport����parallelport*/
			// Baud rate = 9600, Data bits = 8, 1 stop bit, Parity OFF
           
            
            ser.setSerialPortParams(9600, SerialPort.DATABITS_8, 
                                    SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
        } catch (Exception e){
            e.printStackTrace(System.out);
        }

		/*
		// Wait for 1 second if 8051 needs time to initialise
        try { 
            Thread.sleep(1000);
        } catch (InterruptedException e){}
		*/

        try {
			// Test TX: send out chars 'D', 'O', 'G', 'S'
//            OutputStream comOut = ser.getOutputStream();
//            byte []datByte = a.getBytes();
//           comOut.write(datByte);
//            Thread.sleep(300);
           
          
            
            
			// Test RX: display first 4 chars received
//            InputStream comIn = ser.getInputStream();
//            while(comIn.available()==0);
//            Thread.sleep(1000);
//            int bufferLength=comIn.available();
//            
//           // for (int i = 0; i < 4; i++){
//            receiveDataPackage=new byte[bufferLength];
//            
////                while (bufferLength!=0)
////                {
////                	System.out.println(bufferLength);
////                	comIn.read(receiveDataPackage);
////                	bufferLength=comIn.available();
////                }
//            
//            byte[] t = new byte[bufferLength];
//            comIn.read(t);
//            String ttt=new String(t);
//            
//                String test = new String(receiveDataPackage);
                System.out.println(receiveFromSerial());
//                a="Y";
                
                
                
                    // ��ȡdata buffer���ݳ���
            //}

			// close the streams
           // comOut.close();
            //comIn.close();
            
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
        byte []datByte = a.getBytes();
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
        
       // for (int i = 0; i < 4; i++){
        receiveDataPackage=new byte[bufferLength];
        
//            while (bufferLength!=0)
//            {
//            	System.out.println(bufferLength);
//            	comIn.read(receiveDataPackage);
//            	bufferLength=comIn.available();
//            }
        
        byte[] t = new byte[bufferLength];
        comIn.read(t);
        String ttt=new String(t);
        comIn.close();
        return ttt;
    }
    
   
    
}