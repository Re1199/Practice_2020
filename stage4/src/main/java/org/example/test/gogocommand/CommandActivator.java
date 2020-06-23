package org.example.test.gogocommand;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.Hashtable;

public class CommandActivator implements BundleActivator {
    private static BundleContext context;

    static BundleContext getContext() {
        return context;
    }

    public void start(BundleContext ctx) throws Exception {
        CommandActivator.context = ctx;
        System.out.println("Registry bundle with new command ...");
        this.registryService();
        System.out.println("OSGi bundle with new command started");
    }

    private void registryService() {
        Hashtable props = new Hashtable();
        props.put("osgi.command.scope", "practice");
        props.put("osgi.command.function", "hello");
        context.registerService(Command.class.getName(), new Command(context), props);
    }

    public void stop(BundleContext ctx) throws Exception {
        CommandActivator.context = null;
        System.out.println("New command hello bundle stopped!");
    }

}
