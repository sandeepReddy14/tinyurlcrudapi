package com.example.tinyurlcrudapi.service;

import com.example.tinyurlcrudapi.model.UrlData;
import com.example.tinyurlcrudapi.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlService {
    @Autowired
    UrlRepository urlRepository;


    public UrlData createShortUrl(UrlData urlData) {
        return urlRepository.save(urlData);
    }

    public UrlData updateUrlData(String shortUrl, UrlData urlData) {
        return urlRepository.save(urlData);
    }

    public void deleteByShortUrl(String shortUrl) {
        urlRepository.deleteByShortUrl(shortUrl);
    }

    public void deleteAllUrlData() {
        urlRepository.deleteAll();
    }

    public Optional<UrlData> getUrlData(String shortUrl) {
        return urlRepository.getUrlDataByShortUrl(shortUrl);
    }
}
