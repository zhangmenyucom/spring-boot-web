package com.taylor.algo.dada;


/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/7/19 1:12
 */
public class Test {

    public static String revert(char[] source, int start, int end) {
        while (start < end) {
            char temp = source[start];
            source[start] = source[end];
            source[end] = temp;
            start++;
            end--;
        }
        System.out.println(String.valueOf(source));
        return String.valueOf(source);
    }

    public static String revertMessage(String message) {
        char[] source = message.toCharArray();
        revert(source, 0, source.length - 1);
        boolean start_flag = false;
        boolean end_flag = false;
        int start = 0;
        int end = 0;
        for (int i = 0; i < source.length; i++) {
            if (!Character.isSpace(source[i]) && !start_flag) {
                start = i;
                start_flag = true;
            }
            if (Character.isSpace(source[i]) && start_flag) {
                end = i - 1;
                end_flag = true;
            }
            if (start_flag && (end_flag || i == source.length - 1)) {
                if (i == source.length - 1) {
                    end = i;
                }
                revert(source, start, end);
                start_flag = false;
                end_flag = false;
            }
        }
        return String.valueOf(source);
    }

    public static void main(String[] args) {
        String str = "   how are you   ";
        revertMessage(str);

    }

}
