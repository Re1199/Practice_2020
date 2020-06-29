package org.example.test.newsstatcommand;

import org.apache.felix.service.command.CommandProcessor;
import org.example.test.newsportal.NewsPortal;
import org.osgi.service.component.annotations.*;

import java.util.*;

@Component(
        service = NewsStatCommand.class,
        immediate = true,
        property = {
                CommandProcessor.COMMAND_SCOPE + ":String=news",
                CommandProcessor.COMMAND_FUNCTION + ":String=stats"
        }
)
public class NewsStatCommand {
    private NewsPortal newsPortal;
    private Map<String, NewsPortal> newsPortalMap = new HashMap<String, NewsPortal>();

    @Reference(
            service = NewsPortal.class,
            policy = ReferencePolicy.DYNAMIC,
            bind = "binder",
            unbind = "unbinder"
    )
    protected void binder(NewsPortal newsPortal) {
        this.newsPortal = newsPortal;
        System.out.println(newsPortal.getNewsPortalName());
        newsPortalMap.put(newsPortal.getNewsPortalName(), newsPortal);
    }

    public void unbinder(NewsPortal mediaPortal) {
        System.out.println("Unbind " + mediaPortal.getNewsPortalName());
        newsPortalMap.remove(mediaPortal.getNewsPortalName());
    }

    public void stats() {
        System.out.println("Please, choose news portal");
        System.out.println("News portal names: " + String.join(", ", newsPortalMap.keySet()));
    }

    public void stats(String mediaName) {
        if (newsPortalMap.containsKey(mediaName)) {
            System.out.println(newsPortalMap.get(mediaName).getTopWords());
        } else {
            System.out.println("Error! News portal names can be: " +
                    String.join(", ", newsPortalMap.keySet()));
        }
    }
}
