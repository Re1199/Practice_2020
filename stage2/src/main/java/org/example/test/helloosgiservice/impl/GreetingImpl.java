package org.example.test.helloosgiservice.impl;

import org.example.test.helloosgiservice.Greeting;

public class GreetingImpl implements Greeting {
    final String m_name;

    public GreetingImpl(String name) {
        m_name = name;
    }

    public void sayHello() {
        System.out.println("Hello " + m_name + "!");
    }
}