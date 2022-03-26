package util;

// 此文件已经无法使用
// 因为高版本的mysql禁用了相关的功能
// 这里仅作学习，不做实现

import java.io.BufferedReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MysqlUtil {
    public static void backup(String mysqlPath, String backupfile) throws IOException
    {
        String commandline = "\"%s/bin/mysqldump.exe\" -u%s -p%s   -hlocalhost   -P%d %s -r \"%s\"";
        String command = String.format(commandline,mysqlPath,DBUtil.loginName,DBUtil.passwd,DBUtil.port
        ,DBUtil.database,backupfile);
        Runtime.getRuntime().exec(command);
    }

    public static void recover(String mysqlPath,String recoverfile)
    {
        try{
            // 执行命令
            String commandline = "\"%s/bin/mysql.exe\" -u%s -p%s   %s ";
            String command = String.format(commandline,mysqlPath, DBUtil.loginName, DBUtil.passwd,
                    DBUtil.database);
            Process p = Runtime.getRuntime().exec(command);

            OutputStream out = p.getOutputStream();
            String inStr;
            StringBuffer sb = new StringBuffer("");
            String outStr;
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(recoverfile), "utf8"));

            // 按行读取
            while((inStr = br.readLine()) != null)
            {
                sb.append(inStr + "\r\n");
            }
            outStr = sb.toString();

            OutputStreamWriter writer = new OutputStreamWriter(out,"utf8");
            writer.write(outStr);
            writer.flush();
            out.close();
            br.close();
            writer.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {
        //
    }

}










