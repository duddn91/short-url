package com.byw.shorturl.controller;

import com.byw.shorturl.model.ShortenedUrl;
import com.byw.shorturl.service.UrlShortenerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final UrlShortenerService urlShortenerService;

    @GetMapping("")
    public String showHomePage() {
        return "index";
    }

    @PostMapping("/shorten")
    public String shortenUrl(@RequestParam("originalUrl") String originalUrl, Model model) {
        List<ShortenedUrl> shortenedUrls = urlShortenerService.shortenUrl(originalUrl);
        model.addAttribute("shortenedUrls", shortenedUrls);
        return "index";
    }

    @GetMapping("/{shortId}")
    public String redirectToOriginal(@PathVariable String shortId) {
        String originalUrl = urlShortenerService.getOriginalUrl(shortId);
        return "redirect:" + originalUrl;
    }
}
