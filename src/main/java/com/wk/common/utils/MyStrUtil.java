package com.wk.common.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyStrUtil {

    /**
     * 获取UUID
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static boolean strIsNotEmpty(String str) {
        if (str == null) {
            return false;
        }
        return !"".equals(str) && str.length() != 0;
    }

    public static boolean strEmpty(String str) {
        return str == null || str.length() == 0;
    }


    /**
     * 时间转换：
     * String 转为 long
     */

    public static long stringToLong(String strTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        long time = 0;
        try {
            time = formatter.parse(strTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return time;
    }

    public static String listToStr(List<String> list) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; list.size() > i; i++) {
            if (list.size() - 1 == i) {
                stringBuffer.append(list.get(i));
            } else {
                list.size();
                stringBuffer.append(list.get(i)).append(",");
            }
        }
        return stringBuffer.toString();
    }

    public static List<String> strToList(String str) {
        List<String> list = new ArrayList<>();
        if (!strIsNotEmpty(str)) {
            return list;
        }
        String[] strs = str.split(",");
        list.addAll(Arrays.asList(strs));
        return list;
    }


    /**
     * @Description: 方法一：通过list去重
     * @Param: [arrStr]
     * @return: java.lang.String[]
     * @Author: WanKai
     * @Date: 2019/6/27
     */
    public static String[] arrRemoveRepeat(String[] arrStr) {
        List<String> list = new ArrayList<String>();
        for (String s : arrStr) {
            if (!list.contains(s)) {
                list.add(s);
            }
        }
        //返回一个包含所有对象的指定类型的数组
        return list.toArray(new String[1]);
    }

    /**
     * @Description: 方法二：通过map去重
     * @Param: [arrStr]
     * @return: java.lang.String[]
     * @Author: WanKai
     * @Date: 2019/6/27
     */
    public static String[] removeRepeat(String[] arrStr) {
        Map<String, Object> map = new HashMap<String, Object>();
        for (String str : arrStr) {
            map.put(str, str);
        }
        //返回一个包含所有对象的指定类型的数组

        return map.keySet().toArray(new String[1]);
    }


    public static boolean phoneNumberCheck(String phoneNumber) {
        // "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        String regex = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }

    public static void main(String[] args) {
        //System.out.println(StringUtils.phoneNumberCheck("16627958110"));
        System.out.println(MyStrUtil.generateId("U"));
    }

    public static String generateId(String prefix) {
        int num = new Random().nextInt(8999) + 1000;
        Date date = new Date();
        StringBuffer sb = new StringBuffer();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddssSSS");
        sb.append(prefix);
        sb.append(dateFormat.format(date));
        sb.append(num);
        return sb.toString();
    }

    public static String rtrim(String str, String fix) {
        if (str.trim().endsWith(fix)) {
            int index = str.lastIndexOf(fix);
            return str.substring(0, index);
        } else {
            return str;
        }
    }

    public static String rtrim(String str, String fixOne, String fixTwo) {
        return rtrim(rtrim(str, fixOne), fixTwo);
    }

    public static StringBuilder rtrim(StringBuilder str, String fixOne, String fixTwo) {
        return rtrim(rtrim(str, fixOne), fixTwo);
    }

    public static StringBuilder rtrim(StringBuilder str, String fix) {
        if (str.toString().trim().endsWith(fix)) {
            int index = str.lastIndexOf(fix);
            return new StringBuilder(str.substring(0, index));
        } else {
            return str;
        }
    }

    /**
     * @param name 事件名或属性名
     */
    public static boolean blackWordCheck(String name) {
        boolean flag = false;
        String black_word = "date datetime distinct_id event events first_id id original_id device_id properties second_id time user_id users";
        if (black_word.contains(name))
            flag = true;
        String black_word_start_with = "user_group user_tag";
        String[] split_black = black_word_start_with.split(" ");
        for (String black : split_black) {
            if (name.startsWith(black)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
