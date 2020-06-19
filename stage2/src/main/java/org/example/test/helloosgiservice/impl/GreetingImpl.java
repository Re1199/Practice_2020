package org.example.test.helloosgiservice.impl;

import org.example.test.helloosgiservice.Greeting;

public class GreetingImpl implements Greeting {
    final String mName;

    public GreetingImpl(String name) {
        mName = name;
    }

    public void sayHello() {
        System.out.println("Hello " + mName + "!");
    }
}