package org.example.test.newsstatcommand;

import org.example.test.newsportal.NewsPortal;

import org.osgi.service.component.annotations.*;

import java.util.*;

@Component(
        immediate = true
)
public class NewsStatCommand {
    @Reference(
            service = NewsPortal.class,
            cardinality = ReferenceCardinality.MULTIPLE,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "unbinder"
    )
    private NewsPortal newsPortal;
    private Map<String, NewsPortal> newsPortalMap = new HashMap<String, NewsPortal>();

    @Activate
    protected void onActivate() {
        System.out.println(newsPortal.getNewsPortalName());
        newsPortalMap.put(newsPortal.getNewsPortalName(), newsPortal);
    }

    public void unbinder(NewsPortal mediaPortal) {
        System.out.println("Unbind " + mediaPortal.getNewsPortalName());
        newsPortalMap.remove(mediaPortal.getNewsPortalName());
    }

    public void stats() {
        System.out.println("Expected \"news:stats <media_name>\"");
        System.out.println("<media_name> can be: " + String.join(", ", newsPortalMap.keySet()));
    }

    public void stats(String mediaName) {
        if (newsPortalMap.containsKey(mediaName)) {
            System.out.println(newsPortalMap.get(mediaName).getTopWords());
        } else {
            System.out.println("<media_name> can be: " + String.join(", ", newsPortalMap.keySet()));
        }
    }
}
