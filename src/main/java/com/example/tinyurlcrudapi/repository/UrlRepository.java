package com.example.tinyurlcrudapi.repository;

import com.example.tinyurlcrudapi.model.URLData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<URLData,String> {
}
