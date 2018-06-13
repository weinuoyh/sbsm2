package com.sxt.pojo;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: 胡秀亮
 * Date: 2018/3/9
 * Time: 上午11:51
 * 描述:  com.zdlh.web.po
 */
public class LogsPO {

    private Long id;

    private String url; //

    private String ip; //

    private String head; //

    private String param;

    private String result;

    private String msg;

    private String exception;

    private String responseData;

    private Date date;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "LogsPO{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", head='" + head + '\'' +
                ", param='" + param + '\'' +
                ", result='" + result + '\'' +
                ", msg='" + msg + '\'' +
                ", exception='" + exception + '\'' +
                ", responseData='" + responseData + '\'' +
                ", date=" + date +
                ", ip=" + ip +
                '}';
    }
}
