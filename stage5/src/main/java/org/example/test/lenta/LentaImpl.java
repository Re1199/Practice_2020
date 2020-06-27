package org.example.test.lenta;

import org.example.test.newsportal.NewsPortal;
import org.example.test.newsportal.impl.NewsPortalAbstract;

import org.osgi.service.component.annotations.Component;

import java.nio.charset.StandardCharsets;
import java.util.*;

import org.json.*;

import java.io.*;
import java.net.URL;

@Component(
        service = NewsPortal.class,
        immediate = true
)
public class LentaImpl extends NewsPortalAbstract implements NewsPortal{
    public String getNewsPortalName() {
        return "lenta.ru";
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private static JSONObject readJsonFromUrl() throws IOException, JSONException {
        try (InputStream is = new URL("https://api.lenta.ru/lists/latest").openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        }
    }

    public List<String> getNewsTitles() {
        ArrayList<String> res = new ArrayList<String>();
        try {
            JSONArray array = readJsonFromUrl().getJSONArray("headlines");
            for (int i = 0; i < array.length(); i++) {
                JSONObject o = array.getJSONObject(i);
                if (o.get("type").equals("news")) {
                    res.add((String) o.getJSONObject("info").get("title"));
                }
            }
        } catch (IOException e) {
            System.err.println("Can't read news: " + e.getMessage());
        }
        return res;
    }

}
