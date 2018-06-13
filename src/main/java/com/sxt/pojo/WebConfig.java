package com.sxt.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "web")
public class WebConfig {

    private String secrent;

    private String log2db;

    public String getSecrent() {
        return secrent;
    }

    public void setSecrent(String secrent) {
        this.secrent = secrent;
    }

    public String getLog2db() {
        return log2db;
    }

    public void setLog2db(String log2db) {
        this.log2db = log2db;
    }
}
