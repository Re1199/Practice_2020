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
    @Reference(
            service = Greeting.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.STATIC,
            unbind = "unsetGreeting"
    )
    private Greeting hello;

    @Activate
    protected void onActivate() {
        System.out.println("Client activated");
        hello.sayHello();
    }

    protected void unsetGreeting() {
        this.hello = null;
    }
}