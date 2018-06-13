package com.sxt.service.impl;

import com.sxt.pojo.LogsPO;
import com.sxt.service.LogsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: 胡秀亮
 * Date: 2018/3/9
 * Time: 上午11:50
 * 描述:  com.zdlh.web.dao
 */
@Component
public class LogsDaoImpl implements LogsDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(LogsPO log) {
        mongoTemplate.save(log);
    }
}
