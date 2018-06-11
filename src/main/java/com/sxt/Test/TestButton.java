package com.sxt.Test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: shaxueting
 * Date: 2018/5/31
 * Time: 11:38
 * 描述:  com.sxt.Test
 */
public class TestButton {
    public static void main(String[] args)
    {
        Frame frame = new Frame("Test Button");
        Button button = new Button("Press Me!");
        // 增加事件处理器
        button.addActionListener(new ButtonHandler());


        // 将按钮加入frame
        frame.add(button, BorderLayout.CENTER);

        frame.pack();

        frame.setVisible(true);
    }

}

class ButtonHandler implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //事件处理器

        System.out.println("Button is pressed!");

        String label = e.getActionCommand();
        System.out.println(label);
    }
}
