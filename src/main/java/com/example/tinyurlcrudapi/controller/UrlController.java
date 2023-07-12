package com.example.tinyurlcrudapi.controller;

import com.example.tinyurlcrudapi.model.UrlData;
import com.example.tinyurlcrudapi.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/url-crud-api")
public class UrlController {

    @Autowired
    UrlService urlService;

    @GetMapping("/urls/{shortUrl}")
    public ResponseEntity<UrlData> getUrlData(@PathVariable("shortUrl") String shortUrl)
    {
        try{
            Optional<UrlData> _urlData = urlService.getUrlData(shortUrl);
            if(_urlData.isPresent()){
                return new ResponseEntity<>(_urlData.get(), HttpStatus.FOUND);
            }
            else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }
        catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/urls")
    public ResponseEntity<UrlData> createShortUrl(@RequestBody UrlData urlData)
    {
        try{
            UrlData _urlData =  urlService.createShortUrl(urlData);
            return new ResponseEntity<>(_urlData, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/urls/{shortUrl}")
    public ResponseEntity<UrlData> updateUrlData(@PathVariable("shortUrl") String shortUrl, @RequestBody UrlData urlData)
    {
        try{
            UrlData _urlData = urlService.updateUrlData(shortUrl,urlData);
            if(_urlData == null){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            else{
                return new ResponseEntity<>(_urlData, HttpStatus.OK);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/urls/{shortUrl}")
    public ResponseEntity<HttpStatus> deleteUrlData(@PathVariable("shortUrl") String shortUrl){
        try{
            urlService.deleteByShortUrl(shortUrl);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/urls")
    public ResponseEntity<HttpStatus> deleteAllUrlData(){
        try{
            urlService.deleteAllUrlData();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
