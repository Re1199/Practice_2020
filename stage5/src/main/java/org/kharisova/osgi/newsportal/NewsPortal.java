package org.kharisova.osgi.newsportal;

import java.util.List;

public interface NewsPortal {
    String getNewsPortalName();

    List<String> getNewsTitles();

    List<String> getTopWords();
}
