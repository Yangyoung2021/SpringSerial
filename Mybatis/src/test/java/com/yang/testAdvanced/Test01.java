package com.yang.testAdvanced;

import org.junit.jupiter.api.Test;

public class Test01 {

    @Test
    void backNum() {
        boolean result = noString(1001);
        System.out.println(result);
    }

    private boolean testBackNum(int x) {
        String numStr = String.valueOf(x);
        int len = numStr.length();
        if (len == 1) {
            return true;
        }
        int mid = len / 2;
        int count = 1;
        if (len % 2 == 1) {
            //长度为奇数
            while (mid >= count) {
                if (numStr.charAt(mid - count) != numStr.charAt(mid + count)) {
                    return false;
                }
                count++;
            }
        } else {
            //长度为偶数
            while (mid >= count) {
                if (numStr.charAt(mid - count) != numStr.charAt(mid + count - 1)) {
                    return false;
                }
                count++;
            }
        }
        return true;
    }


    boolean noString(int x) {
        if (x < 0) return false;
        int source = x;
        int result = 0;
        int temp;
        while (x / 10 > 0) {
            temp = x % 10;
            result = (temp + result) * 10;
            x = x / 10;
        }
        result += x;
        return result == source;
    }
}
