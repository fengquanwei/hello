package com.fengquanwei.hello.javase.concurrent.synchronize.lock.condition;

/**
 * 文件模拟类
 *
 * @author fengquanwei
 * @create 2018/1/27 13:46
 **/
public class FileMock {
    private static char[] chars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    private String[] content; // 存储文件内容
    private int index; // 内容的行号

    public FileMock(int size, int length) {
        content = new String[size];
        for (int i = 0; i < size; i++) {
            StringBuilder stringBuilder = new StringBuilder(length);
            for (int j = 0; j < length; j++) {
                int index = (int) (Math.random() * chars.length);
                stringBuilder.append(chars[index]);
            }
            content[i] = stringBuilder.toString();
        }
        index = 0;
    }

    public boolean hasMoreLines() {
        return index < content.length;
    }

    public String getLine() {
        if (this.hasMoreLines()) {
            return content[index++];
        }
        return null;
    }
}
