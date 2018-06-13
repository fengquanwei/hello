package com.fengquanwei.hello.util;

/**
 * 数字工具类
 *
 * @author fengquanwei
 * @create 2018/5/5 22:02
 **/
public class NumberUtil {
    /**
     * 获取 Integer 的二进制（补码）
     */
    public static String integer2Bits(Integer number) {
        if (number == null) {
            return null;
        }

        StringBuffer binaryString = new StringBuffer();
        for (int i = 0; i < 32; i++) {
            binaryString.append((number & (0x80000000 >>> i)) >>> (31 - i));
        }

        return binaryString.toString();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(integer2Bits(i));
        }
    }
}
