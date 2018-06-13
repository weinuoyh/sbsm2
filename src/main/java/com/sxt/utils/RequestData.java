package com.sxt.utils;


public class RequestData implements java.io.Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -8600102062469129327L;

    private String url; //

    private String head; //

    private String param;

    private String ip; //

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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "RequestData{" +
                "url='" + url + '\'' +
                ", head='" + head + '\'' +
                ", param='" + param + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
