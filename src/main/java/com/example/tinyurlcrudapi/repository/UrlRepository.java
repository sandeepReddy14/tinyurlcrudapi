package com.example.tinyurlcrudapi.repository;

import com.example.tinyurlcrudapi.model.UrlData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UrlRepository extends MongoRepository<UrlData,String> {
    void deleteByShortUrl(String shortUrl);

    Optional<UrlData> getUrlDataByShortUrl(String shortUrl);
    Optional<UrlData> getUrlDataByLongUrl(String longUrl);
    boolean existsByShortUrl(String shortUrl);
}
