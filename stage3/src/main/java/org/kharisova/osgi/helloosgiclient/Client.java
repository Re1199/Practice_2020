package org.kharisova.osgi.helloosgiclient;

import org.kharisova.osgi.helloosgiservice.Greeting;
import org.osgi.service.component.annotations.*;


@Component(
        immediate = true
)
public class Client {
    @Reference(
            service = Greeting.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.STATIC,
            bind = "binder",
            unbind = "unbinder"
    )
    private Greeting hello;

    protected void binder(Greeting greeting){
        this.hello = greeting;
    }

    @Activate
    protected void onActivate() {
        System.out.println("Client activated");
        hello.sayHello();
    }

    protected void unbinder() {
        this.hello = null;
    }
}