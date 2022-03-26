package gui.frame;

// 主菜单框架
// 可以选择进入哪一个具体的Panle
// 前面做的界面类都是面板类，不能独立运行。
// 整个项目的运行，还是需要一个顶级容器类 JFrame来容纳这些面板类。
// 在主窗体中通过setContentPane把MainPanel设置为内容面板

// 1.单例设计模式：
// 解决问题：保证一个类在内存中的对象是唯一的。
// 如何保证对象的唯一性？
// 1.不允许其他程序用new关键字来创建对象。
// 2.在该类中创建一个本类的实例。
// 3.定义一个公有的方法，让其他程序可以获取该对象。

import javax.swing.*;

import gui.panel.MainPanel;
import util.GUIUtil;

public class MainFrame extends JFrame {
    public static MainFrame instance = new MainFrame();

    private MainFrame(){
        // 下面是对于Frame的设置
        this.setSize(500,400);
        this.setTitle("MyBill for Windows10");
        // 将MainPanel传递过来
        this.setContentPane(MainPanel.instance);
        // 默认显示在屏幕中间
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        instance.setVisible(true);
    }

}

