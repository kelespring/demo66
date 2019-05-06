package com.example.demo66;

import com.google.common.base.Splitter;
import com.google.common.io.ByteStreams;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class TestGuava {


    @Test
    public void testGuava(){
        Iterable<String> join1 = Splitter.on("&").split("id=123&name=green");
        List<String> ss = Lists.newArrayList(join1);
        Map<String, String> join2 = Splitter.on("&").withKeyValueSeparator("=").split("id=123&name=green");

        System.out.println(ss);
    }

    @Test
    public void testDemo(){
        String delimiter= "|";
        String filePath = "20190418175454077001/Application (1).xlsx";
        String docFilePath = "20190419110047713015/Application (1).xlsx|20190419110048577016/Application (2).xlsx|20190419110049544017/Application (3).xlsx|20190419110050406018/Application (4).xlsx";
//        List<String> newFilePath = Arrays.stream(docFilePath.split(delimiter)).collect(Collectors.toList());
        List<String> oldFilePathListTmp =  com.google.common.collect.Lists.newArrayList(Splitter.on(delimiter).split(docFilePath));
//        ByteStreams.limit();
//        String newFilePath = Arrays.stream(docFilePath.split(delimiter)).filter(item->!item.equals(filePath)).collect(Collectors.joining(delimiter));
    }
}
