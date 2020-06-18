package org.example.test.helloosgiservice.impl;

import org.example.test.helloosgiservice.Greeting;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;


public class Activator implements BundleActivator {
    private static BundleContext context;

    static BundleContext getContext() {
        return context;
    }


    public void start(BundleContext ctx) {
        Activator.context = ctx;

        System.out.println("Registry Service HelloOSGiService ...");

        this.registryMathService();

//        ServiceReference ref = context.getServiceReference(Greeting.class.getName());
//        ((Greeting) context.getService(ref)).sayHello();

        System.out.println("OSGi HelloOSGiService Started");

    }

    private void registryMathService() {
        Greeting service = new GreetingImpl("OSGi World");
        context.registerService(Greeting.class.getName(), service, null);
    }

    public void stop(BundleContext bundleContext) {
        Activator.context = null;
        System.out.println("OSGi HelloOSGiService Stopped!");
    }
}