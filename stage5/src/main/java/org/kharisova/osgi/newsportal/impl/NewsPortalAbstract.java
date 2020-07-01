package org.kharisova.osgi.newsportal.impl;

import org.kharisova.osgi.newsportal.NewsPortal;

import java.util.*;
import java.util.stream.Collectors;

public abstract class NewsPortalAbstract implements NewsPortal {
    static final int NUM_WORDS = 10;


    public List<String> getTopWords() {
        List<String> newsNames = this.getNewsTitles();
        return findKMostFrequentWords(newsNames, NUM_WORDS);
    }

    private List<String> findKMostFrequentWords(List<String> titles, int k) {
        return titles
                .stream()
                .map((s) -> s.replaceAll("[^а-яА-Яa-zA-Z0-9\\-\\s]", "").toLowerCase().split("\\s+"))
                .flatMap(Arrays::stream)
                .collect(Collectors.groupingBy(s -> s))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing((e) -> e.getValue().size(), Comparator.reverseOrder()))
                .limit(k)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
