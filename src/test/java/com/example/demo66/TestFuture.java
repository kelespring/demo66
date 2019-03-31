package com.example.demo66;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class TestFuture {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        CompletableFuture<String> future = CompletableFuture.supplyAsync(()->{
            System.out.println("去干活，待会儿回来！");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "我回来了";
        },executor);

        future.thenAccept((e)->{
            System.out.println("--->"+e);
        });
        executor.shutdown();

        System.out.println("--->"+22);
    }
}
