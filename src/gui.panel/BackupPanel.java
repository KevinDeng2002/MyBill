package gui.panel;

import javax.swing.*;

import gui.listener.BackupListener;
import util.ColorUtil;
import util.GUIUtil;

public class BackupPanel extends WorkingPanel{
    // 直接把这个按键放上去即可
    static {
        GUIUtil.useLNF();
    }

    public static BackupPanel instance = new BackupPanel();

    JButton bBackup = new JButton("备份");

    public BackupPanel(){
        GUIUtil.setColor(ColorUtil.blueColor,bBackup);
        // 继承了JPanel的类自身相当于已有一个Panel,此处直接添加即可
        this.add(bBackup);
        addListener();
    }

    public void updateData()
    {

    }

    public void addListener()
    {
        BackupListener listener = new BackupListener();
        bBackup.addActionListener(listener);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(BackupPanel.instance);
    }

}

