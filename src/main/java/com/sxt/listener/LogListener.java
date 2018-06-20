package com.sxt.listener;

import com.sxt.event.LogEvent;
import com.sxt.pojo.LogsPO;
import com.sxt.cfg.WebConfig;
import com.sxt.service.LogsDao;
import com.sxt.utils.RequestData;
import com.sxt.utils.ResponseData;
import com.sxt.utils.SnowflakeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class LogListener implements ApplicationListener<LogEvent> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LogsDao logsDao;

    @Autowired
    WebConfig webConfig;

    @Async
    @Override
    public void onApplicationEvent(LogEvent event) {

        if (webConfig.getLog2db() != null && webConfig.getLog2db().equals("true")) {

            Object obj = event.getSource();

            boolean sentEmail = false;

            try {

                LogsPO log = new LogsPO();
                log.setId(SnowflakeUtil.getInstance().nextId());
                log.setDate(new Date());
                RequestData requestData = event.getRequestData();

                if (requestData != null) {
                    log.setUrl(requestData.getUrl());
                    log.setHead(requestData.getHead());
                    log.setParam(requestData.getParam());
                    log.setIp(requestData.getIp());
                }

                ResponseData responseData = event.getResponseData();

                if (responseData != null) {
                    if (responseData.isSuccess()) {
                        log.setResult("Success");
                    } else {
                        log.setResult("Fail");
                        if (event.getPosition() != null) {
                            if (event.getPosition().equals("RuntimeException") || event.getPosition().equals("NullPointerException") || event.getPosition().equals("Exception") || event.getPosition().equals("ServiceException")) {
                                sentEmail = true;
                            }
                        }
                    }
                    log.setMsg(responseData.getMsg());
                    log.setException(responseData.getException());
                    log.setResponseData(responseData.toString());
                }
                logsDao.save(log);

                if (sentEmail) {

                    //FIXME 这里需要发送邮件
                }

            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }
}
