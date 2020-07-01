package org.kharisova.osgi.newsstatcommand;

import org.apache.felix.service.command.CommandProcessor;
import org.kharisova.osgi.newsportal.NewsPortal;
import org.osgi.service.component.annotations.*;

import java.io.UnsupportedEncodingException;
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
            String s = newsPortalMap.get(mediaName).getTopWords().toString();
            try {
                byte[] b = s.getBytes("UTF-8");
                s = new String(b, "UTF-8");
            } catch (UnsupportedEncodingException e) {
            }
            System.out.println(s);
        } else {
            System.out.println("Error! News portal names can be: " +
                    String.join(", ", newsPortalMap.keySet()));
        }
    }
}

