package Test;

// 这是一个最基本的面板的预览
// 可以看见仅仅是写出接口就篇幅不小
// 所以需要package来管理

import sun.font.TrueTypeFont;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.Border;


public class MyBillMainFrame {
    public static void main(String[] args)
    {
        // 建类
        JFrame f = new JFrame();
        // 基本界面设置
        f.setSize(500,400);
        f.setTitle("MyBill");
        // 居中
        f.setLocationRelativeTo(null);
        f.setResizable(true);
        // 关闭窗口停止运行
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 添加按钮
        JToolBar tb = new JToolBar();
        JButton bSpend = new JButton("消费一览");
        JButton bRecord = new JButton("记一笔");
        JButton bCategory = new JButton("消费分类");
        JButton bReport = new JButton("月消费报表");
        JButton bConfig = new JButton("设置");
        JButton bBackup = new JButton("备份");
        JButton bRecover = new JButton("恢复");
        // 加上去
        tb.add(bSpend);
        tb.add(bRecord);
        tb.add(bCategory);
        tb.add(bReport);
        tb.add(bConfig);
        tb.add(bBackup);
        tb.add(bRecover);
        // 边界布局
        f.setLayout(new BorderLayout());   // BorderLayout提供5个方位可以放置
        // 把上面写的几个按钮放到上面
        f.add(tb,BorderLayout.NORTH);
        // 再来一个面板放到中间
        f.add(new JPanel(), BorderLayout.CENTER);

        // 最后设置可视化
        f.setVisible(true);

        bSpend.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

            }
        });
        bRecord.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

            }
        });
        bCategory.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

            }
        });
        bConfig.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

            }
        });
        bBackup.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

            }
        });
        bRecover.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

            }
        });


    }
}
