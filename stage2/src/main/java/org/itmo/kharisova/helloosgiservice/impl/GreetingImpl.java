package org.itmo.kharisova.helloosgiservice.impl;

import org.itmo.kharisova.helloosgiservice.Greeting;

public class GreetingImpl implements Greeting {
    final String mName;

    public GreetingImpl(String name) {
        mName = name;
    }

    public void sayHello() {
        System.out.println("Hello " + mName + "!");
    }
}