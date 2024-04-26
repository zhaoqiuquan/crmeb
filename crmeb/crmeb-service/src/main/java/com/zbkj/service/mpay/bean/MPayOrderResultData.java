package com.zbkj.service.mpay.bean;

import lombok.Data;

/**
 * @Author zqq
 */
@Data
public class MPayOrderResultData extends MPayBase {
    private boolean success;
    private String msg;
    private String timestamp;
    private String payInfoType;
    private Object payInfo;
    private String signType;
    private String sign;
}
