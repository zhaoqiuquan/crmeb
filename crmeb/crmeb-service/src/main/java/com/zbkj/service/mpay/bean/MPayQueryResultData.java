package com.zbkj.service.mpay.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class MPayQueryResultData  extends MPayBase {

    private Long merchantOrderId;
    private Long merchantId;
    private String merchantOrderNo;
    private BigDecimal orderAmount;
    private Date finishTime;
    private OrderState orderState;
    private String timestamp;
    private String signType;
    private String sign;
}
