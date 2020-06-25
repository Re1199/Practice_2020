package org.example.test.helloosgiclient;

import org.example.test.helloosgiservice.Greeting;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.*;

@Component(
        immediate = true
)
public class Client {
    private Greeting hello;

    @Reference(
            service = Greeting.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.STATIC,
            unbind = "unsetGreeting"
    )
    protected void setGreeter(Greeting hello) {
       this.hello = hello;
    }

    @Activate
    protected void onActivate() {
        hello.sayHello();
        System.out.println("Client activated");
    }

    protected void unsetGreeting() {
        this.hello = null;
    }
}