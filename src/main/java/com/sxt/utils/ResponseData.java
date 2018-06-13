package com.sxt.utils;


//@ApiModel
public class ResponseData<T> implements java.io.Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -8600102062469129327L;

    //@ApiModelProperty(value = "返回码",required = true)
    private Integer code; // 返回码

    // @ApiModelProperty(value = "返回提示信息")
    private String msg; // 返回提示信息

    //@ApiModelProperty(value = "返回异常提示信息")
    private String exception; // 返回异常提示信息

    //@ApiModelProperty(value = "返回数据")
    private T data; // 返回数据

    //@ApiModelProperty(value = "如果返回数据data是集合，返回集合长度，如果是分页查询 ，返回总记录数量")
    private Integer dataCount; //若干data是集合，返回集合长度

    public ResponseData() {
    }

    public ResponseData(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseData(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getDataCount() {
        return dataCount;
    }

    public void setDataCount(Integer dataCount) {
        this.dataCount = dataCount;
    }

    /**
     * 根据 code 码判断业务状态 code ==200，表示业务成功。其他表示业务失败，msg 进一步描述业务状态。
     */
    public boolean isSuccess() {
        if (code == null) {
            return false;
        }
        if (code.intValue() == 200) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", exception='" + exception + '\'' +
                ", data=" + data +
                ", dataCount=" + dataCount +
                '}';
    }

}
