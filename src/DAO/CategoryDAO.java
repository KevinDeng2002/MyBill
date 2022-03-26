package DAO;

// 此文件与RecordDAO类似
// 其实都是在对类做操作
// 把类在Java和sql之间进行传递

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entity.Category;
import util.DBUtil;

public class CategoryDAO {

    // 获取总督条目数
    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select count(*) from category";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
            System.out.println("total: " + total);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    // 添加条目
    // 可能出现异常
    // c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)
    // 目前暂未异常
    public void add(Category category) {
        String sql = "insert into category values(null,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, category.name);
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                category.id = id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 更新,根据Id
    public void update(Category category) {
        String sql = "update category set name= ? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            // 为sql提供参数
            ps.setString(1, category.name);
            ps.setInt(2, category.id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "delete from category where id = " + id;
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 获取
    public Category get(int id)
    {
        Category category = null;
        try(Connection c = DBUtil.getConnection();Statement s = c.createStatement();)
        {
            String sql = "select * from category where id = " + id;
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()){
                category = new Category();
                String name = rs.getString(2);
                category.name = name;
                category.id = id;
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return category;
    }

    // 下面是将类存储在list中
    public List<Category> list(){
        return list(0,Short.MAX_VALUE);
    }

    public List<Category> list(int start, int count)
    {
        List<Category> cs = new ArrayList<Category>();

        String sql = "select * from category order by id desc limit ?,? ";
        try(Connection c = DBUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql);)
        {
            ps.setInt(1,start);
            ps.setInt(2,count);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Category category = new Category();
                int id = rs.getInt(1);
                String name = rs.getString(2);
                category.id = id;
                category.name = name;
                cs.add(category);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return cs;
    }

    public static void main(String[] args) {
        CategoryDAO x = new CategoryDAO();
        x.delete(1);
    }
}

























