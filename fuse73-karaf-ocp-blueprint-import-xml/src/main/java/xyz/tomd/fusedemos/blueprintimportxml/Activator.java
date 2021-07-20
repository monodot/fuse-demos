package xyz.tomd.fusedemos.blueprintimportxml;

import org.osgi.framework.*;
import org.osgi.framework.wiring.FrameworkWiring;

import java.io.InputStream;
import java.util.Collections;

public class Activator implements BundleActivator {

    public void start(BundleContext bundleContext) throws Exception {
//        InputStream stream = this.getClass().getResourceAsStream("imports/blueprint2.xml");
//        Bundle b = bundleContext.installBundle("file:imports:imports/blueprint2.xml", stream);
        Bundle b = bundleContext.installBundle("blueprint:file:/home/tdonohue/Code/repos/fuse-demos/fuse-demos-karaf/blueprint-import-xml/src/main/resources/imports/blueprint2.xml", stream);

        //b.start();

        bundleContext.addBundleListener(new BundleListener() {
            public void bundleChanged(BundleEvent bundleEvent) {

            }
        });

        b.getBundleContext().getBundle(0L).adapt(FrameworkWiring.class).refreshBundles(Collections.singletonList(b));

    }

    public void stop(BundleContext bundleContext) throws Exception {

    }
}
