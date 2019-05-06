package com.example.demo66;

import com.google.common.base.Equivalence;
import com.google.common.collect.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class TestJava8Stream {
    static List<People> peopleList = new ArrayList<People>(){
        {
            Random random = new Random();
            for (int i = 0; i < 1000; i++) {
                add(new People("name"+random.nextInt(50) ,random.nextInt(50) ));
            }
        }
    };


    //流排序
    static void sort(){
        peopleList.stream()
                .sorted(Comparator.comparing(People::getAge)
                        .reversed()).forEach(System.out::println);
    }
    //流过滤，流转换类型
    static void transferMap(){
        List<String> names = peopleList.stream().filter(x->x.age>20).map(x->x.name+x.age).collect(toList());
        names.forEach(System.out::println);
    }

    static void collections(){
        //不可用的空集合
        Collections.emptyList();//返回空list
        Collections.emptyMap();//返回空Map
        Collections.emptySet();//返回空set
        //google guava
        List<Integer> list = Lists.newArrayList(1, 2, 3);//构造List
        Lists.newLinkedList();//构造LinkedList
        Maps.newHashMap();//构造hashMap
        Multimap<String,People> multimap = ArrayListMultimap.create();
        multimap.put("a",new People("kevin",1));
        Multiset<Integer> multiSet = HashMultiset.create();
        multiSet.add(1);
        multiSet.count(1);//统计1的个数


    }

    //包裹生成list
    static void warp(){
        List<Integer> list = new ArrayList<>();
        Integer[] arr = {1, 2, 3};
        Collections.addAll(list, arr);
    }

    //集合比较
    @Test
    public void guawa(){
        HashSet setA = Sets.newHashSet(1, 2, 3, 4, 5);
        HashSet setB = Sets.newHashSet(4, 5, 6, 7, 8);
        Sets.SetView<Integer> union = Sets.union(setA,setB);
        //并集
        for (Integer integer : union)
            System.out.println(integer);

        Sets.SetView<Integer> diff = Sets.difference(setA,setB);
        //差集
        for (Integer integer : diff)
            System.out.println("--"+integer);

        //交集
        Sets.SetView<Integer> intersection = Sets.intersection(setA,setB);
        for (Integer integer : intersection)
            System.out.println(integer);

    }

    //合并
    static void concatCollections(){
        List<Integer> listA = Lists.newArrayList();
        List<Integer> listB = Lists.newArrayList();
        List<Integer> result =
                Stream.concat(listA.stream(), listB.stream())
                        .distinct()
                        .collect(Collectors.toList());
    }

    @Test
    public void testSet(){
        Set s = new HashSet();
        Map map_d = new HashMap();
        map_d.put("name","lilei");
        map_d.put("age",11);
        Map map_e = new HashMap();
        map_e.put("name","wangj");
        map_e.put("age",12);


        Map map = new HashMap();
        map.put("lilei",map_d);
        map.put("wangj",map_e);



        Map map_f = new HashMap();
        map_f.put("name","lisi");
        map_f.put("age",13);
        Map map_g = new HashMap();
        map_g.put("name","sjdj");
        map_g.put("age",12);

        Map map4 = new HashMap();
        map4.put("lisi",map_f);
        map4.put("sjdj",map_g);

        MapDifference diff = Maps.difference(map4, map, new Equivalence() {

            @Override
            protected boolean doEquivalent(Object a, Object b) {
                Map a_map = (Map) a;
                Map b_map = (Map) b;
                int age_a = (Integer) a_map.get("age");
                int age_b = (Integer) b_map.get("age");
                return age_a == age_b;
            }

            @Override
            protected int doHash(Object o) {
                Map a_map = (Map) o;
                int age_a = (Integer) a_map.get("age");

                return age_a;
            }
        });
        Map diff_map =  diff.entriesDiffering();
        System.out.println("dddd"+diff_map.size());

        diff_map.forEach((key,v)->{
            System.out.println(v);
        });

//        s.add(new People("kevin",10));
//        s.add(new People("kevin",11));
//        Set s2 = new HashSet();
//        s2.add(new People("kevin",10));
//        s2.add(new People("kevin",12));

//        Set<Map> diff =  Sets.difference(s2,s);
//        diff.forEach(r->{
//            System.out.println(r.get("age"));
//        });
    }


    /**
     * 取Map集合的并集
     *
     * @param map1 大集合
     * @param map2 小集合
     * @return 两个集合的并集
     */
    public static Map<String, Object> getUnionSetByGuava(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> bigMapKey = map1.keySet();
        Set<String> smallMapKey = map2.keySet();
        Set<String> differenceSet = Sets.union(bigMapKey, smallMapKey);

        Map<String, Object> result = Maps.newHashMap();
        for (String key : differenceSet) {
            if (map1.containsKey(key)) {
                result.put(key, map1.get(key));
            } else {
                result.put(key, map2.get(key));
            }
        }
        return result;
    }

    /**
     * 取Map集合的差集
     *
     * @param bigMap   大集合
     * @param smallMap 小集合
     * @return 两个集合的差集
     */
    public static Map<String, Object> getDifferenceSetByGuava(Map<String, Object> bigMap, Map<String, Object> smallMap) {
        Set<String> bigMapKey = bigMap.keySet();
        Set<String> smallMapKey = smallMap.keySet();
        Set<String> differenceSet = Sets.difference(bigMapKey, smallMapKey);
        Map<String, Object> result = Maps.newHashMap();
        for (String key : differenceSet) {
            result.put(key, bigMap.get(key));
        }
        return result;
    }

    /**
     * 取Map集合的交集（String,String）
     *
     * @param map1 大集合
     * @param map2 小集合
     * @return 两个集合的交集
     */
    public static Map<String, Object> getIntersectionSetByGuava(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> bigMapKey = map1.keySet();
         Set<String> smallMapKey = map2.keySet();
        Set<String> differenceSet = Sets.intersection(bigMapKey, smallMapKey);
        Map<String, Object> result = Maps.newHashMap();
        for (String key : differenceSet) {
            result.put(key, map1.get(key));
        }
        return result;
    }

    @Test
    public void testList(){
        // 交集
        List<String> listA_01 = new ArrayList<String>(){{
            add("A");
            add("B");
        }};
        List<String> listB_01 = new ArrayList<String>(){{
            add("B");
            add("C");
        }};
        listA_01.retainAll(listB_01);
        System.out.println(listA_01); // 结果:[B]
        System.out.println(listB_01); // 结果:[B, C]

// 差集
        List<String> listA_02 = new ArrayList<String>(){{
            add("A");
            add("B");
        }};
        List<String> listB_02 = new ArrayList<String>(){{
            add("B");
            add("C");
        }};
        listA_02.removeAll(listB_02);
        System.out.println(listA_02); // 结果:[A]
        System.out.println(listB_02); // 结果:[B, C]

// 并集
        List<String> listA_03 = new ArrayList<String>(){{
            add("A");
            add("B");
        }};
        List<String> listB_03 = new ArrayList<String>(){{
            add("B");
            add("C");
        }};
        listA_03.removeAll(listB_03);
        listA_03.addAll(listB_03);
        System.out.println(listA_03); // 结果:[A, B, C]
        System.out.println(listB_03); // 结果:[B, C]
    }

    @Test
    public void testStream(){
        List<Student> students = new ArrayList<Student>();
        Student student = new Student("张三","女","11");
        Student student2 = new Student("张三","男","11");
        Student student3 = new Student("张三","男","11");
        Student student4 = new Student("李四","男","11");
        Student student5 = new Student("李四","男","11");
        Student student6 = new Student("王五","男","11");
        students.add(student);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
        students.add(student6);
//        Map<String,List<Student>> map = students.stream().collect(Collectors.groupingBy(Student :: getName));
//        map.forEach((k,v) -> {
//            System.out.println("k = "+ k + "||" +"v = " + v.stream().collect(Collectors.groupingBy(Student :: getSex)).size());
//        });

        Map<Student, List<Student>> map = students.stream()
                .collect(Collectors.groupingBy(f -> new Student(f.getName(),f.getSex())));

        map.forEach((o, o2) -> {
            List<Student> studentss = o2;
            System.out.println(o);
            System.out.println("---------------start----------------");

            studentss.forEach(s -> System.out.println(s));
            System.out.println("---------------end----------------");

        });
    }
}


@Data
@AllArgsConstructor
 class People {
    String name;
    int age;
}

@Data
@AllArgsConstructor
class Student {
    String name;
    String sex;
    String age;

    public Student(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }
}
