package gui.listener;

// 监控”更新“按钮
// 1. 首先判断输入的预算值是否是整数
// 2. 接着判断输入的MYSQL安装路径是否正确。
// 判断办法是看路径对应的bin子目录下是否有mysql.exe文件存在
// 3. 如果上述两个判断都通过了，那么就调用业务类ConfigService进行数据更新
// C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.File;

import javax.naming.CompositeName;
import javax.swing.*;

import com.sun.corba.se.spi.ior.TaggedComponentFactoryFinder;
import entity.Config;
import gui.panel.ConfigPanel;
import jdk.nashorn.internal.scripts.JO;
import service.ConfigService;
import util.GUIUtil;

public class ConfigListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        ConfigPanel p = ConfigPanel.instance;
        if(!GUIUtil.checkNumber(p.tfBudget,"本月预算"))
            return;

        String mysqlPath = p.tfMysqlPath.getText();
        if(0 != mysqlPath.length())
        {
            // 唯一定位mysql的安装位置
            File commandFile = new File(mysqlPath,"bin/mysql.exe");
            if(!commandFile.exists())
            {
                // message提示框
                JOptionPane.showMessageDialog(p,"mysql路径错误");
                p.tfMysqlPath.grabFocus();
                return;
            }
        }

        ConfigService cs = new ConfigService();
        cs.update(ConfigService.budget,p.tfBudget.getText());
        cs.update(ConfigService.mysqlPath,mysqlPath);

        JOptionPane.showMessageDialog(p,"设置修改成功");
    }
}



















