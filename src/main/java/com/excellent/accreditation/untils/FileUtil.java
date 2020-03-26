package com.excellent.accreditation.untils;

import java.util.Random;

/**
 * 判断图片文件大小的工具类
 */
public class FileUtil
{
    /**
     * 判断文件大小
     *
     * @param len  文件长度
     * @param size 限制大小
     * @param unit 限制单位（B,K,M,G）
     * @return
     */
    public static boolean checkFileSize(Long len, int size, String unit)
    {
        double fileSize = 0;
        if ("B".equals(unit.toUpperCase()))
        {
            fileSize = (double) len;
        }
        else if ("K".equals(unit.toUpperCase()))
        {
            fileSize = (double) len / 1024;
        }
        else if ("M".equals(unit.toUpperCase()))
        {
            fileSize = (double) len / 1048576;
        }
        else if ("G".equals(unit.toUpperCase()))
        {
            fileSize = (double) len / 1073741824;
        }
        if (fileSize > size)
        {
            return false;
        }
        return true;
    }

    /**
     * 获得随机字符串
     *
     * @param length  字符串程度
     * @return 随机字符串
     */
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
