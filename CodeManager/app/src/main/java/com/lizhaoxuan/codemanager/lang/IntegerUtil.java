package com.lizhaoxuan.codemanager.lang;

import java.util.List;

/**
 * 整型工具类
 */
public class IntegerUtil {

    /**
     * 把String转换为Int
     * @param num
     * @param max 设置最大值，如果解析出来的int超过最大值就返回最大值，null代表最大值不限制
     * @return
     */
    public static int parseInt(String num, Integer max){
        int resultNum = 0;
        try {
            resultNum = Integer.parseInt(num);
            if(null != max){
                resultNum = resultNum < max ? resultNum : max;
            }
        }catch (Exception e){

        }
        return resultNum;
    }

    /**
     * 取出Int集合中的最大值
     * @param nums
     * @return
     */
    public static Integer parserMaxInt(List<Integer> nums){
        int max = 0;
        if(null != nums && nums.size() > 0){
            for (int i = 0; i < nums.size(); i++) {
                if(i == 0){
                    max = nums.get(i);
                }else{
                    if(nums.get(i) > max){
                        max = nums.get(i);
                    }
                }
            }
            return max;
        }
        return null;
    }
}
