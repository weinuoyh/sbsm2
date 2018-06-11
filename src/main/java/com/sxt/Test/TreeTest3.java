package com.sxt.Test;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.sql.*;

/**
 * Created by IntelliJ IDEA.
 * User: shaxueting
 * Date: 2018/6/1
 * Time: 15:20
 * 描述:  com.sxt.Test
 */
public class TreeTest3 {
    private JFrame frame;
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    //使用系统默认主题
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    TreeTest3 window = new TreeTest3();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public TreeTest3() {
        initialize();
    }
    private void initialize() {
        // 创建没有父节点和子节点、但允许有子节点的树节点，并使用指定的用户对象对它进行初始化。
        // public DefaultMutableTreeNode(Object userObject)
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
    }
}
