package com.zbkj.service.mpay.bean;

import lombok.Data;

/**
 * @Author zqq
 */
@Data
public class MPayOrderResult {
    private boolean success;
    private String msg;
    private MPayOrderResultData data;
}
