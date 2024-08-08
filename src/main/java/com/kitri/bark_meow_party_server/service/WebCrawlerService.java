package com.kitri.bark_meow_party_server.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebCrawlerService {

    public List<String> crawl(String url) throws IOException {
        List<String> titles = new ArrayList<>();

        Document document = Jsoup.connect(url).get();

        // 예를 들어, <span> 태그의 내용을 추출하는 경우
        for (Element element : document.select("div")) {
            titles.add(element.text());
        }

        return titles;
    }

    public String crawlAll(String url) throws IOException {
        List<String> titles = new ArrayList<>();

        Document document = Jsoup.connect(url).get();


        // 페이지 전체 내용 가져오기
        String pageContent = document.outerHtml();

        return pageContent;
    }
}
