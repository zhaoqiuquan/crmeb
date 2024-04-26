package com.zbkj.service.mpay.bean;

import com.zbkj.service.mpay.util.MPayUtil;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class MPayQueryParam extends MPayBase {

    private Long merchantId; //商户号
    private String merchantOrderNo;//商户订单号
    private String timestamp;//时间字符串 yyyyMMddHHmmss
    private String signType;
    private String sign;

    public MPayQueryParam(String merchantOrderNo) {
        this.merchantId = MPayConfig.merchantId;
        this.merchantOrderNo = merchantOrderNo;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        this.signType = MPayConfig.signType;
        this.sign = MPayUtil.sign(this.getFieldMap(), MPayConfig.signKey);
    }
}
