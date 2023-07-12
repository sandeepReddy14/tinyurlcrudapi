package com.example.tinyurlcrudapi.service;

import com.example.tinyurlcrudapi.model.UrlData;
import com.example.tinyurlcrudapi.repository.UrlRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Optional;

@Service
public class UrlService {
    @Autowired
    UrlRepository urlRepository;
    private final char[] base62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    public UrlData createShortUrl(UrlData urlData) {
        if(urlData.isCustomUrl()){
            Optional<UrlData> _urlData = urlRepository.getUrlDataByShortUrl(urlData.getShortUrl());
            if(_urlData.isEmpty()){
                return urlRepository.save(urlData);
            }
            else {
                return null;
            }
        }
        else{
            String longUrl = urlData.getLongUrl();
            String md5Hex = DigestUtils.md5DigestAsHex(longUrl.getBytes()).toUpperCase();
            urlData.setShortUrl(md5Hex);
            Optional<UrlData> _urlData = urlRepository.getUrlDataByShortUrl(urlData.getShortUrl());
            String randomSalt;
            int retryCount = 10;
            while(_urlData.isPresent() && retryCount > 0){
                randomSalt = RandomStringUtils.randomAlphabetic(8);
                md5Hex = DigestUtils.md5DigestAsHex(longUrl.concat(randomSalt).getBytes()).toUpperCase();
                urlData.setShortUrl(md5Hex);
                _urlData = urlRepository.getUrlDataByShortUrl(urlData.getShortUrl());
                retryCount -= 1;
            }

            if(retryCount != 0 && _urlData.isPresent()) {
                return null;
            }
            else {
                return urlRepository.save(urlData);
            }
        }

    }

    public UrlData updateUrlData(String shortUrl, UrlData urlData) {
        Optional<UrlData> _urlData = urlRepository.getUrlDataByShortUrl(urlData.getShortUrl());
        if(_urlData.isPresent()){
            if(urlData.getLife() != _urlData.get().getLife()){
                _urlData.get().setLife(urlData.getLife());
            }
            return urlRepository.save(_urlData.get());
        }
        else{
            return null;
        }
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
