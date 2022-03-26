package service;

// 不难看出就是写业务的【逻辑】
// 使用的就是DAO提供的原语

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import DAO.CategoryDAO;
import DAO.RecordDAO;
import entity.Category;
import entity.Record;
import gui.model.CategoryTableModel;

public class CategoryService {
    // 这两类有关系，所以需要同时实例化
    CategoryDAO categorydao = new CategoryDAO();
    RecordDAO recorddao = new RecordDAO();

    // 服务1
    // 查询出所有的Category，然后根据每种分类，再把分类对应的消费记录总数查出来，
    // 并且设置在对应分类实例的recordNumer上。
    //最后再根据recordNumer进行倒排序，让消费频率高的分类放在前面。
    public List<Category> list()
    {
        // 把所有类别都取出来
        List<Category> cs = categorydao.list();
        // 查询每个类别对应的消费记录总数(并非金额)
        for(Category c : cs)
        {
            // cid为外键，从Category里直接查询到
            // 使用者无需担心
            List<Record> rs = recorddao.list(c.id);
            c.recordNumber = rs.size();
        }
        // 从高到低
        Collections.sort(cs,(c1,c2)->c2.recordNumber-c1.recordNumber);
        return cs;
    }

    // 服务2
    // 增加一种分类
    public void add(String name)
    {
        Category c = new Category();
        c.setName(name);
        categorydao.add(c);  // 调用服务DAO
    }

    // 服务3
    // 更改某个分类的名称
    public void update(int id,String name)
    {
        Category c = new Category();
        c.setName(name);
        c.setId(id);
        categorydao.update(c);
    }

    // 服务4
    // 删除某个分类
    public void delete(int id)
    {
        categorydao.delete(id);
    }
}
















