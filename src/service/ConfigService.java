package service;

import DAO.ConfigDAO;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import entity.Config;

import java.util.SplittableRandom;

public class ConfigService {

    public static final String budget = "budget";
    public static final String mysqlPath = "mysqlPath";
    public static final String default_budget = "500";

    static ConfigDAO dao = new ConfigDAO();
    static {
        init();
    }

    public static void init()
    {
        init(budget,default_budget);
        init(mysqlPath,"");
    }

    private static void init(String key,String val)
    {
        // 获取这个对象
        Config config = dao.getByKey(key);
        // 能获取则获取
        // 不能的话就创建一个并且加入到mysql中
        if(config == null)
        {
            Config c = new Config();
            c.setKey(key);
            c.setValue(val);
            dao.add(c);
        }
    }

    public String get(String key)
    {
        Config config = dao.getByKey(key);
        return config.getValue();
    }

    public void update(String key,String val)
    {
        Config config = dao.getByKey(key);
        // 重设对象的值
        config.setValue(val);
        // 更新MySQL的存储值
        dao.update(config);
    }

    public int getIntBudget()
    {
        return Integer.parseInt(get(budget));
    }

}










