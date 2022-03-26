package gui.panel;


import javax.swing.JButton;
import javax.swing.JPanel;

import gui.listener.RecoveryListener;
import util.ColorUtil;
import util.GUIUtil;

import java.awt.*;

public class RecoverPanel extends WorkingPanel{
    static {
        GUIUtil.useLNF();
    }

    public static RecoverPanel instance = new RecoverPanel();

    JButton bRe = new JButton("恢复");

    public RecoverPanel(){
        GUIUtil.setColor(ColorUtil.blueColor,bRe);
        this.add(bRe);
        addListener();
    }

    @Override
    public void updateData() {
        // TODO Auto-generated method stub

    }

    @Override
    public void addListener() {
        RecoveryListener listener = new RecoveryListener();
        bRe.addActionListener(listener);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(RecoverPanel.instance);
    }

}
