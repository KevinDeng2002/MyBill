package Test;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.border.Border;

import util.CircleProgressBar;
import util.ColorUtil;
import util.GUIUtil;

public class CircleTest {
    public static void main(String[] args)
    {
        GUIUtil.useLNF();  // 水晶皮肤
        JPanel p = new JPanel();   // 创建面板(2)
        CircleProgressBar cpb = new CircleProgressBar();
        // 设置颜色
        cpb.setBackgroundColor(ColorUtil.blueColor);
        cpb.setProgress(0);
        // 按钮(3)
        JButton b = new JButton("点击");

        // 把（3）放入（2）
        p.setLayout(new BorderLayout());  // 使用该布局
        p.add(cpb, BorderLayout.CENTER);
        p.add(b,BorderLayout.SOUTH);
        // 显示面板
        GUIUtil.showPanel(p);

        // 给按键添加监视器
        



    }
}
