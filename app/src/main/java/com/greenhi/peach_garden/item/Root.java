package com.greenhi.peach_garden.item;

public class Root<T> {
    private String code;
    private String msg;
    private T data;
    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return this.msg;
    }
    public void setResult(T data){
        this.data = data;
    }
    public T getResult(){
        return this.data;
    }
}
