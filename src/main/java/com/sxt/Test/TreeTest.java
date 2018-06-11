package com.sxt.Test;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * Created by IntelliJ IDEA.
 * User: shaxueting
 * Date: 2018/6/1
 * Time: 14:53
 * 描述:  com.sxt.Test
 */
public class TreeTest {
    //程序入口

    public static void main(String[] args) {

        TreeTest tj=new TreeTest();

        tj.init();

    }



//初始化界面内容

    public void init() {
        JFrame jFrame = new JFrame();

        jFrame.setTitle("东方标准JTree示例");

        jFrame.setSize(300, 400);

        java.awt.FlowLayout fl = new java.awt.FlowLayout();

        jFrame.setLayout(fl);

// 将自己创建的树加到界面上:

        JTree tree = createTree();

        jFrame.add(tree);

        jFrame.setDefaultCloseOperation(3);

        jFrame.setVisible(true);

    }

    //创建一个自定义树

    public JTree createTree() {

// 创建默认树

        JTree tree = new JTree();

// 首先,创建一个根节点:

        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode();

// 设定节点上的数据对象,节点显示标题则为设定对象的toString()值

        rootNode.setUserObject("树的测试");

// 树下有5个组:

        for (int i = 0; i < 5; i++) {

            DefaultMutableTreeNode teamNode = new DefaultMutableTreeNode();

            teamNode.setUserObject("第" + i + "组");

// 将组节点加到根节点上:

            rootNode.add(teamNode);

            for (int t = 0; t < 6; t++) {

                DefaultMutableTreeNode userNode = new DefaultMutableTreeNode();

                userNode.setUserObject("第" + t + "个用户");

// 将用户节点加到组节点上:

                teamNode.add(userNode);

            }

// 创建树的Model对象，创建时传入根节点：

            DefaultTreeModel dm = new DefaultTreeModel(

                    rootNode);

// 将模型设给树,树上显示的将上前面所加载的节点

            tree.setModel(dm);

// 设定树上的图标

// ImageIcon leafIcon = new ImageIcon("src/budy_init.gif");

// DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();

// renderer.setLeafIcon(leafIcon);

// tree.setCellRenderer(renderer);

        }

        return tree;

    }
}
