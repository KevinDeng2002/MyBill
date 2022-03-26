package DAO;

// ConfigDAO专门用于把Config实例与Config表进行ORM映射。
// ConfigDAO负责把Config实例转换成一条Config表中的记录，
// 反过来，又把Config表中的记录转换为一个Config实例。

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.sun.xml.internal.ws.api.ha.StickyFeature;
import entity.Config;
import sun.awt.IconInfo;
import sun.util.resources.cldr.dz.CalendarData_dz_BT;
import util.DBUtil;

import javax.swing.*;

public class ConfigDAO {

    // 获取总条目数
    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select count(*) from config";

            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                // 获取行数 columnIndex （唯2从1开始的指标）
                total = rs.getInt(1);
            }
            System.out.println("total: " + total);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    // 添加实例
    // 可能出现异常
    // c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)
    public void add(Config config) {

        String sql = "insert into config values(null,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, config.key);
            ps.setString(2, config.value);
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                config.id = id;
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void update(Config config) {
        String sql = "update config set key_= ?, value=? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, config.key);
            ps.setString(2, config.value);
            ps.setInt(3, config.id);

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // 按照id删除
    public void delete(int id) {
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "delete from config where id = " + id;
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 根据id拿数据
    public Config get(int id) {
        Config config = null;
        // 必须下链接mysql才能执行命令
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select * from config " + id;
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                // 承载体
                config = new Config();
                // 拿数据
                String key = rs.getString("key_");
                String value = rs.getString("value");
                config.key = key;
                config.value = value;
                config.id = id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return config;
    }

    // 初始化一个存放类的list
    public List<Config> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<Config> list(int start,int count)
    {
        List<Config> configs = new ArrayList<Config>();
        String sql= "select * from config order by id desc limit?,?";

        try(Connection c = DBUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql);)
        {
            ps.setInt(1,start);
            ps.setInt(2,count);

            // 已经预编译，不需要再加入sql
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                Config config = new Config();
                int id = rs.getInt(1);
                String key = rs.getString("key_");
                String value = rs.getString("value");
                config.id = id;
                config.value = value;
                config.key = key;
                configs.add(config);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return configs;
    }


    // 按照键值获取
    public Config getByKey(String key)
    {
        Config config = null;   // 初始化对象
        String sql = "select * from config where key_ = ?";
        try(Connection c = DBUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql);)
        {
            ps.setString(1,key);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                config = new Config();
                int id = rs.getInt("id");
                String value = rs.getString("value");
                config.key = key;
                config.value = value;
                config.id = id;
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return config;
    }

    // test
    public static void main(String[] args) {
        ConfigDAO x = new ConfigDAO();
    }
}