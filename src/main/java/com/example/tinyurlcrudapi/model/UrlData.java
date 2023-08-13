package com.example.tinyurlcrudapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "urlData")
public class UrlData {
    @Id
    private String id;
    private String shortUrl;

    private String longUrl;
    private boolean isCustomUrl;

    private String randomSalt;
    private Date creationDate;
    private int life;

    public UrlData() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public boolean isCustomUrl() {
        return isCustomUrl;
    }

    public void setCustomUrl(boolean customUrl) {
        isCustomUrl = customUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getRandomSalt() {
        return randomSalt;
    }

    public void setRandomSalt(String randomSalt) {
        this.randomSalt = randomSalt;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    @Override
    public String toString() {
        return "UrlData{" +
                "id='" + id + '\'' +
                ", longUrl='" + longUrl + '\'' +
                ", isCustomUrl=" + isCustomUrl +
                ", shortUrl='" + shortUrl + '\'' +
                ", randomSalt='" + randomSalt + '\'' +
                ", creationDate=" + creationDate +
                ", life=" + life +
                '}';
    }
}
