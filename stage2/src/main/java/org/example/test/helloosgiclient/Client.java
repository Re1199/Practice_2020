package org.example.test.helloosgiclient;

import org.example.test.helloosgiservice.Greeting;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Client implements BundleActivator {

    public void start(BundleContext ctx) {
        ServiceReference ref =
                ctx.getServiceReference(Greeting.class.getName());

        ((Greeting) ctx.getService(ref)).sayHello();
    }

    public void stop(BundleContext ctx) {
    }
}