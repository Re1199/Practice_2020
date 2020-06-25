package org.example.test.helloosgiservice.impl;

import org.example.test.helloosgiservice.Greeting;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

@Component(
        service=Greeting.class,
        immediate = true
)
public class GreetingImpl implements Greeting {
    /*
    final String mName;

    public GreetingImpl(String name) {
        mName = name;
    }
     */

    public void sayHello() {
        //System.out.println("Hello " + mName + "!");
        System.out.println("Hello, OSGi!");
    }

    /*
    @Activate
    public void activate() {
        Greeting service = new GreetingImpl("OSGi World");
        service.sayHello();
    }
     */
}