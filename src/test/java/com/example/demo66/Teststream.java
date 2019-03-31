package com.example.demo66;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Teststream {
    @Test
    public void testcj(){
        List<Integer> list1=new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        List<Integer> list2=new ArrayList<>();
        list2.add(3);
        list2.add(4);
        list2.add(5);

        System.out.println("====1-2求差集===");
        List<Integer> list=list1.stream().filter(t-> !list2.contains(t)).collect(toList());
        list.stream().forEach(System.out::println);

        System.out.println("====2-1求差集===");
        list=list2.stream().filter(t-> !list1.contains(t)).collect(toList());
        list.stream().forEach(System.out::println);
    }

    @Test
    public void test1(){
        List<Integer> list1=new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        List<Integer> list2=new ArrayList<>();
        list2.add(3);
        list2.add(4);
        list2.add(5);

        System.out.println("====求交集===");

        List<Integer> list=list1.stream().filter(t->list2.contains(t)).collect(toList());
        list.stream().forEach(System.out::println);




        System.out.println("====求差集===");
        list=list1.stream().filter(t-> !list2.contains(t)).collect(toList());
        list.stream().forEach(System.out::println);


        System.out.println("====求并集===");

        list.addAll(list1);
        list.addAll(list2);
        list=list.stream().distinct().collect(toList());
        list.stream().forEach(System.out::println);
    }

    /**
     * List<AccountVo> list = response.getData().getItems();
     * 本地查询出来的账户集合
     *
     * List<Account> towList = accountRepository.findAll();
     * 筛选差集代码
     *
     * List<AccountVo> distinctByUniqueList = list.stream()
     *         .filter(item -> !towList.stream()
     *         .map(e -> e.getOwnerId())
     *         .collect(Collectors.toList())
     *         .contains(item.getOwnerId()))
     *         .collect(Collectors.toList());
     *
     * ---------------------
     * 作者：baidu_37302589
     * 来源：CSDN
     * 原文：https://blog.csdn.net/baidu_37302589/article/details/86594630
     * 版权声明：本文为博主原创文章，转载请附上博文链接！
     */

    public void test2(){
        List<String> list1 = new ArrayList<String>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("5");
        list1.add("6");

        List<String> list2 = new ArrayList<String>();
        list2.add("2");
        list2.add("3");
        list2.add("7");
        list2.add("8");

        // 交集
        List<String> intersection = list1.stream().filter(item -> list2.contains(item)).collect(toList());
        System.out.println("---交集 intersection---");
        intersection.parallelStream().forEach(System.out :: println);

        // 差集 (list1 - list2)
        List<String> reduce1 = list1.stream().filter(item -> !list2.contains(item)).collect(toList());
        System.out.println("---差集 reduce1 (list1 - list2)---");
        reduce1.parallelStream().forEach(System.out :: println);

        // 差集 (list2 - list1)
        List<String> reduce2 = list2.stream().filter(item -> !list1.contains(item)).collect(toList());
        System.out.println("---差集 reduce2 (list2 - list1)---");
        reduce2.parallelStream().forEach(System.out :: println);

        // 并集
        List<String> listAll = list1.parallelStream().collect(toList());
        List<String> listAll2 = list2.parallelStream().collect(toList());
        listAll.addAll(listAll2);
        System.out.println("---并集 listAll---");
        listAll.parallelStream().forEachOrdered(System.out :: println);

        // 去重并集
        List<String> listAllDistinct = listAll.stream().distinct().collect(toList());
        System.out.println("---得到去重并集 listAllDistinct---");
        listAllDistinct.parallelStream().forEachOrdered(System.out :: println);

        System.out.println("---原来的List1---");
        list1.parallelStream().forEachOrdered(System.out :: println);
        System.out.println("---原来的List2---");
        list2.parallelStream().forEachOrdered(System.out :: println);
    }


}
