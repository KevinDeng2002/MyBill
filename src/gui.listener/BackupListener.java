package gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import entity.Config;
import gui.panel.BackupPanel;
import gui.panel.ConfigPanel;
import gui.panel.MainPanel;
import service.ConfigService;
import sun.applet.Main;
import util.MysqlUtil;

public class BackupListener implements ActionListener{

    public void actionPerformed(ActionEvent e)
    {
        BackupPanel p = BackupPanel.instance;

        // 获取路径
        String mysqlPath = new ConfigService().get(ConfigService.mysqlPath);
        if(0 == mysqlPath.length())
        {
            JOptionPane.showMessageDialog(p,"请先设置mysql路径");
            // 跳转
            MainPanel.instance.workingPanel.show(ConfigPanel.instance);
            ConfigPanel.instance.tfMysqlPath.grabFocus();
            return;
        }

        // 文件选择器
        // 指定保存数据的文件
        JFileChooser fc = new JFileChooser();
        fc.setSelectedFile(new File("mybill,sql"));

        // 过滤文件
        fc.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".sql");
            }

            @Override
            public String getDescription() {
                return ".sql";
            }
        });


        // 确认用户操作
        int returnVal = fc.showSaveDialog(p);
        File file = fc.getSelectedFile();
        System.out.println(file);
        // 按下保存键
        if(returnVal == JFileChooser.APPROVE_OPTION)
        {
            //如果保存的文件名没有以.sql结尾，自动加上.sql
            System.out.println(file);
            if(!file.getName().toLowerCase().endsWith(".sql"))
                file = new File(file.getParent(),file.getName()+".sql");
            System.out.println(file);

            // 进行备份
            try{
                MysqlUtil.backup(mysqlPath,file.getAbsolutePath());
                JOptionPane.showMessageDialog(p,"备份成功:\r\n" + file.getAbsolutePath());
            }catch (Exception e1)
            {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(p,"备份失败 \r\n" + e1.getMessage());
            }
        }
    }

}














