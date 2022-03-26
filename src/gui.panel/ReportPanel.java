package gui.panel;

// 创建月消费报表
// ReportPanel使用的是ChartUtil现成的方法生成了当月的模拟数据。

import static util.GUIUtil.showPanel;

import java.awt.BorderLayout;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import entity.Record;
import service.ReportService;
import util.ChartUtil;
import util.GUIUtil;

public class ReportPanel extends WorkingPanel{
    static {
        GUIUtil.useLNF();
    }

    public static ReportPanel instance = new ReportPanel();

    public JLabel l = new JLabel();

    public ReportPanel(){
        this.setLayout(new BorderLayout());
        Image i = ChartUtil.getImage(400,300); // shou barchart
        ImageIcon icon = new ImageIcon(i);
        l.setIcon(icon);
        this.add(l);
        addListener();
    }

    @Override
    public void addListener()
    {
        //不需要这个具体实现
    }

    @Override
    public void updateData()
    {
        List<Record> rs = new ReportService().ListThisMonthRecords();
        // 把数据传入，生成图像
        Image i = ChartUtil.getImage(rs,350,350);
        ImageIcon icon = new ImageIcon(i);
        l.setIcon(icon);
    }


    public static void main(String[] args) {
        GUIUtil.showPanel(ReportPanel.instance);
    }
}












