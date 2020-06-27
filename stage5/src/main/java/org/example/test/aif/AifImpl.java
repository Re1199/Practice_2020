package org.example.test.aif;

import org.example.test.newsportal.NewsPortal;
import org.example.test.newsportal.impl.NewsPortalAbstract;
import org.osgi.service.component.annotations.Component;

import java.net.URLConnection;
import java.util.*;
import java.io.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component(
        service = NewsPortal.class,
        immediate = true
)
public class AifImpl extends NewsPortalAbstract  implements NewsPortal{
    public String getNewsPortalName() {
        return "aif.ru";
    }

    public List<String> getNewsTitles() {
        List<String> res = new ArrayList<>();
        try {
            URL url = new URL("http://www.aif.ru/rss/news.php");
            URLConnection urlConnection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            Pattern p = Pattern.compile("<title><!\\[CDATA\\[(.*?)]]>");
            for (String line; (line = reader.readLine()) != null; ) {
                Matcher matcher = p.matcher(line);
                while (matcher.find()) {
                    String s = matcher.group();
                    res.add(s.substring(16, s.length() - 1));
                }
            }
        } catch (Exception e) {
            System.err.println("Can't find url: " + e.getMessage());
        }
        return res;
    }
}
