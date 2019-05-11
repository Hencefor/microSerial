package microp;
 
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
 
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.TooManyListenersException;
 
/**
 * ����rxtx��������
 *
 * rxtx������https://bitbucket.org/jlauer/mfz-cdn/downloads/mfz-rxtx-2.2-20081207-win-x64.zip��
 * ֮ǰ��http://rxtx.qbang.org/pub/rxtx/rxtx-2.1-7-bins-r2.zip���صģ�û��x64�汾dll��
 *
 * ʹ�õ�jdk��װ��C:\Program Files\Java\jdk1.8.0_60�����ԣ�
 * ��ѹ���ļ�rxtxParallel.dll��rxtxSerial.dll�ŵ�C:\Program Files\Java\jdk1.8.0_60\bin
 * ��ѹ���ļ�RXTXcomm.jar�ŵ�C:\Program Files\Java\jdk1.8.0_60\jre\lib\ext
 *
 * ������RXTXcomm.jar��mvn�⣬�ҵ�org.rxtx:rxtx:2.1.7��Ӧ����һ���ģ�����û��jar����dll������dllӦ�û���Ҫ�ֶ���
 *
 * @author zhanglc-c
 */
public class TestRxTx {
 
	public static void main(String[] args) throws Exception {
		CommPortIdentifier port = getSerialPort("COM3");
		System.out.println(port.getName());
		SerialPortClient client = new SerialPortClient(port);
		client.initAndOpen();
 
		client.send(new byte[]{(byte) 0x05});
		client.send(new byte[]{(byte) 0x02});
		//InputStream comIn = client.getInputStream();
		short temp=1;
		
 
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
			client.send(new byte[]{(byte) i});
			//client.onReceive(null);
			
			Thread.sleep(200);
		}
 
		client.close();
	}
 
	// �г��˿�
	public static Enumeration<CommPortIdentifier> listAllPort() {
		@SuppressWarnings("unchecked")
		Enumeration<CommPortIdentifier> portList = CommPortIdentifier
				.getPortIdentifiers();
		return portList;
	}
 
	public static List<CommPortIdentifier> listAllPort(int portType) {
		List<CommPortIdentifier> ret = new ArrayList<>();
		Enumeration<CommPortIdentifier> all = listAllPort();
		if (all == null)
			return ret;
		while (all.hasMoreElements()) {
			CommPortIdentifier portId = (CommPortIdentifier) all.nextElement();
			if (portId.getPortType() == portType) {
				ret.add(portId);
			}
		}
		return ret;
	}
 
	public static List<CommPortIdentifier> listAllSerialPort() {
		return listAllPort(CommPortIdentifier.PORT_SERIAL);
	}
 
	// ��ȡ����
	public static CommPortIdentifier getSerialPort(String portName) {
		List<CommPortIdentifier> list = listAllSerialPort();
		if (list == null)
			return null;
		for (CommPortIdentifier p : list) {
			if (p.getName().equalsIgnoreCase(portName)) {
				return p;
			}
		}
		return null;
	}
 
	// ���ڲ���
	public static class SerialPortClient implements SerialPortEventListener {
		public final CommPortIdentifier port;
		public SerialPort serialPort;
		public InputStream is;
		public OutputStream os;
 
		public SerialPortClient(CommPortIdentifier port) {
			this.port = port;
		}
 
		public void initAndOpen() throws IOException, UnsupportedCommOperationException,
				TooManyListenersException, PortInUseException {
			System.out.println("���ڣ���ʼ���ʹ򿪡�������������9600������λ8��ֹͣλ1������żУ�飩");
			serialPort = (SerialPort) port.open("SerialPort-Test", 2000);
 
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
 
			serialPort.setSerialPortParams(9600,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);
 
			os = serialPort.getOutputStream();
			is = serialPort.getInputStream();
		}
 
		public void close() {
			System.out.println("���ڣ��رա�����");
			if (os != null)
				try {
					os.close();
				} catch (IOException e) {
				}
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
				}
			if (serialPort != null)
				serialPort.close();
		}
 
		public void send(byte[] arr) throws IOException {
			if (arr != null && os != null) {
				System.out.println("���ڣ������ֽڸ���" + arr.length);
				os.write(arr);
			} else {
				System.out.println("���ڣ�����ʧ�ܣ���������Ϊ�ջ�û�򿪴���");
			}
		}
 
		protected void onReceive(SerialPortEvent event) {
			System.out.println("���ڣ������ݡ�����");
			int newData = 0;
			do {
				try {
					newData = is.read();
					System.out.println("���ڣ��������ݣ�" + Integer.toString(newData, 16));
				} catch (IOException e) {
					return;
				}
			} while (newData != -1);
		}
		
		
 
		public void serialEvent(SerialPortEvent event) {
			switch (event.getEventType()) {
			case SerialPortEvent.BI:
			case SerialPortEvent.OE:
			case SerialPortEvent.FE:
			case SerialPortEvent.PE:
			case SerialPortEvent.CD:
			case SerialPortEvent.CTS:
			case SerialPortEvent.DSR:
			case SerialPortEvent.RI:
			case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
				break;
			case SerialPortEvent.DATA_AVAILABLE:// ��ȡ�����ڷ�����Ϣ
				onReceive(event);
				break;
			default:
				break;
			}
		}
 
	}
}

