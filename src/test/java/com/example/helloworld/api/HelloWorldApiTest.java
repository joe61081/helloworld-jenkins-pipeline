package com.example.helloworld.api;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicLong;

public class HelloWorldApiTest {

    @Test
    public void sayHelloTest(){
        AtomicLong counter = new AtomicLong();
        Saying saying = new Saying(counter.incrementAndGet(), "test");
        assertEquals(1, saying.getId());
        assertEquals("test", saying.getContent());
    }
}
