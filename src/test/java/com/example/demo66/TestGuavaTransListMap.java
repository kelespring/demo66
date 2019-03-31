package com.example.demo66;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class TestGuavaTransListMap {
    /**
     * list转换为字符串
     */

    public void joinTest(){
        List<String> names = Lists.newArrayList("John", "Jane", "Adam", "Tom");
        String result = Joiner.on(",").join(names);
        System.out.println(result.equals( "John,Jane,Adam,Tom"));
    }


    /**
     * map转换为字符串
     */

    public void whenConvertMapToString_thenConverted() {
        Map<String, Integer> salary = Maps.newHashMap();
        salary.put("John", 1000);
        salary.put("Jane", 1500);
        String result = Joiner.on(" , ").withKeyValueSeparator(" = ")
                .join(salary);
        System.out.println(result);
    }

    /**
     * list转String，跳过null
     */

    public void whenConvertListToStringAndSkipNull_thenConverted() {
        List<String> names = Lists.newArrayList("John", null, "Jane", "Adam", "Tom");
        String result = Joiner.on(",").skipNulls().join(names);
        System.out.println(result);
        System.out.println(result.equals( "John,Jane,Adam,Tom"));
    }

    /**
     * list转String，将null变成其他值
     */

    public void whenUseForNull_thenUsed() {
        List<String> names = Lists.newArrayList("John", null, "Jane", "Adam", "Tom");
        String result = Joiner.on(",").useForNull("nameless").join(names);
        System.out.println(result);


        System.out.println(result.equals( "John,nameless,Jane,Adam,Tom"));
    }

    /**
     * String to List
     */

    public void whenCreateListFromString_thenCreated() {
        String input = "apple - banana - orange";
        List<String> result = Splitter.on("-").trimResults().splitToList(input);
        System.out.println(result);

    }

    /**
     * String to Map
     */

    public void whenCreateMapFromString_thenCreated() {
        String input = "John=first,Adam=second";
        Map<String, String> result = Splitter.on(",")
                .withKeyValueSeparator("=")
                .split(input);
        System.out.println("first= "+result.get("John"));
        System.out.println("second= "+ result.get("Adam"));
    }

    /**
     * 多个字符进行分割
     */

    public void whenSplitStringOnMultipleSeparator_thenSplit() {
        String input = "apple.banana,,orange,,.";
        List<String> result = Splitter.onPattern("[.|,]")
                .omitEmptyStrings()
                .splitToList(input);
        System.out.println(result);
    }

    /**
     * 每隔多少字符进行分割
     */

    public void whenSplitStringOnSpecificLength_thenSplit() {
        String input = "Hello world";
        List<String> result = Splitter.fixedLength(3).splitToList(input);
        System.out.println(result);
    }

    /**
     * 限制分割多少字后停止
     */

    public void whenLimitSplitting_thenLimited() {
        String input = "a,b,c,d,e";
        List<String> result = Splitter.on(",")
                .limit(4)
                .splitToList(input);

        System.out.println(4==result.size());
        System.out.println(result);
    }



}
