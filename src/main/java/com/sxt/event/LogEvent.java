package com.sxt.event;

import com.sxt.utils.RequestData;
import com.sxt.utils.ResponseData;
import org.springframework.context.ApplicationEvent;

/**
 * 日志监听器
 */
public class LogEvent extends ApplicationEvent {

    private ResponseData responseData;

    private String position;

    private RequestData requestData;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source
     * @param requestData
     * @param responseData
     * @param position
     */
    public LogEvent(Object source, RequestData requestData, ResponseData responseData, String position) {
        super(source);
        this.responseData = responseData;
        this.requestData = requestData;
        this.position = position;
    }

    public ResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public RequestData getRequestData() {
        return requestData;
    }

    public void setRequestData(RequestData requestData) {
        this.requestData = requestData;
    }

    @Override
    public String toString() {
        return "LogEvent{" +
                "  responseData=" + responseData +
                ", position='" + position + '\'' +
                ", requestData=" + requestData +
                '}';
    }
}
