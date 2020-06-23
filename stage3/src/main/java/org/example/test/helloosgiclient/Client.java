package org.example.test.helloosgiclient;

import org.example.test.helloosgiservice.Greeting;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.*;

@Component
public class Client {
    private BundleContext ctx;

    @Reference(
            service = Greeting.class,
            cardinality = ReferenceCardinality.MANDATORY,
            unbind = "unbinder"
    )

    @Activate
    protected void onActivate() {
        ServiceReference ref =
                ctx.getServiceReference(Greeting.class.getName());
        ((Greeting) ctx.getService(ref)).sayHello();
    }

    protected void unbinder() {
        this.ctx = null;
    }
}