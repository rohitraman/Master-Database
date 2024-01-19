package com.example.master.database.bean;

public class Response {
    Object obj;
    int status;

    public Response(Object obj, int status) {
        this.obj = obj;
        this.status = status;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
