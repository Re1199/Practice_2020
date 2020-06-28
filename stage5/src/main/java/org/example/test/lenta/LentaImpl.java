package org.example.test.lenta;

import org.example.test.newsportal.NewsPortal;
import org.example.test.newsportal.impl.NewsPortalAbstract;

import org.osgi.service.component.annotations.Component;
import org.json.*;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;
import java.net.URL;

@Component(
        service = NewsPortal.class,
        immediate = true
)
public class LentaImpl extends NewsPortalAbstract implements NewsPortal {
    public String getNewsPortalName() {
        return "lenta.ru";
    }

    private static JSONObject readJsonFromUrl() throws IOException, JSONException {
        try (InputStream is = new URL("https://api.lenta.ru/lists/latest").openStream()) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String text = readAll(bufferedReader);
            return new JSONObject(text);
        }
    }

    private static String readAll(Reader reader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int i;
        while ((i = reader.read()) != -1) {
            stringBuilder.append((char) i);
        }
        return stringBuilder.toString();
    }

    public List<String> getNewsTitles() {
        ArrayList<String> res = new ArrayList<String>();
        try {
            JSONArray array = readJsonFromUrl().getJSONArray("headlines");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                if (jsonObject.get("type").equals("news")) {
                    res.add((String) jsonObject.getJSONObject("info").get("title"));
                }
            }
        } catch (IOException e) {
            System.err.println("Can't read news from lenta");
            System.err.println(e.getMessage());
        }
        return res;
    }
}
