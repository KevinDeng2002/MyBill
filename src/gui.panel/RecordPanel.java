package gui.panel;

// 设置消费记录panel
// 此处会用到下拉栏和日期控件

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Test.JTextFieldTest;
import entity.Category;
import entity.Record;
import gui.listener.RecordListener;
import org.jdesktop.swingx.JXDatePicker;

import gui.model.CategoryComboBoxModel;
import service.CategoryService;
import util.ColorUtil;
import util.GUIUtil;


public class RecordPanel extends WorkingPanel{
    // 皮肤放前面
    static {
        GUIUtil.useLNF();
    }

    // 单例模式
    public static RecordPanel instance = new RecordPanel();

    // 设置所有需要监听的组件
    // 1.设置label
    JLabel lSpend = new JLabel("花费(￥)");
    JLabel lCategory = new JLabel("分类");
    JLabel lComment = new JLabel("备注");
    JLabel lDate = new JLabel("日期");

    // 2.设置JTextField
    // 将输入的数据有用JTextField读取
    public JTextField tfSpend = new JTextField("0");
    public JTextField tfComment = new JTextField();

    // 3.日期控件的设置
    public JXDatePicker datepicker = new JXDatePicker(new Date());

    // 4.设置cbModel组件
    // 详见CSDN
    // 简单的来说:
    // JComboBox，下拉列表框。JComboBox以下列列表的形式展示多个选项，用户可以从下拉列表中选择一个值。
    // 其中的选项内容由一个 ComboBoxModel 实例来维护
    // 所以说我们先构建了一个Model，并把我们需要的栏位值写了进去
    // (Model文件的编写比较符合规范)
    public CategoryComboBoxModel cbModel = new CategoryComboBoxModel();
    public JComboBox<Category> cbCategory = new JComboBox<>(cbModel);

    // 5.设置Button
    JButton bSubmit = new JButton("记录该次消费");


    // 构造函数初始化
    public RecordPanel(){
        // 构造函数内设置不需要监听的组件
        // 1.设置颜色
        GUIUtil.setColor(ColorUtil.grayColor, lSpend,lCategory,lComment,lDate);
        GUIUtil.setColor(ColorUtil.blueColor, bSubmit);

        // 2.设置面板
        // 总体来说是上下两个
        JPanel pInput = new JPanel();
        JPanel pSubmit = new JPanel();
        // 随之设置布局
        int gap = 40;
        pInput.setLayout(new GridLayout(4,2,gap,gap));

        // 3.把label以及其他组件加到panel上
        // 添加顺序为：左-->右  上-->下
        // 上方panel
        pInput.add(lSpend);
        pInput.add(tfSpend);
        pInput.add(lCategory);
        pInput.add(cbCategory);
        pInput.add(lComment);
        pInput.add(tfComment);
        pInput.add(lDate);
        pInput.add(datepicker);

        // 下方panel
        pSubmit.add(bSubmit);

        // 4.添加到这个实例上
        // 相当于这个实例本身就是一个大的panel
        this.setLayout(new BorderLayout());
        this.add(pInput,BorderLayout.NORTH);
        this.add(pSubmit,BorderLayout.CENTER);

        addListener();
    }

    public Category getSelectedCategory()
    {
        return (Category) cbCategory.getSelectedItem();
    }

    public void updateData()
    {
        cbModel.cs = new CategoryService().list();
        cbCategory.updateUI();
        resetInput();
        tfSpend.grabFocus();
    }

    public void resetInput(){
        tfSpend.setText("0");
        tfComment.setText("0");
        if(0 != cbModel.cs.size())
        {
            cbCategory.setSelectedIndex(0);
        }
        datepicker.setDate(new Date());
    }

    public void addListener()
    {
        RecordListener listener = new RecordListener();
        bSubmit.addActionListener(listener);
    }

    // 测试
    public static void main(String[] args) {
        GUIUtil.showPanel(RecordPanel.instance);
    }
}











