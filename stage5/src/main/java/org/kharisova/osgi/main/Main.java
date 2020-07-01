package org.kharisova.osgi.main;

import org.kharisova.osgi.aif.AifImpl;
import org.kharisova.osgi.habr.HabrImpl;
import org.kharisova.osgi.lenta.LentaImpl;

public class Main {
    public static void main(String[] args) {
        AifImpl aif = new AifImpl();
        System.out.println(aif.getNewsPortalName());
        System.out.println(aif.getNewsTitles());
        System.out.println(aif.getTopWords());

        HabrImpl habr = new HabrImpl();
        System.out.println(habr.getNewsPortalName());
        System.out.println(habr.getNewsTitles());
        System.out.println(habr.getTopWords());

        LentaImpl lenta = new LentaImpl();
        System.out.println(lenta.getNewsPortalName());
        System.out.println(lenta.getNewsTitles());
        System.out.println(lenta.getTopWords());
    }
}
