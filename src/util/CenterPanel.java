package util;

// 这个类实现了面板居中
// 开发后期：把这个看作是画图util

import gui.panel.WorkingPanel;

import java.awt.Component;
// dimension是Java的一个类，封装了一个构件的高度和宽度
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class CenterPanel extends JPanel{
    private boolean strech; // 是否拉伸
    private double rate;  // 拉伸比例
    private JComponent c;  // 显示的组件

    // 构造函数
    public CenterPanel(double rate,boolean strech)
    {
        // 布局管理器
        this.setLayout(null);  // 不适用相关的布局
        this.rate = rate;
        this.strech = strech;
    }

    public CenterPanel(double rate){
        this(rate,true);  // 调用上一个构造函数
    }

    // 因为 super.repaint里有业务逻辑要执行，仅仅是重写就吧这部分逻辑顶掉了，
    // 所以还是需要手动调用一次父类的 repaint才行
    public void repaint()
    {
        if(null != c){
            Dimension containerSize = this.getSize();
            Dimension componentSize = c.getPreferredSize();  // 获取一个偏好的大小

            // 伸缩后的大小
            if(strech)
            {
                c.setSize((int)(containerSize.width*rate),(int)(containerSize.height*rate));
            }
            else{
                c.setSize(componentSize);
            }

            c.setLocation(containerSize.width / 2 - c.getSize().width / 2,
                    containerSize.height / 2 - c.getSize().height / 2);
            super.repaint();
        }
    }


    // 使用show方法显示组件,show方法中的思路是：
    // 先把这个容器中的组件都移出，然后把新的组件加进来，
    // 并且调用updateUI进行界面渲染。
    // updateUI会导致swing去调用repaint()方法
    // 展示组件
    public void show(JComponent p){
        this.c = p;
        Component[] cs = getComponents();
        for(Component c : cs)
        {
            remove(c);
        }
        add(p);
        // 打开主菜单时更新（后期补上的代码，前期不理会）
        if(p instanceof WorkingPanel)
            // 转一下，使用父类方法
            // 这里很重要
            // 每一次重写updateData时，都在此处调用
            ((WorkingPanel) p).updateData();
        this.updateUI();
    }

    // 测试
    public static void main(String[] args)
    {
        JFrame f = new JFrame();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(200,200);
        // 设置窗口相对于指定组件的位置。
        //如果组件当前未显示或者 c 为 null，则此窗口将置于屏幕的中央。
        f.setLocationRelativeTo(null);
        CenterPanel cp = new CenterPanel(0.85,true);
        // 用getContentPane()方法获得JFrame的内容面板(再对其加入组件)
        f.setContentPane(cp);
        JButton b = new JButton("abc");  // 测试环节
        cp.show(b);
    }
}
