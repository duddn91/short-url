package com.byw.shorturl;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/url")
@RequiredArgsConstructor
public class UrlShortenerController {

    private final UrlShortenerService urlShortenerService;

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody String originalUrl) {
        String shortUrl = urlShortenerService.shortenUrl(originalUrl);
        return ResponseEntity.ok(shortUrl);
    }

    @GetMapping("/{shortId}")
    public ResponseEntity<Void> redirect(@PathVariable String shortId, HttpServletResponse response) {
        String originalUrl = urlShortenerService.getOriginalUrl(shortId);
        response.setHeader("Location", originalUrl);
        return ResponseEntity.status(HttpStatus.FOUND).build();
    }
}
