package com.zenika.academy.barbajavas.wordle.domain.controller;

public class ApiError {
    private int errorNum;
    private String errorMsg;

    public ApiError(int errorNum, String errorMsg) {
        this.errorNum = errorNum;
        this.errorMsg = errorMsg;
    }

    public int getErrorNum() {
        return errorNum;
    }

    public void setErrorNum(int errorNum) {
        this.errorNum = errorNum;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
