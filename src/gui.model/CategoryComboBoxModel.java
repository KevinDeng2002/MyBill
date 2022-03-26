package gui.model;

// 设计下拉框(在RecordPanel中)

import entity.Category;
import service.CategoryService;

import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

public class CategoryComboBoxModel implements ComboBoxModel<Category>{
    // 选项栏，String的类别暂时为String
//    public List<String> cs = new ArrayList<>();
//    String c;  // 单个

//    // 构造方法
//    public CategoryComboBoxModel(){
//        cs.add("餐饮");
//        cs.add("交通");
//        cs.add("住宿");
//        cs.add("学习");
//        cs.add("娱乐");
//        c = cs.get(0);
//    }

    // 显示实时信息
    public List<Category> cs = new CategoryService().list();

    public Category c;

    public CategoryComboBoxModel()
    {
        // 显示第一个数据在框里
        if(!cs.isEmpty())
        {
            c = cs.get(0);
        }
    }


    // 实现抽象类

    @Override
    public int getSize() {
        return cs.size();
    }

    @Override
    public Category getElementAt(int index) {
        // TODO Auto-generated method stub
        return cs.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        // TODO Auto-generated method stub

    }

    // 当界面上选中了某一个下拉框项，就会调用这个方法
    @Override
    public void setSelectedItem(Object anItem) {
        c = (Category) anItem;

    }

    // 获取被选中的数据
    @Override
    public Object getSelectedItem() {
        // TODO Auto-generated method stub
        if(!cs.isEmpty())
        {
            return c;
        }
        else
        {
            return null;
        }
    }
}





