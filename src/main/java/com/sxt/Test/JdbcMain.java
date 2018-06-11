package com.sxt.Test;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

/**
 * Created by IntelliJ IDEA.
 * User: shaxueting
 * Date: 2018/5/31
 * Time: 12:16
 * 描述:  com.zdlh.tool
 */
public class JdbcMain implements WindowListener, ActionListener {
    private JFrame frame;
    JPanel jp1,jp2,jp3,jp4;
    JButton jb1,jb2,jb3,jb4,jb5,jb6;
    JButton button1;
    JButton button2;
    JTextField textField1;
    JTextField textField2;
    JTextField textField3;
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    //使用系统默认主题
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    JdbcMain window = new JdbcMain();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public JdbcMain() {
        initialize();
    }
    private void initialize() {
        frame = new JFrame();//实例化对象
        frame.addWindowListener(this);
        frame.setBounds(600, 300, 800, 600);
        frame.setBackground(Color.PINK);//设置窗体的背景颜色
        frame.setTitle("小朵朵");
        frame.setVisible(false);
         jp1 = new JPanel();
         jp2 = new JPanel();
         jp3 = new JPanel();
         jp4 = new JPanel();
        jp1.setLayout(null);
        JLabel label1 = new JLabel("姓名：");
        label1.setBounds(80,10,40,20);
        frame.add(label1);

        textField1 = new JTextField();
        textField1.setBounds(130,8,120,20);
        frame.add(textField1);

        jb1 = new JButton("查询");
        jb1.setBounds(100,40,60,20);
        jb1.addActionListener(this);  //添加鼠标响应事件
        frame.add(jb1);

        jb2 = new JButton("重置");
        jb2.setBounds(180,40,60,20);
        jb2.addActionListener(this);
        frame.add(jb2);
         jp1 = new JPanel();
        jp1.setBounds(40,90,260,100);

        //定义指定颜色，指定标题的边框
        TitledBorder tb = new TitledBorder(BorderFactory.createLineBorder(new Color(255,0,0)),"查询结果");
        tb.setTitleColor(Color.blue);//设置标题颜色

        jp1.setBorder(tb);    //将标题边框添加到面板中
        //resultPanel.setBackground(Color.yellow);
        jp1.setLayout(null);
        JLabel label2 = new JLabel("姓名：");
        label2.setBounds(80,110,40,20);
        frame.add(label2);
        textField2 = new JTextField();
        textField2.setBounds(130,108,120,20);
        frame.add(textField2);
        JLabel label3 = new JLabel("密码");
        label3.setBounds(80,140,40,20);
        frame.add(label3);
        textField3 = new JTextField();
        textField3.setBounds(130,140,120,20);
        frame.add(textField3);

        frame.add(jp1);
//
//        Frame frame = new Frame("Test Button");
//        Button button = new Button("Press Me!");
//        // 增加事件处理器
//        button.addActionListener(new ButtonHandler());
//
//        // 将按钮加入frame
//        frame.add(button, BorderLayout.CENTER);
//
//        frame.pack();
//
//        frame.setVisible(true);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jb = (JButton)e.getSource();
        if(jb == jb1){
            String name = textField1.getText();
            String password = search(name);
            textField2.setText(name);
            textField3.setText(password);
        }
        if(jb == jb2){
            textField1.setText("");
        }
//        String trim = textField1.getText().trim();
//        JOptionPane.showMessageDialog(null, trim);
    }
    public String search(String name){
        String age="";
        try {
            Connection conn = DB.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "select age from user where name='"+name+"'";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                age = rs.getString(1);
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return age;
    }
}

class DB {

    private static final String URL = "jdbc:mysql://localhost:3306/weinuoyh?useUnicode=true&characterEncoding=utf-8";
    //数据库路径：服务器所在的计算机为localhost:1433;
    //数据库名称：UserInfo
    private static final String UserName = "root";    //用户名称
    private static final String PassWord = "weinuo11";  //用户密码
    private static Connection conn = null;

   public static Connection getConnection(){
       try {
           Class.forName("com.mysql.jdbc.Driver");  //加载驱动程序
           conn = DriverManager.getConnection(URL, UserName, PassWord);    //创建数据库连接
       } catch (SQLException e) {
           e.printStackTrace();
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }
       return conn;
   }
}