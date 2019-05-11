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
    static String a="abc";
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

		/*
		// Wait for 1 second if 8051 needs time to initialise
        try { 
            Thread.sleep(1000);
        } catch (InterruptedException e){}
		*/

        try {
			// Test TX: send out chars 'D', 'O', 'G', 'S'
            OutputStream comOut = ser.getOutputStream();
            byte []datByte = a.getBytes();
           comOut.write(datByte);
            Thread.sleep(300);
           // comOut.write('1');
//            comOut.write('G');
//            comOut.write('S');
//           
            
            
			// Test RX: display first 4 chars received
            InputStream comIn = ser.getInputStream();
            int bufferLength=comIn.available();
           // for (int i = 0; i < 4; i++){
            receiveDataPackage=new byte[bufferLength];
            
//                while (bufferLength!=0)
//                {
//                	System.out.println(bufferLength);
//                	comIn.read(receiveDataPackage);
//                	bufferLength=comIn.available();
//                }
            
            byte[] t = new byte[3];
            comIn.read(t);
            String ttt=new String(t);
            
                String test = new String(receiveDataPackage);
                System.out.println(ttt);
                    // 获取data buffer数据长度
            //}

			// close the streams
            comOut.close();
            comIn.close();
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println("test");
        }
		// close the port
        ser.close(); 
    }
}