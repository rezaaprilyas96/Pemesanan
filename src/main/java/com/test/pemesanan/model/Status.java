package com.test.pemesanan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Status {

    @JsonProperty("status")
    private String status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private Object data;

    @JsonProperty("data")
    private List<Object> datas;

    public Status() {
    }

    public Status(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public Status(String status, String message, List<Object> datas) {
        this.status = status;
        this.message = message;
        this.datas = datas;
    }

    public Status(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("data")
    public Object getObject() {
        return data;
    }

    @JsonProperty("data")
    public void setObject(Object data) {
        this.data = data;
    }
}
