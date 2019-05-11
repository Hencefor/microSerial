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
 
    private static JFrame frame = new JFrame("单片机的串口通信测试");
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
 
        // 马达启动按钮
        ImageIcon startIcon = new ImageIcon("images/start.jpg");
        motoStartBtn = new JButton(" 马达启动", startIcon);
        motoStartBtn.setFont(new Font("Microsoft YaHei", 1, 15));
        jPanel.setBorder(new EmptyBorder(30, 20, 20, 30));
        motoStartBtn.setActionCommand("motoStartBtn");
        jPanel.add(motoStartBtn);
         
        // 马达停止按钮
        ImageIcon stopIcon = new ImageIcon("images/stop.jpg");
        motoStopBtn = new JButton(" 马达停止", stopIcon);
        motoStopBtn.setFont(new Font("Microsoft YaHei", 1, 15));
        motoStopBtn.setActionCommand("motoStopBtn");
        jPanel.add(motoStopBtn);
         
        // 蜂鸣器启动按钮
        buzzerStartBtn = new JButton(" 蜂鸣器启动", startIcon);
        buzzerStartBtn.setFont(new Font("Microsoft YaHei", 1, 15));
        jPanel.setBorder(new EmptyBorder(30, 20, 20, 30));
        buzzerStartBtn.setActionCommand("buzzerStartBtn");
        jPanel.add(buzzerStartBtn);
         
        // 蜂鸣器停止按钮
        buzzerStopBtn = new JButton(" 蜂鸣器停止", stopIcon);
        buzzerStopBtn.setFont(new Font("Microsoft YaHei", 1, 15));
        jPanel.setBorder(new EmptyBorder(30, 20, 20, 30));
        buzzerStopBtn.setActionCommand("buzzerStopBtn");
        jPanel.add(buzzerStopBtn);
         
        // 说明
        jLabel = new JLabel();
        jLabel.setFont(new Font("Microsoft YaHei", 1, 15));
        jLabel.setText("说明：按下“启动”按钮，启动功能模块，按下“停止”按钮，停止功能模块。");
        jPanel.add(jLabel);
 
        // 事件监听
        motoStartBtn.addActionListener(this);
        motoStopBtn.addActionListener(this);
        buzzerStartBtn.addActionListener(this);
        buzzerStopBtn.addActionListener(this);
    }
 
    /**
     * 用于接收操作事件的侦听器接口。
     * 在发生操作事件时，调用该对象的 actionPerformed 方法
     * 即在点击按钮时调用ActionListener接口的覆写方法
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            /**
             * 在while循环中实现，如果连续两次按下“启动蜂鸣器”按钮，就会出现BUG，所以这里让蜂鸣器的“启动”和”停止“
             * 因为在单片机程序中，蜂鸣器是靠不断更替的高低电平来启动蜂鸣器的两个按钮互斥，默认不能连续两次按下同一个按钮，
             * 而马达在单片机中不需要在循环代码中控制，所以这里不需要让马达的启动和停止按钮互斥，但为了风格统一，这里也互斥了
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
 
        //获取你的屏幕的宽和高
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        //然后设置你编写的窗口的初始位置，也就是在中间，
        frame.setLocation(width/2-300, height/2-250);
 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setVisible(true);
    }
}