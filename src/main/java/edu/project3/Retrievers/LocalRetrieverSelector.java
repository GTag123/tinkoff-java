package edu.project3.Retrievers;

import java.nio.file.Paths;

public class LocalRetrieverSelector extends RetrieverSelector {

    @Override
    public LogRetriever selectRetriever(String path) {
        try {
            Paths.get(path);
            return new LocalLogRetriever(path);
        } catch (Exception e) {
            return checkNext(path);
        }
    }
}
