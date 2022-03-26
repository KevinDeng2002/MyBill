package util;

// 本类实现了GUI的基本操作
// 单独写居中面板是因为这里没有这个功能


import jdk.xml.internal.JdkXmlFeatures;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.ObjectInput;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUIUtil {
    private static String imageFolder = "C:\\Users\\15715\\Desktop\\Programs\\Java\\Project\\MyBill\\src\\img";

    // 把按键传进来进行设置
    // 给按钮设置图标和文本以及提示文字
    public static void setImageIcon(JButton b, String filename, String tip) {
        ImageIcon i = new ImageIcon(new File(imageFolder, filename).getAbsolutePath());
        b.setIcon(i);
        // 开始设置
        b.setPreferredSize((new Dimension(61, 81)));
        // 提示文字以及位置
        // 作用是，每次鼠标进入这个标签时，都会执行一次setToolTipText方法
        // 颜色发生变化
        b.setToolTipText(tip);
        b.setVerticalTextPosition(JButton.BOTTOM);
        b.setHorizontalTextPosition(JButton.CENTER);
        // 直接设置文字
        b.setText(tip);
    }

    // 给多个组件设置前景色
    // 可变参数
    public static void setColor(Color color, JComponent... cs) {
        for (JComponent c : cs) {
            c.setForeground(color);
        }
    }

    // 水晶皮肤的使用
    public static void useLNF() {
        try {
            javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 显示面板的内容
    public static void showPanel(JPanel p, double strech) {
        GUIUtil.useLNF();   // 使用水晶皮肤
        JFrame f = new JFrame();
        f.setSize(500, 500);
        f.setLocationRelativeTo(null);
        CenterPanel cp = new CenterPanel(strech);
        f.setContentPane(cp);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        cp.show(p);
    }

    // 假设不输入strech
    public static void showPanel(JPanel p) {
        showPanel(p, 0.85);
    }


    // 下面三个方法相当于三个递进的函数

    // 判断输入框的内容是否为空
    // 不空返回true 空返回false
    public static boolean chechEmpty(JTextField tf, String input) {
        String text = tf.getText().trim();  // .trim()的作用是在获得的文本中除去空格
        if (0 == text.length()) {
            JOptionPane.showMessageDialog(null, input + " 不能为空!");
            tf.grabFocus();  // 在一个窗口弹出后，焦点定位在文本框(此处的窗口为上文的问题显示窗口)
            return false;
        }
        return true;
    }


    // 校验一个组件是否为数字格式
    public static boolean checkNumber(JTextField tf, String input) {
        if (!chechEmpty(tf, input))
            return false;
        String text = tf.getText().trim();
        try {
            int x = Integer.parseInt(text);  // 将String类型的参数，解析为一个带符号的十进制整数。
            if(x <= 0) return false;
            return true;
        } catch (NumberFormatException el) {
            JOptionPane.showMessageDialog(null, input + " 需要是整数!");
            tf.grabFocus();
            return false;
        }
    }

    // 检查组件内容是否为0
    public static boolean chechZero(JTextField tf, String input) {
        if (!checkNumber(tf, input)) return false;
        String text = tf.getText().trim();
        if (0 == Integer.parseInt(text)) {
            JOptionPane.showMessageDialog(null, input + " 不能为0!");
            tf.grabFocus();
            return false;
        }
        return true;
    }


    // 测试文件
    public static void main(String[] args) {
        // 测试一下面板的显示和水晶皮肤
        GUIUtil.useLNF();
        JPanel p = new JPanel();
        p.add(new JButton("按钮1"));
        p.add(new JButton("按钮2"));
        GUIUtil.showPanel(p);
    }
}


















