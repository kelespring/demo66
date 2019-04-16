package com.example.demo66;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

@Slf4j
public class TestDateTime {
    @Test
    public void testThreadSafeDatefomat(){
       String str =  DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
       log.info(str);
    }

    private static final ThreadLocal<SimpleDateFormat> dateFormatter = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return  new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    @Test
    public void testJava8(){
//        LocalTime localTime = LocalTime.of(17,3,18);
        LocalTime localTime = LocalTime.now();

        int hour = localTime.getHour();
        int minute = localTime.getMinute();
        int second = localTime.getSecond();
        int nano = localTime.getNano();
        log.info("hour:{},minute:{},second:{},nano{}",hour,minute,second,nano);
    }
}


