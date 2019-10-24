package com.gnnt.mybatisgenerator.jdk8;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JDK8Test {
    private List<Student> list = Lists.newArrayList(
        new Student("001","张珊珊", 89,20, Student.Status.FREE),
            new Student("002","张三", 59,19,Student.Status.BUSY),
            new Student("003","李四", 90,17,Student.Status.BUSY),
            new Student("004","王五", 70,22,Student.Status.FREE),
             new Student("004","王五", 70,23,Student.Status.GOOD)
           );
    @Test
    public void test_stream(){
        /**
         * 中间操作
         *  筛选 fliter
         */
        list.stream().filter(

                (e) -> e.getScore()>60

        ).forEach(
                System.out::println
        );

        System.out.println("--------findFirst---------");
        //打印列表的第一个
        Optional<Student> first = list.stream().findFirst();
        first.ifPresent(System.out::println);

        System.out.println("-------limit----------");
        //limit 打印前两个
        list.stream().limit(2).forEach(System.out::println);

        System.out.println("--------skip---------");
        //skip 跳过第一个，到最后 与limit互补
        list.stream().skip(1).forEach(System.out::println);

        System.out.println("--------distinct---------");
        //distinct 去重 需重写集合中元素的hashcode和equals方法
        list.stream().distinct().forEach(System.out::println);

        System.out.println("--------map---------");
        /**
         * 映射
         */
        //map 映射出一个新的流 内容为每个学生的名字
        list.stream().map(Student::getName).forEach(System.out::println);
        //将list集合转换为map  key：学生编号 value:当前学生 下面这种方式如果有重复的key会报错
        //Map<String, Student> map = list.stream().collect(Collectors.toMap(Student::getCode, Function.identity()));
        //改为这种方式 针对重复的key, 覆盖之前的value
        Map<String, Student> map =list.stream().collect(Collectors.toMap(Student::getCode, Function.identity(),(key,value) -> value));
        map.forEach((key,value) -> System.out.println("key="+key+" value="+value));




    }
    @Test
    public void test_sort(){
        /**
         * 排序
         *  1、自然排序（comparable）
         *  2、定制排序（comparator）
         */
        List<String> list1 = Arrays.asList("a","c","f","e","b","d");
        System.out.println("-----------自然排序sorted------------");
        list1.stream().sorted().forEach(System.out::println);

        System.out.println("-----------定制排序sorted------------");
        list.stream().sorted((s1,s2) -> s1.getCode().compareTo(s2.getCode())).forEach(System.out::println);
        //先按照学号排，如果学号一样按照年龄排，sorted 里面接收的是Comparator.compare，实际返回为int,因此不可以排double类型。
        list.stream().sorted((s1,s2) -> {
            if(s1.getCode().equals(s2.getCode())){
                return s1.getAge().compareTo(s2.getAge());
            }else{
                return s1.getCode().compareTo(s2.getCode());
            }

        }).forEach(System.out::println);

        System.out.println("-----------定制排序sorted 排double------------");
        //按照分数排序,这种方式可排double
        list.stream().sorted((s1,s2) -> Double.compare(s1.getScore(),s2.getScore())).forEach(System.out::println);
        list.stream().sorted( Comparator.comparing(Student::getScore)).forEach(System.out::println);
    }

    @Test
    public void test_match(){
        /**
         * anyMatch--检查是否至少匹配一个元素
         * allMatch---检查是否匹配所有元素
         * noneMatch---检查是否没有匹配所有元素
         * findFirst---- 返回第一个元素
         * findAny----返回当前流中的第一个元素
         * count------返回流中元素的总个数
         * max-------返回流中的最大值
         * min-------返回流中最小值
         */
        //查找学生中是否有三好学生
        boolean result = list.stream().anyMatch(student -> student.getStatus().equals(Student.Status.GOOD));
        System.out.println("anyMatch----"+result);
        //查找学生中是否都在学习
        boolean allMatch = list.stream().allMatch(student -> student.getStatus().equals(Student.Status.BUSY));
        System.out.println("allMatch----"+allMatch);
        //检查学生中是否都没有在玩
        boolean noneMatch = list.stream().noneMatch(student -> student.getStatus().equals(Student.Status.FREE));
        System.out.println("noneMatch----"+noneMatch);
        
        //按照分数排序，取分数最低的学生
        Optional<Student> first = list.stream().sorted(Comparator.comparing(Student::getScore)).findFirst();
        System.out.println("findFirst-----"+first.get());
        
        //取分数最高的学生
        Optional<Student> findFirst = list.stream().sorted((s1,s2) -> - Double.compare(s1.getScore(),s2.getScore())).findFirst();
        System.out.println("findFirst-----"+findFirst.get().toString());

        //获取学生中偷玩学生的个数
        long count = list.stream().filter(student -> student.getStatus().equals(Student.Status.FREE)).count();
        System.out.println("count-----"+count);
        
        //通过max获取分数最高的学生
        Optional<Student> max = list.stream().max((s1, s2) -> Double.compare(s1.getScore(), s2.getScore()));
        System.out.println("max-----"+max.get().toString());

        //通过min获取分数最低的学生
        Optional<Student> min = list.stream().min((s1, s2) -> Double.compare(s1.getScore(), s2.getScore()));
        System.out.println("min-----"+min.get().toString());

        //获取学生中任意一个空闲的学生去扫地
        Optional<Student> any = list.stream().filter(student -> student.getStatus().equals(Student.Status.FREE)).findAny();
        System.out.println("any-----"+any.get().toString());

        //获取学生中最低分数是多少
        Optional<Double> min1 = list.stream().map(Student::getScore).min(Double::compare);
        System.out.println("min1-----"+min1.get().toString());

    }
    @Test
    public void test_reduce(){
        /**
         * reduce(T identity,BinaryOperator) /reduce(BinaryOperator) --可以将流中的元素返回结合起来，得到一个值
         */
        //相当于计算从1到10的和
        List<Integer> list1 = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        //reduce(identity) 第一个参数是起始值
        Integer reduce = list1.stream().reduce(0, (x, y) -> x + y);
        System.out.println("reduce----"+reduce);

        //计算学生的分数总和
        Optional<Double> reduce1 = list.stream().map(Student::getScore).reduce(Double::sum);
        System.out.println("reduce-----"+reduce1.get());
    }
    @Test
    public void test_collect(){
        /**
         * 收集：
         * collect---将流转换为其他形式，接受一个Collector接口的实现，用于给Stream中的元素做汇总方法。
         */
        //计算学生分数的总和
        Double collect = list.stream().collect(Collectors.summingDouble(Student::getScore));
        System.out.println("collect----"+collect);

        //计算学生的平均分数
        Double averagingDouble = list.stream().collect(Collectors.averagingDouble(Student::getScore));
        System.out.println("averagingDouble----"+averagingDouble);

        //计算学生的最大分数的学生
        Optional<Student> maxBy = list.stream().collect(Collectors.maxBy((s1, s2) -> Double.compare(s1.getScore(), s2.getScore())));
        System.out.println("maxBy----"+maxBy.get().toString());

        //计算学生中最小的分数
        Optional<Double> minBy = list.stream().map(Student::getScore).collect(Collectors.minBy(Double::compare));
        System.out.println("minBy----"+minBy.get());

        System.out.println("--------groupingBy---------");

        //分组
        //按照状态分组
        Map<Student.Status, List<Student>> groupingBy = list.stream().collect(Collectors.groupingBy(Student::getStatus));
        groupingBy.forEach((key,value) -> System.out.println("key="+key + " value="+value));

        Map<Student, Long> groupingBy2 = list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        groupingBy2.forEach((k,v) -> System.out.println("key="+k.toString()+" value="+v));

        //多级分组
        Map<Student.Status, Map<String, List<Student>>> groupingBy3 = list.stream().collect(Collectors.groupingBy(Student::getStatus, Collectors.groupingBy(
                e -> {
                    if (((Student) e).getScore() < 60) {
                        return "及格";
                    } else if (((Student) e).getScore() >= 90) {
                        return "优秀";
                    } else {
                        return "良好";
                    }
                }
        )));
        System.out.println("groupingBy3----"+groupingBy3.toString());
        
        //分区
        Map<Boolean, List<Student>> partitioningBy = list.stream().collect(Collectors.partitioningBy(e -> e.getScore()>= 90));
        System.out.println("partitioningBy----"+partitioningBy.toString());

        DoubleSummaryStatistics doubleSummaryStatistics = list.stream().collect(Collectors.summarizingDouble(Student::getScore));
        System.out.println("sum"+doubleSummaryStatistics.getSum());
        System.out.println("min"+doubleSummaryStatistics.getMin());
        System.out.println("max"+doubleSummaryStatistics.getMax());
        System.out.println("ave"+doubleSummaryStatistics.getAverage());
        System.out.println("count"+doubleSummaryStatistics.getCount());

    }
}
class Student{
    private String code;
    private String name;
    private double score;
    private Integer age;
    private Status status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Student(String code, String name, double score, Integer age, Status status) {
        this.code = code;
        this.name = name;
        this.score = score;
        this.age = age;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Student{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", score=" + score +
                ", age=" + age +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Double.compare(student.score, score) == 0 &&
                age == student.age &&
                Objects.equals(code, student.code) &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, score, age);
    }
    enum Status{
        BUSY,
        FREE,
        GOOD;
    }
}
