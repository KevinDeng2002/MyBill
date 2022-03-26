package service;

import java.util.Date;
import DAO.RecordDAO;
import entity.Category;
import entity.Record;
import jdk.nashorn.internal.runtime.events.RecompilationEvent;

public class RecordService {
    // 记一笔消费
    RecordDAO recordDAO = new RecordDAO();
    // 传参：什么时候，在哪一类消费了多少钱，另外，还需要comment
    public void add(int spend,Category c,String comment,Date date)
    {
        Record r = new Record();
        r.spend = spend;
        r.cid = c.id;  // 通过外键，与Category表联系
        r.comment = comment;
        r.date = date;
        // 建类后调用DAO存入mysql5
        recordDAO.add(r);
    }
}
