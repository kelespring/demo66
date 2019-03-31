package com.example.demo66;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo66Application {

    public static void main(String[] args) {
        for(int i =0;i<100;i++){
            System.out.println(i);
            test();
            System.out.println(i);

        }
        SpringApplication.run(Demo66Application.class, args);
    }
    public static void test(){
        System.out.println(22);
        System.out.println(33);

    }

}
