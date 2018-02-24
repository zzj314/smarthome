package io.github.futurewl.smarthome.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Base64 工具
 * 利用 Java 8 的 Base64 工具
 *
 * @author weilai
 */
public final class Base64Util {
    /**
     * 解码
     *
     * @param value 值
     * @return 返回值
     */
    public static String decode(String value, String charsetName) throws UnsupportedEncodingException {
        return new String(Base64.getDecoder().decode(value), charsetName);
    }

    /**
     * 编码
     *
     * @param value 值
     * @return 返回值
     */
    public static String encode(String value, String charsetName) {
        try {
            return Base64.getEncoder().encodeToString(value.getBytes(charsetName));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
