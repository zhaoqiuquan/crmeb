package com.zbkj.service.mpay.bean;

import lombok.Data;

@Data
public class MPayQueryResult {

    private boolean success;
    private String msg;
    private MPayQueryResultData data;


}
