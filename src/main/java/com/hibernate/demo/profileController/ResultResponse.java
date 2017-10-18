package com.hibernate.demo.profileController;

/**
 * @author wanli zhou
 * @created 2017-10-17 10:09 PM.
 */
public class ResultResponse {
    private String code;
    private String data;

    public ResultResponse(String code, String data) {
        this.code = code;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
