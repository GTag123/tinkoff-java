package edu.hw6;

import edu.hw6.Task5HackerNews.HackerNews;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class Task5Test {

    @Test
    @DisplayName("Test hackerNewsTopStories function")
    public void testHackerNewsTopStories() {
        HackerNews hackerNews = new HackerNews();
        assertThat(hackerNews.hackerNewsTopStories()).isNotEmpty();
    }

    @Test
    @DisplayName("Test 1 news function")
    public void test1News() {
        HackerNews hackerNews = new HackerNews();
        assertThat(hackerNews.news(1)).isNotEmpty();
    }

    @Test
    @DisplayName("Test 2 news function")
    public void test2News() {
        HackerNews hackerNews = new HackerNews();
        assertThat(hackerNews.news(-1)).isEmpty();
    }
}
