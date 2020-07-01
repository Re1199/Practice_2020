package org.itmo.kharisova.helloosgiclient;

import org.itmo.kharisova.helloosgiservice.Greeting;
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