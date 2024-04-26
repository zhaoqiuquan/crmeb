package com.zbkj.service.mpay.util;



import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.TreeMap;

/**
 * @Author zqq
 */
public class MPayUtil {

    public static String sign(TreeMap<String, Object> paramMap, String key) {
        StringBuffer str = new StringBuffer();
        for (Map.Entry<String, Object> item : paramMap.entrySet()) {
            if ("sign".equals(item.getKey()) || "signType".equals(item.getKey())
                    || item.getValue() == null
                    || (item.getValue() instanceof String && StringUtils.isBlank((String) item.getValue()))) {
                continue;
            }
            str.append(item.getKey()).append("=").append(item.getValue()).append("&");
        }
        str.append("key=").append(key);
        return DigestUtils.md5Hex(str.toString()).toLowerCase();
    }

    public static boolean verifySign(TreeMap<String, Object> paramMap, String key) {
        String beVerifySign = (String) paramMap.get("sign");
        String sign = sign(paramMap, key);
        return beVerifySign.equals(sign);
    }

}
