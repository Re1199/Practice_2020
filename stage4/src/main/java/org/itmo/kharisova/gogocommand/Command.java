package org.itmo.kharisova.gogocommand;

import org.apache.felix.service.command.Parameter;
import org.osgi.framework.BundleContext;

public class Command {
    private BundleContext bundleContext;

    public Command(BundleContext ctx) {
        this.bundleContext = ctx;
    }

    public void hello() {
        System.out.println("Enter param, please");
    }

    public void hello(String param) {
        System.out.println("Hello, " + param + "!");
    }
}
