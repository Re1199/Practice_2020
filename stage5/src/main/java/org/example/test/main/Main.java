package org.example.test.main;

import org.example.test.aif.AifImpl;
import org.example.test.habr.HabrImpl;
import org.example.test.lenta.LentaImpl;

public class Main {
    public static void main(String[] args) {
        LentaImpl testLenta = new LentaImpl();
        System.out.println(testLenta.getNewsPortalName());
        System.out.println(testLenta.getNewsTitles());
        System.out.println(testLenta.getTopWords());


        AifImpl testAif = new AifImpl();
        System.out.println(testAif.getNewsPortalName());
        System.out.println(testAif.getNewsTitles());
        System.out.println(testAif.getTopWords());


        HabrImpl testHabr = new HabrImpl();
        System.out.println(testHabr.getNewsPortalName());
        System.out.println(testHabr.getNewsTitles());
        System.out.println(testHabr.getTopWords());
    }
}
