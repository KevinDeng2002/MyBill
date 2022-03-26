package gui.panel;

// 主菜单

import java.awt.BorderLayout;

import javax.naming.ContextNotEmptyException;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

// 从util工具包中调用
import gui.listener.ToolBarListener;
import util.CenterPanel;    // 注意看这里引入了CenterPanel
// 意思是，MainPanel用的时CenterPanel的那一套函数
// 所以说后面改进MainPanel是修改CenterPanel的函数
import util.GUIUtil;

// MainPanel 是主窗体的ContentPanel，采用的是BorderLayerout的布局方式。
// 北边是一个工具条
// 中间是一个空白的Panel，用于将来显示不同的功能面板

public class MainPanel extends JPanel{
    // 在静态初始化块中调用LookAndFeel，设置水晶皮肤
    // 这段话必须放在最前面才能生效
    static {
        GUIUtil.useLNF();
    }
    // 单例模式，便于监听
    public static MainPanel instance = new MainPanel();
    public JToolBar tb = new JToolBar();
    public JButton bSpend = new JButton();
    public JButton bRecord = new JButton();
    public JButton bCategory = new JButton();
    public JButton bReport = new JButton();
    public JButton bConfig = new JButton();
    public JButton bBackup = new JButton();
    public JButton bRecover = new JButton();

    public CenterPanel workingPanel;
    private MainPanel(){
        // 这里的文件位置的设置在:GUIutil里面
        GUIUtil.setImageIcon(bSpend, "home.png", "消费一览");
        GUIUtil.setImageIcon(bRecord, "record.png", "记一笔");
        GUIUtil.setImageIcon(bCategory, "category2.png", "消费分类");
        GUIUtil.setImageIcon(bReport, "report.png", "月消费报表");
        GUIUtil.setImageIcon(bConfig, "config.png", "设置");
        GUIUtil.setImageIcon(bBackup, "backup.png", "备份");
        GUIUtil.setImageIcon(bRecover, "restore.png", "恢复");

        // 把三级加到二级上
        tb.add(bSpend);
        tb.add(bRecord);
        tb.add(bCategory);
        tb.add(bReport);
        tb.add(bConfig);
        tb.add(bBackup);
        tb.add(bRecover);
        // 这是指能否把这个栏位单独提出了，类似matlab的悬浮状态
        // 为了美观性设置为false
        tb.setFloatable(false);

        // 另一个工作面板，这里先不做实现
        workingPanel = new CenterPanel(0.8);
        // 这里直接调用自己继承的函数
        // 在instance那里体现出来
        setLayout(new BorderLayout());
        add(tb,BorderLayout.NORTH);
        add(workingPanel,BorderLayout.CENTER);

        addListener();
    }

    // 在实例化mainPanel时，就添加listener
    private void addListener(){
        ToolBarListener listener = new ToolBarListener();
        // 给需要的button添加
        bSpend.addActionListener(listener);
        bRecord.addActionListener(listener);
        bCategory.addActionListener(listener);
        bReport.addActionListener(listener);
        bConfig.addActionListener(listener);
        bBackup.addActionListener(listener);
        bRecover.addActionListener(listener);
    }

    // 测试函数
    public static void main(String[] args) {
        GUIUtil.showPanel(MainPanel.instance,1);
    }
}














