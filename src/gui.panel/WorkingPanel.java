package gui.panel;

// 我们发现：
// 1. 在设置面板ConfigPanel和分类面板CategoryPanel里都有addListener接口
// 2. 面板在显示的时候，都需要从数据库中读取信息，并显示在界面上，比如CategoryPanel的updateData方法。
// ConfigPanel虽然暂时没有updateData这么一个方法，
// 但是也是有这个需要的(当点击工具栏上的设置按钮的时候，也是需要把预算信息读取出来，并显示在面板上的)
// 可以预见到，后面的各种面板类，都有类似的功能需求。 这样，就可以抽象出一个WorkingPanel类，来提供这样的功能

// WorkingPanel是一个抽象类，其中声明了方法addListener()和updateData().
// 不同的面板类，都应该继承这个抽象类，进而必须提供addListener和updateData方法。

import javax.swing.*;

public abstract class WorkingPanel extends JPanel {
    public abstract void updateData();
    public abstract void addListener();
}
