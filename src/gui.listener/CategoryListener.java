package gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import entity.Category;
import gui.panel.CategoryPanel;
import jdk.nashorn.internal.scripts.JO;
import service.CategoryService;


public class CategoryListener implements ActionListener{

    // 开始监听按钮
    @Override
    public void actionPerformed(ActionEvent e)
    {
        CategoryPanel p = CategoryPanel.instance;

        JButton b = (JButton) e.getSource();

        if(b == p.bAdd)
        {
            String name = JOptionPane.showInputDialog(null);
            if(0 == name.length())
            {
                JOptionPane.showMessageDialog(p,"类别名称不能为空");
                return ;
            }
            new CategoryService().add(name);
        }

        if(b == p.bEdit)
        {
            // 找到要修改的对象
            Category c = p.setSelectedCategory();
            int id = c.id;
            String name = JOptionPane.showInputDialog("修改类别名称",c.name);
            if(0 == name.length())
            {
                JOptionPane.showMessageDialog(p,"类别名称不能为空");
                return ;
            }
            new CategoryService().update(id,name);
        }

        if(b == p.bDelete)
        {
            Category c = p.setSelectedCategory();
            // 有消费记录，系统层面进展删除
            if(0 != c.recordNumber)
            {
                JOptionPane.showMessageDialog(p,"此类别下存在消费记录，不能删除（默认模式）\n" +
                        "若要删除，请先清空记录）");
                return ;
            }
            if(JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(p,"确认删除？"))
            {
                return;
            }
            int id = c.id;
            new CategoryService().delete(id);

        }
        p.updateDate();

    }

}
