package com.sxt.Test;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;

/**
 * Created by IntelliJ IDEA.
 * User: shaxueting
 * Date: 2018/5/31
 * Time: 12:16
 * 描述:  com.zdlh.tool
 */
public class SxtMain implements WindowListener, ActionListener {
    private JFrame frame;
    JPanel jp1, jp2, jp3, jp4;
    JButton jb1, jb2, jb3, jb4, jb5, jb6;
    JButton button1;
    JButton button2;
    JTextField textField1, textField2, textField3, textTree;

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    //使用系统默认主题
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    SxtMain window = new SxtMain();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public SxtMain() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();//实例化对象
        frame.addWindowListener(this);
        frame.setBounds(600, 300, 800, 600);
        frame.setBackground(Color.PINK);//设置窗体的背景颜色
        frame.setTitle("小朵朵");
        frame.setVisible(false);
        frame.setLayout(null);
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp1.setLayout(null);
        JLabel label1 = new JLabel("姓名：");
        label1.setBounds(80, 10, 40, 20);
        frame.add(label1);

        textField1 = new JTextField();
        textField1.setBounds(130, 8, 120, 20);
        frame.add(textField1);

        jb1 = new JButton("查询");
        jb1.setBounds(100, 40, 60, 20);
        jb1.addActionListener(this);  //添加鼠标响应事件
        frame.add(jb1);

        jb2 = new JButton("重置");
        jb2.setBounds(180, 40, 60, 20);
        jb2.addActionListener(this);
        frame.add(jb2);
        jp1 = new JPanel();
        jp1.setBounds(40, 90, 260, 100);
        jp1.setBackground(Color.yellow);
        //定义指定颜色，指定标题的边框
        TitledBorder tb = new TitledBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)), "查询结果");
        tb.setTitleColor(Color.blue);//设置标题颜色

        jp1.setBorder(tb);    //将标题边框添加到面板中
        //resultPanel.setBackground(Color.yellow);
        jp1.setLayout(null);
        JLabel label2 = new JLabel("姓名：");
        label2.setBounds(80, 110, 40, 20);
        frame.add(label2);
        textField2 = new JTextField();
        textField2.setBounds(130, 108, 120, 20);
        frame.add(textField2);
        JLabel label3 = new JLabel("密码");
        label3.setBounds(80, 140, 40, 20);
        frame.add(label3);
        textField3 = new JTextField();
        textField3.setBounds(130, 140, 120, 20);
        frame.add(textField3);

        frame.add(jp1);
        jp2 = new JPanel();
        jp2.setLayout(null);
        jp2.setBounds(5, 200, 780, 350);
        jp2.setBackground(Color.white);
//        textTree = new JTextField();
//        textTree.setBounds(6, 206, 360, 350);
//        textTree.setBackground(Color.PINK);
        DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("表");
        Connection conn = DB.getConnection();
        try {
            DatabaseMetaData dbMetaData = conn.getMetaData();
            ResultSet rs = dbMetaData.getTables(null, null, null,new String[] { "TABLE" });
            while (rs.next()) {// ///TABLE_TYPE/REMARKS
                String name=rs.getString("TABLE_NAME");
                node1.add(new DefaultMutableTreeNode(name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        node1.add(new DefaultMutableTreeNode(new User("表1")));
        node1.add(new DefaultMutableTreeNode(new User("表2")));
        node1.add(new DefaultMutableTreeNode(new User("表3")));

        DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("视图");
        node2.add(new DefaultMutableTreeNode(new User("视图1")));
        node2.add(new DefaultMutableTreeNode(new User("视图2")));
        node2.add(new DefaultMutableTreeNode(new User("视图3")));

        DefaultMutableTreeNode top = new DefaultMutableTreeNode("zdlh_api");

        top.add(new DefaultMutableTreeNode(new User("11")));
        top.add(node1);
        top.add(node2);
        final JTree tree = new JTree(top);
        JFrame f = new JFrame("JTreeDemo");
        f.add(tree);
        f.setSize(300, 300);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 添加选择事件
        tree.addTreeSelectionListener(new TreeSelectionListener() {

            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
                        .getLastSelectedPathComponent();

                if (node == null)
                    return;

                Object object = node.getUserObject();
                if (node.isLeaf()) {
                    User user = (User) object;
                    System.out.println("你选择了：" + user.toString());
                }

            }
        });

        //jp2.add(textTree);
        frame.add(jp2);
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
        JButton jb = (JButton) e.getSource();
        if (jb == jb1) {
            String name = textField1.getText();
            String password = search(name);
            textField2.setText(name);
            textField3.setText(password);
        }
        if (jb == jb2) {
            textField1.setText("");
        }
//        String trim = textField1.getText().trim();
//        JOptionPane.showMessageDialog(null, trim);
    }

    public String search(String name) {
        String age = "";
        try {
            Connection conn = DB.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "select age from user where name='" + name + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
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


