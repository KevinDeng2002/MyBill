package gui.panel;

// 显示mysql安装路径


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.jar.JarEntry;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import gui.listener.ConfigListener;
import jdk.internal.org.objectweb.asm.tree.IincInsnNode;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import service.ConfigService;
import sun.security.x509.IPAddressName;
import util.ColorUtil;
import util.GUIUtil;

public class ConfigPanel extends WorkingPanel{
    static {
        GUIUtil.useLNF();
    }

    // 这个实例化的执行一定在整个类以及执行完成才执行，与写的位置无关
    public static ConfigPanel instance = new ConfigPanel();

    // 监听组件
    // public 熟悉的组件会被监听
    JLabel LBudget = new JLabel("本月预算");
    public JTextField tfBudget = new JTextField("0");
    JLabel LMysql = new JLabel("Mysql安装路径");
    public JTextField tfMysqlPath = new JTextField("");

    // 设置更新MySQL路径的按钮
    JButton bSubmit = new JButton("更新");


    // 构造函数
    public ConfigPanel(){
        GUIUtil.setColor(ColorUtil.grayColor, LBudget,LMysql);
        GUIUtil.setColor(ColorUtil.blueColor, bSubmit);

        // 因为不需要监听Panel，所以放在构造函数内进行new
        // 2个，北方与中央布局
        JPanel pInput = new JPanel();
        JPanel pSubmit = new JPanel();
        // GridLayout需要设置gap
        int gap = 40;
        pInput.setLayout(new GridLayout(4,1,gap,gap));
        pInput.add(LBudget);
        pInput.add(tfBudget);
        pInput.add(LMysql);
        pInput.add(tfMysqlPath);

        pSubmit.add(bSubmit);

        this.setLayout(new BorderLayout());
        this.add(pInput, BorderLayout.NORTH);
        this.add(pSubmit,BorderLayout.CENTER);

        addListener();
    }

    public void addListener()
    {
        ConfigListener l = new ConfigListener();
        bSubmit.addActionListener(l);
    }

    public void updateData()
    {
        // 就更新两个数据就行
        String budget = new ConfigService().get(ConfigService.budget);
        String mysqlPath = new ConfigService().get(ConfigService.mysqlPath);
        tfBudget.setText(budget);
        tfMysqlPath.setText(mysqlPath);
        tfBudget.grabFocus();
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(ConfigPanel.instance);
    }


}
