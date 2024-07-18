package edu.hw6.Task5HackerNews;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {

    private static final Pattern TITLE_PATTERN = Pattern.compile("\"title\":\"([^\"]+)\"");

    private long[] convertJsonToLongArray(String json) {
        String[] topStories = json.substring(1, json.length() - 1).split(",");
        return Arrays.stream(topStories).mapToLong(Long::parseLong).toArray();
    }

    public long[] hackerNewsTopStories() {
        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://hacker-news.firebaseio.com/v0/topstories.json"))
                .GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return convertJsonToLongArray(response.body());
        } catch (Exception  e) {
            return new long[0];
        } finally {
            client = null; // Explicitly setting to null, as client.close() may throw an IOException.
        }
    }

    private String getNewsTitleFromJson(String json) {
        Matcher matcher = TITLE_PATTERN.matcher(json);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    public String news(long id) {
        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://hacker-news.firebaseio.com/v0/item/" + id + ".json"))
                .build();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return getNewsTitleFromJson(response.body());
        } catch (Exception e) {
            return "";
        } finally {
            client = null;
        }
    }

}
