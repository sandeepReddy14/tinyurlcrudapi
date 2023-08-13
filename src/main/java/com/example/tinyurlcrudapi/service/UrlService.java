package com.example.tinyurlcrudapi.service;

import com.example.tinyurlcrudapi.model.ShortUrlCounter;
import com.example.tinyurlcrudapi.model.UrlData;
import com.example.tinyurlcrudapi.repository.ShortUrlCounterRepository;
import com.example.tinyurlcrudapi.repository.UrlRepository;
import com.example.tinyurlcrudapi.utils.ErrorCode;
import com.example.tinyurlcrudapi.utils.MD5ToBase62;
import com.example.tinyurlcrudapi.utils.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UrlService {
    @Autowired
    UrlRepository urlRepository;
    @Autowired
    ShortUrlCounterRepository shortUrlCounterRepository;



    public Response createShortUrl(UrlData urlData) {
        if(urlData.isCustomUrl()){
            Optional<UrlData> _urlData = urlRepository.getUrlDataByShortUrl(urlData.getShortUrl());
            if(_urlData.isEmpty()){
                Response _response = new Response(urlRepository.save(urlData), ErrorCode.GENERATED_SUCCESSFULLY);
                return _response;
            }
            else {
                Response _response = new Response(null, ErrorCode.CUSTOM_URL_ALREADY_EXISTS);
                return _response;
            }
        }
        else{
            String longUrl = urlData.getLongUrl();
            byte[] md5HexBytes = DigestUtils.md5Digest(longUrl.getBytes());
            String shortUrlMd = MD5ToBase62.md5bytesToBase62(md5HexBytes).substring(0,9);
            Optional<UrlData> tempUrlData = urlRepository.getUrlDataByShortUrl(shortUrlMd);
            Optional<ShortUrlCounter> tempShortUrlCounter = shortUrlCounterRepository.getShortUrlCounterByShortUrl(shortUrlMd);
            if(tempShortUrlCounter.isEmpty())
            {
                urlData.setShortUrl(shortUrlMd);
                Response _response = new Response(urlRepository.save(urlData), ErrorCode.GENERATED_SUCCESSFULLY);
                shortUrlCounterRepository.save(new ShortUrlCounter(shortUrlMd,1));
                return _response;
            }
            else
            {
                long shortUrlCount = tempShortUrlCounter.get().getCount();
                String _shortUrlMd = shortUrlMd + MD5ToBase62.longToBase62(shortUrlCount);
                boolean _shortUrlMdExists = urlRepository.existsByShortUrl(_shortUrlMd);
                if(_shortUrlMdExists)
                {
                    Response _response = new Response(null, ErrorCode.COLLISION_ERROR);
                    return _response;
                }
                else
                {
                    urlData.setShortUrl(_shortUrlMd);
                    Response _response = new Response(urlRepository.save(urlData), ErrorCode.GENERATED_SUCCESSFULLY);
                    tempShortUrlCounter.get().setCount(shortUrlCount+1);
                    shortUrlCounterRepository.save(tempShortUrlCounter.get());
                    return _response;
                }
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
        shortUrlCounterRepository.deleteAll();
    }

    public Optional<UrlData> getUrlData(String shortUrl) {
        return urlRepository.getUrlDataByShortUrl(shortUrl);
    }
}
