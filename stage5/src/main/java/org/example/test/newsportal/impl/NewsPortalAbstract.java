package org.example.test.newsportal.impl;

import org.example.test.newsportal.NewsPortal;
import org.osgi.service.component.annotations.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component(
        service=NewsPortal.class,
        immediate = true
)
public abstract class NewsPortalAbstract implements NewsPortal {
    public List<String> getTopWords()  {
        List<String> newsNames = this.getNewsTitles();
        return findKMostFrequentWords(newsNames, 10);
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
