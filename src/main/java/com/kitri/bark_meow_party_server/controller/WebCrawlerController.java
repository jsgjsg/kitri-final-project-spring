package com.kitri.bark_meow_party_server.controller;

import com.kitri.bark_meow_party_server.service.WebCrawlerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class WebCrawlerController {

    private final WebCrawlerService webCrawlerService;

    public WebCrawlerController(WebCrawlerService webCrawlerService) {
        this.webCrawlerService = webCrawlerService;
    }

    @GetMapping("/api/crawl")
    public List<String> crawl(@RequestParam String url) {
        try {
            return webCrawlerService.crawl(url);
        } catch (IOException e) {
            e.printStackTrace();
            return List.of(); // 빈 리스트 반환
        }
    }

    @GetMapping("/api/crawlAll")
    public String crawlAll(@RequestParam String url) {
        try {
            return webCrawlerService.crawlAll(url);
        } catch (IOException e) {
            e.printStackTrace();
            return ""; // 빈 문자열 반환
        }
    }
}
