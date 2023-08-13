package com.example.tinyurlcrudapi.controller;

import com.example.tinyurlcrudapi.model.UrlData;
import com.example.tinyurlcrudapi.service.UrlService;
import com.example.tinyurlcrudapi.utils.ErrorCode;
import com.example.tinyurlcrudapi.utils.Response;
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
    public ResponseEntity<Response> createShortUrl(@RequestBody UrlData urlData)
    {
        /**/
        try{
            Response _response = urlService.createShortUrl(urlData);
            switch(_response.getErrorCode())
            {
                case NO_ERROR_CODE:
                    break;
                case GENERATED_SUCCESSFULLY:
                    return new ResponseEntity<>(_response, HttpStatus.CREATED);
                case CUSTOM_URL_ALREADY_EXISTS:
                    return new ResponseEntity<>(_response,HttpStatus.CONFLICT);
                case COLLISION_ERROR:
                    return new ResponseEntity<>(_response,HttpStatus.CONFLICT);
                case SERVICE_SERVER_ERROR:
                    return new ResponseEntity<>(_response,HttpStatus.INTERNAL_SERVER_ERROR);
                default:
                    break;
            }
        }
        catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(new Response(ErrorCode.SERVICE_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
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
