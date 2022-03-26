package gui.listener;

// 给界面上的"记录该次消费“ 按钮添加监听
// 1. 首先判断是否有消费分类信息，如果没有提示先增加消费分类
// 2. 输入的金额不能为0
// 3. 调用RecordService的add添加消费记录
// 4. 提示添加成功
// 5. 添加成功后，切换到消费一览

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;

import entity.Category;
import entity.Record;
import gui.panel.CategoryPanel;
import gui.panel.MainPanel;
import gui.panel.RecordPanel;
import gui.panel.SpendPanel;
import service.RecordService;
import util.GUIUtil;

public class RecordListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        RecordPanel p = RecordPanel.instance;
        // 没有数据的话
        if(0==p.cbModel.cs.size())
        {
            JOptionPane.showMessageDialog(p,"暂无任何类别，请先添加类别");
            // 帮助自动跳转至种类面板进行种类的添加
            MainPanel.instance.workingPanel.show(CategoryPanel.instance);
            return ;
        }

        if(!GUIUtil.chechZero(p.tfSpend,"花费的金额"))
        {
            return ;
        }
        // 获取数据，使用服务
        // String转为int
        // 钱数
        int spend = Integer.parseInt(p.tfSpend.getText());
        // 需要category是因为需要外键
        Category c = p.getSelectedCategory();
        // 评论
        String comment = p.tfComment.getText();
        // 时间
        Date d = p.datepicker.getDate();
        new RecordService().add(spend,c,comment,d);
        JOptionPane.showMessageDialog(p,"添加成功");
        MainPanel.instance.workingPanel.show(SpendPanel.instance);
    }

}


















