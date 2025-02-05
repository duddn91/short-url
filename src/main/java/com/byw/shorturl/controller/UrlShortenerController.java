package com.byw.shorturl.controller;

import com.byw.shorturl.model.UrlRequestDto;
import com.byw.shorturl.service.UrlShortenerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/url")
@RequiredArgsConstructor
public class UrlShortenerController {

    private final UrlShortenerService urlShortenerService;

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@Valid @RequestBody UrlRequestDto requestDto) {
        String shortUrl = urlShortenerService.shortenUrl(requestDto.getOriginalUrl());
        return ResponseEntity.ok(shortUrl);
    }

    @GetMapping("/{shortId}")
    public ResponseEntity<Void> redirect(@PathVariable String shortId) {
        String originalUrl = urlShortenerService.getOriginalUrl(shortId);
        return ResponseEntity.status(302).header("Location", originalUrl).build();
    }
}
