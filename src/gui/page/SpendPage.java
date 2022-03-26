package gui.page;

// 消费一览面板上需要什么信息，这个类就封装什么信息。

public class SpendPage {
    //本月消费
    public String monthSpend;
    //今日消费
    public String todaySpend;
    //日均消费
    public String avgSpendPerDay;
    //本月剩余
    public String monthAvailable;
    //日均可用
    public String dayAvgAvailable;
    //距离月末
    public String monthLeftDay;
    //使用比例
    public int usagePercentage;
    //是否超支
    public boolean isOverSpend = false;

    // 初始化
    public SpendPage(int monthSpend, int todaySpend, int avgSpendPerDay, int monthAvailable, int dayAvgAvailable,
                     int monthLeftDay, int usagePercentage)
    {
        this.monthSpend = "￥" + monthSpend;
        this.todaySpend = "￥" + todaySpend;
        this.avgSpendPerDay = "￥" + avgSpendPerDay;

        // 超支计算
        if(monthAvailable < 0)
        {
            isOverSpend = true;
        }

        if(!isOverSpend)
        {
            this.monthAvailable = "￥" + monthAvailable;
            this.dayAvgAvailable = "￥" + dayAvgAvailable;
        }
        else
        {
            this.monthAvailable = "超支" + (0 - monthAvailable);
            this.dayAvgAvailable = "￥0";
        }

        this.monthLeftDay = monthLeftDay + "天";
        this.usagePercentage = usagePercentage;
    }

}















