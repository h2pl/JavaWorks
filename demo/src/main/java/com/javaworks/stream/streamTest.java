package com.javaworks.stream;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Intellij IDEA.
 * User:  penglong.huang
 * Date:  2021/12/1
 */
public class streamTest {
    private static final Map<String, Object> finalMap = new HashMap<>();
    private static final List<String> finalList = new ArrayList<>();

    static {
        finalMap.put("1", "asdasd");
        finalMap.put("2", "asdasd");
        finalMap.put("3", "adas");
        finalMap.put("4", "ads");
        finalMap.put("5", "eqwe");
        finalMap.put("6", "ewt");
        finalMap.put("7", "ert");
        finalMap.put("8", "bx");

        finalList.addAll(Lists.newArrayList("12", "12", "asd", "asdasd", "eqeqwedqad"));
    }

    public static void main(String[] args) {

        System.out.println("getStream");
        System.out.println("--------------");
        getStream();
        System.out.println("middleOperate");
        System.out.println("--------------");
        middleOperate();

        groupAndReduce();
    }

    private static void getStream() {
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream(); //获取一个顺序流
        Stream<String> parallelStream = list.parallelStream(); //获取一个并行流
    }

    private static void middleOperate() {
        Stream<Integer> stream = Stream.of(6, 4, 6, 7, 3, 9, 8, 10, 12, 14, 14);

        Stream<Integer> newStream = stream.filter(s -> s > 5) //6 6 7 9 8 10 12 14 14
                .distinct() //6 7 9 8 10 12 14
                .skip(2) //9 8 10 12 14
                .limit(2); //9 8
        newStream.forEach(System.out::println);


        List<String> list = Arrays.asList("a,b,c", "1,2,3");

        //将每个元素转成一个新的且不带逗号的元素
        Stream<String> s1 = list.stream().map(s -> s.replaceAll(",", ""));
        s1.forEach(System.out::println); // abc  123

        Stream<String> s3 = list.stream().flatMap(s -> {
            //将每个元素转换成一个stream
            String[] split = s.split(",");
            Stream<String> s2 = Arrays.stream(split);
            return s2;
        });
        s3.forEach(System.out::println); // a b c 1 2 3

        finalList.stream().flatMap(new Function<String, Stream<?>>() {
            @Override
            public Stream<?> apply(String s) {
                return Stream.of(s, s);
            }
        });
    }

    static class Person {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    private static void groupAndReduce() {

        List<Person> people = Lists.newArrayList(new Person("aa", 4), new Person("bb", 3), new Person("bb", 5), new Person("cc", 2), new Person("dd", 1));
        Map<String, Person> map;
        map = people.parallelStream()
                .collect(Collectors.toMap(Person::getName, Function.identity(), (c1, c2) -> c1.getAge() > c2.getAge() ? c1 : c2));
        System.out.println(map);

    }
}
