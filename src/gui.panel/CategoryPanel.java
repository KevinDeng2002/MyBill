package gui.panel;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

import entity.Category;
import gui.listener.CategoryListener;
import gui.model.CategoryTableModel;
import service.CategoryService;
import util.ColorUtil;
import util.GUIUtil;


// 看每类的消费数

public class CategoryPanel extends WorkingPanel{
    static {
        GUIUtil.useLNF();
    }

    public static CategoryPanel instance = new CategoryPanel();
    // 操作按键
    public JButton bAdd = new JButton("增加");
    public JButton bEdit = new JButton("编辑");
    public JButton bDelete = new JButton("删除");
    String[] columnNames = new String[]{"类别","消费次数"};

    // 初始化ctm
    public CategoryTableModel ctm = new CategoryTableModel();
    public JTable t = new JTable(ctm);

    public CategoryPanel(){
        GUIUtil.setColor(ColorUtil.blueColor,bAdd,bEdit,bDelete);
        // 设置上下两个面板
        // 这个是可以滑动的面板
        JScrollPane sp = new JScrollPane(t);
        JPanel pSubmit = new JPanel();
        pSubmit.add(bAdd);
        pSubmit.add(bEdit);
        pSubmit.add(bDelete);

        this.setLayout(new BorderLayout());
        this.add(sp, BorderLayout.CENTER);
        this.add(pSubmit,BorderLayout.SOUTH);

        addListener();

    }

    public static void main(String[] args) {
        GUIUtil.showPanel(CategoryPanel.instance);
    }

    // 获取JTable上当前选中的Category对象
    // 本质上都是：行号找对象，列号找属性
    public Category setSelectedCategory()
    {
        int index = t.getSelectedRow();
        // 找到cs（List）中的对象
        return ctm.cs.get(index);
    }

    // 增加一个updateData方法，
    // 用于在增加，删除，和修改之后，更新表格中的信息，并默认选中第一行。
    // 除此之外，还进行判断，如果表格里没有数据，删除和修改按钮不可使用。
    public void updateDate()
    {
        ctm.cs = new CategoryService().list();
        // 更新UI
        t.updateUI();
        t.getSelectionModel().setSelectionInterval(0,0);

        // 没有数据则不能编辑
        if(0 == ctm.cs.size())
        {
            bEdit.setEnabled(false);
            bDelete.setEnabled(false);
        }
        else
        {
            bEdit.setEnabled(true);
            bDelete.setEnabled(true);
        }
    }


    // 抽象类的应用--抽象类只提供方法名，在继承类中进行具体实现
    // 重写WorkingPanel的方法
    public void addListener(){
        CategoryListener listener = new CategoryListener();
        bAdd.addActionListener(listener);
        bEdit.addActionListener(listener);
        bDelete.addActionListener(listener);
    }

    public void updateData()
    {
        // 把所有的类取出来
        ctm.cs = new CategoryService().list();
        // 更新UI
        t.updateUI();
        t.getSelectionModel().setSelectionInterval(0,0);
        if(0==ctm.cs.size()){
            bEdit.setEnabled(false);
            bDelete.setEnabled(false);
        }
        else{
            bEdit.setEnabled(true);
            bDelete.setEnabled(true);
        }
    }
}
























