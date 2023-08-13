package com.example.tinyurlcrudapi.repository;

import com.example.tinyurlcrudapi.model.ShortUrlCounter;
import com.example.tinyurlcrudapi.model.UrlData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import java.util.List;
import java.util.Optional;

public interface ShortUrlCounterRepository extends MongoRepository<ShortUrlCounter,String> {
    long countByShortUrl(String shortUrl);
    Optional<ShortUrlCounter> getShortUrlCounterByShortUrl(String shortUrl);
}
