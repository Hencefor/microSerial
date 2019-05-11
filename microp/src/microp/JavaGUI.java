package microp;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
 
//import com.lvshitech.java51.tool.Tools;
 
@SuppressWarnings("serial")
public class JavaGUI extends JPanel implements ActionListener {
 
    private static JFrame frame = new JFrame("��Ƭ���Ĵ���ͨ�Ų���");
    private static JPanel jPanel;
    JLabel jLabel;
    JButton motoStartBtn;
    JButton motoStopBtn;
    JButton buzzerStartBtn;
    JButton buzzerStopBtn;
 
    JavaGUI() {
        frame.setSize(800, 250);
        frame.setResizable(false);
        jPanel = new JPanel();
        jPanel.setBorder(new EmptyBorder(30, 20, 20, 30));
 
        // ���������ť
        ImageIcon startIcon = new ImageIcon("images/start.jpg");
        motoStartBtn = new JButton(" �������", startIcon);
        motoStartBtn.setFont(new Font("Microsoft YaHei", 1, 15));
        jPanel.setBorder(new EmptyBorder(30, 20, 20, 30));
        motoStartBtn.setActionCommand("motoStartBtn");
        jPanel.add(motoStartBtn);
         
        // ���ֹͣ��ť
        ImageIcon stopIcon = new ImageIcon("images/stop.jpg");
        motoStopBtn = new JButton(" ���ֹͣ", stopIcon);
        motoStopBtn.setFont(new Font("Microsoft YaHei", 1, 15));
        motoStopBtn.setActionCommand("motoStopBtn");
        jPanel.add(motoStopBtn);
         
        // ������������ť
        buzzerStartBtn = new JButton(" ����������", startIcon);
        buzzerStartBtn.setFont(new Font("Microsoft YaHei", 1, 15));
        jPanel.setBorder(new EmptyBorder(30, 20, 20, 30));
        buzzerStartBtn.setActionCommand("buzzerStartBtn");
        jPanel.add(buzzerStartBtn);
         
        // ������ֹͣ��ť
        buzzerStopBtn = new JButton(" ������ֹͣ", stopIcon);
        buzzerStopBtn.setFont(new Font("Microsoft YaHei", 1, 15));
        jPanel.setBorder(new EmptyBorder(30, 20, 20, 30));
        buzzerStopBtn.setActionCommand("buzzerStopBtn");
        jPanel.add(buzzerStopBtn);
         
        // ˵��
        jLabel = new JLabel();
        jLabel.setFont(new Font("Microsoft YaHei", 1, 15));
        jLabel.setText("˵�������¡���������ť����������ģ�飬���¡�ֹͣ����ť��ֹͣ����ģ�顣");
        jPanel.add(jLabel);
 
        // �¼�����
        motoStartBtn.addActionListener(this);
        motoStopBtn.addActionListener(this);
        buzzerStartBtn.addActionListener(this);
        buzzerStopBtn.addActionListener(this);
    }
 
    /**
     * ���ڽ��ղ����¼����������ӿڡ�
     * �ڷ��������¼�ʱ�����øö���� actionPerformed ����
     * ���ڵ����ťʱ����ActionListener�ӿڵĸ�д����
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            /**
             * ��whileѭ����ʵ�֣�����������ΰ��¡���������������ť���ͻ����BUG�����������÷������ġ��������͡�ֹͣ��
             * ��Ϊ�ڵ�Ƭ�������У��������ǿ����ϸ���ĸߵ͵�ƽ��������������������ť���⣬Ĭ�ϲ����������ΰ���ͬһ����ť��
             * ������ڵ�Ƭ���в���Ҫ��ѭ�������п��ƣ��������ﲻ��Ҫ������������ֹͣ��ť���⣬��Ϊ�˷��ͳһ������Ҳ������
             */
            if("motoStartBtn".equals(e.getActionCommand())) {
                motoStartBtn.setEnabled(false);
                Tools.action("1");
                motoStopBtn.setEnabled(true);
            } else if("motoStopBtn".equals(e.getActionCommand())) {
                motoStopBtn.setEnabled(false);
                Tools.action("0");
                motoStartBtn.setEnabled(true);
            } else if("buzzerStartBtn".equals(e.getActionCommand())) {
                buzzerStartBtn.setEnabled(false);
                Tools.action("2");
                buzzerStopBtn.setEnabled(true);
            } else {
                buzzerStopBtn.setEnabled(false);
                Tools.action("3");
                buzzerStartBtn.setEnabled(true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
 
    public static void main(String[] args) {
        Container c = frame.getContentPane();
        c.add(new JavaGUI(), BorderLayout.CENTER);
        frame.add(jPanel);
 
        //��ȡ�����Ļ�Ŀ�͸�
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        //Ȼ���������д�Ĵ��ڵĳ�ʼλ�ã�Ҳ�������м䣬
        frame.setLocation(width/2-300, height/2-250);
 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setVisible(true);
    }
}