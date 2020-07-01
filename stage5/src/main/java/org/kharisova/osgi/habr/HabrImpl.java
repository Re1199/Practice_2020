package org.kharisova.osgi.habr;

import org.kharisova.osgi.newsportal.NewsPortal;
import org.kharisova.osgi.newsportal.impl.NewsPortalAbstract;
import org.osgi.service.component.annotations.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component(
        service = NewsPortal.class,
        immediate = true
)
public class HabrImpl extends NewsPortalAbstract implements NewsPortal {
    static  final int LEN_BEGIN = 16;
    static  final int LEN_END = 3;


    public String getNewsPortalName() {
        return "habr.com";
    }

    public List<String> getNewsTitles() {
        List<String> res = new ArrayList<>();
        try {
            URL url = new URL("https://habr.com/ru/rss/interesting/");
            URLConnection urlConnection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));
            Pattern pattern = Pattern.compile("<title><!\\[CDATA\\[(.*?)]]>");
            for (String line; (line = reader.readLine()) != null; ) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    String s = matcher.group();
                    s = s.substring(LEN_BEGIN, s.length() - LEN_END);
                    if (s.startsWith("[Из песочницы] "))
                        s = s.substring(15);
                    if (s.startsWith("[Перевод] "))
                        s = s.substring(10);
                    res.add(s);
                }
            }
        } catch (Exception e) {
            System.err.println("Can't find url from habr");
            System.err.println(e.getMessage());
        }
        return res;
    }
}
