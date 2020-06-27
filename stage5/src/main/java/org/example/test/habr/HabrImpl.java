package org.example.test.habr;

import org.example.test.newsportal.NewsPortal;
import org.example.test.newsportal.impl.NewsPortalAbstract;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HabrImpl extends NewsPortalAbstract implements NewsPortal {
    public String getNewsPortalName() {
        return "habr.com";
    }

    public List<String> getNewsTitles() {
        List<String> res = new ArrayList<>();
        try {
            URL url = new URL("https://habr.com/ru/rss/interesting/");
            URLConnection urlConnection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            Pattern p = Pattern.compile("<title><!\\[CDATA\\[(.*?)]");
            for (String line; (line = reader.readLine()) != null; ) {
                Matcher m = p.matcher(line);
                while (m.find()) {
                    String s = m.group();
                    res.add(s.substring(16, s.length() - 1));
                }
            }
        } catch (Exception e) {
            System.err.println("Can't find url: " + e.getMessage());
        }
        return res;
    }
}
