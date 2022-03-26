package entity;

// 配置信息Config类与配置信息表 config相对应
// 其他两个也是如此，按着模板写即可

public class Config {
    public int id;
    public String key;
    public String value;

    // 每个元素我们定义一些基本设置
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getKey(){
        return key;
    }

    public void setKey(String key){
        this.key = key;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String val)
    {
        this.value = val;
    }

}
