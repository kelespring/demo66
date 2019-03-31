package com.example.demo66;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;

import java.text.SimpleDateFormat;
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
}


