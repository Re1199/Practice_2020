package org.kharisova.osgi.aif;

import org.kharisova.osgi.newsportal.NewsPortal;
import org.kharisova.osgi.newsportal.impl.NewsPortalAbstract;
import org.osgi.service.component.annotations.Component;

import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component(
        service = NewsPortal.class,
        immediate = true
)
public class AifImpl extends NewsPortalAbstract implements NewsPortal {
    static final int LEN_BEGIN = 16;
    static final int LEN_END = 3;


    public String getNewsPortalName() {
        return "aif.ru";
    }

    public List<String> getNewsTitles() {
        List<String> res = new ArrayList<>();
        try {
            URL url = new URL("https://www.aif.ru/rss/news.php");
            URLConnection urlConnection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));
            Pattern p = Pattern.compile("<title><!\\[CDATA\\[(.*?)]]>");
            for (String line; (line = reader.readLine()) != null; ) {
                Matcher matcher = p.matcher(line);
                while (matcher.find()) {
                    String s = matcher.group();
                    res.add(s.substring(LEN_BEGIN, s.length() - LEN_END));
                }
            }
        } catch (Exception e) {
            System.err.println("Can't find url from aif");
            System.err.println(e.getMessage());
        }
        return res;
    }
}
