package com.zbkj.service.mpay.bean;

/**
 * @Author zqq
 */
public class MPayConfig {
    public static Long merchantId = 5L;
    public static String signKey = "aaccff1b9a1450f45e59f82380d933fa";
    public static String payWay = "HEEPAY_QP";

    public static String signType = "MD5";

    public static String notifyUrl = "/api/admin/payment/callback/mpay";


    public static String apiUrl = "https://api.vvpayfor.com/api/order";
    public static String queryUrl = "https://api.vvpayfor.com/api/query";
}
