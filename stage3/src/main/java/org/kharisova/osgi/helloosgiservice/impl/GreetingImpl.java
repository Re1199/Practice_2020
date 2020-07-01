package org.kharisova.osgi.helloosgiservice.impl;

import org.kharisova.osgi.helloosgiservice.Greeting;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

@Component(
        service = Greeting.class,
        immediate = true
)
public class GreetingImpl implements Greeting {
    public void sayHello() {
        System.out.println("Hello, OSGi!");
    }

    @Activate
    protected void onActivate() {
        System.out.println("Service activated");
    }
}

