package service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import DAO.RecordDAO;
import com.sun.org.apache.xml.internal.security.signature.reference.ReferenceOctetStreamData;
import entity.Record;
import util.DateUtil;


public class ReportService {
    // 获取某天的消费金额
    // 把本月的消费记录找出来，再看哪些是今天的，加起来即可
    public int getDaySpend(Date d,List<Record> monthRawData)
    {
        int daySpend = 0;
        for(Record record : monthRawData)
        {
            if(record.date.equals(d))
            {
                daySpend += record.spend;
            }
        }
        return daySpend;
    }

    // 因此我们需要把本月的消费金额找出来
    public List<Record> ListThisMonthRecords()
    {
        // 使用服务来获取MySQL中的数据
        RecordDAO dao = new RecordDAO();
        // 取出本月所有原数据
        List<Record> monthRawData = dao.listThisMonth();
        List<Record> res = new ArrayList<>();
        // 辅助参数
        Date monthBegin = DateUtil.monthBegin();
        int monthTotalDay = DateUtil.DaysofThisMonth();
        // 设置Calendar便于进行时间戳的设置
        Calendar c =Calendar.getInstance();
        for(int i=0;i<monthTotalDay;++i)
        {
            Record r = new Record();
            c.setTime(monthBegin);
            c.add(Calendar.DATE,i);
            // 定位至这一天（次循环遍历本月每一天）
            Date eachDayOfThisMonth = c.getTime();
            // 得到当天花的钱
            int daySpend = getDaySpend(eachDayOfThisMonth,monthRawData);
            r.spend = daySpend;
            res.add(r);
        }
        return res;
    }
}














