package com.banyuan.blog.utils;

import org.springframework.stereotype.Component;

@Component
public class MyUtils {
    public int random(int start, int end) throws RuntimeException {
        double a = (double)start;
        double b = (double)end;
        if (start > end) {
            throw new RuntimeException("start must less than end");
        }
        double r = Math.random();
        return (int) ((r + a / (b - a)) * (b - a));
    }

    public Character getRandomAlphabet(){
        char a = (char) random(97, 123);
        if (random(0, 10) % 2 == 0) {
            return Character.toLowerCase(a);
        } else {
            return Character.toUpperCase(a);
        }
    }

    public String generateVCode(int length, Boolean includeEnglisAlphabet) {
        StringBuilder code = new StringBuilder("");
        if (includeEnglisAlphabet) {
            for (int i = 0; i < length; i++) {
                if (random(0, 10) % 2 == 0) {
                    code.append(getRandomAlphabet());
                }else {
                    code.append(random(0,10));
                }
            }
        }else {
            for (int i=0; i<length;i++){
                code.append(random(0,10));
            }
        }
        return code.toString();
    }
}
