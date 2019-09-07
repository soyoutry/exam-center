package com.boss.bes.exam.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateToString {
    public static List<String> convert(List<Date> dateList){
        List<String> stringList = new ArrayList<>();
        stringList.add(0,DateFormatUtil.formatToDatetime(dateList.get(0)));
        stringList.add(1,DateFormatUtil.formatToDatetime(dateList.get(1)));
        return stringList;
    }
}
