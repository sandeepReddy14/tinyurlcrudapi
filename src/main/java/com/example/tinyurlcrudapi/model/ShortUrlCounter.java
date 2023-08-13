package com.example.tinyurlcrudapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "shortUrlCounter")
public class ShortUrlCounter {
    @Id
    private String id;
    private String shortUrl;
    private long count = 0;

    public ShortUrlCounter(String shortUrl, long count) {
        this.shortUrl = shortUrl;
        this.count = count;
    }

    public ShortUrlCounter() {
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ShortUrlCounter{" +
                "id='" + id + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                ", count=" + count +
                '}';
    }
}
