package org.example.test.newsportal.impl;

import org.example.test.newsportal.NewsPortal;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.reverseOrder;

public abstract class NewsPortalAbstract implements NewsPortal {
    public List<String> getTopWords() {
        List<String> res = new ArrayList<String>();
        List<String> newsNames = this.getNewsTitles();
        Map<String, Long> wordMap = new HashMap<String, Long>();
        for (String newsName : newsNames) {
            String[] words = newsName.split("\\s+");
            for (String word : words) {
                word = word.replaceAll("[^а-яА-Яa-zA-Z0-9\\-\\s]", "").toLowerCase();
                if (!word.equals(""))
                    wordMap.put(word, wordMap.containsKey(word) ? wordMap.get(word) + 1 : 1);
            }
        }
        if (wordMap.size() <= 10) {
            return new ArrayList<>(wordMap.keySet());
        } else {
            List<Map.Entry<String, Long>> list = new ArrayList<>(wordMap.entrySet());
            list.sort(Map.Entry.comparingByValue());
            List<Map.Entry<String, Long>> sorted =
                    wordMap.entrySet().stream()
                            .sorted(reverseOrder(Map.Entry.comparingByValue())).limit(10).collect(Collectors.toList());
            for (int i = 0; i < 10; i++) {
                res.add(sorted.get(i).getKey());
            }
        }
        return res;
    }
}
