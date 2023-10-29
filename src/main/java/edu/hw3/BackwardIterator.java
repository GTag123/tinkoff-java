package edu.hw3;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BackwardIterator<T> implements Iterator<T> {
    private int currIndex;
    private List<T> list;

    public BackwardIterator(List<T> list) {
        this.list = list;
        this.currIndex = list.size();
    }

    @Override
    public boolean hasNext() {
        return currIndex - 1 >= 0;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Next element doesn`t exist");
        }
        currIndex--;
        return list.get(currIndex);
    }
}
