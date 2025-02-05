package com.byw.shorturl.service;

import com.byw.shorturl.model.ShortenedUrl;
import com.byw.shorturl.repository.UrlShortenerRepository;
import com.github.f4b6a3.ulid.UlidCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlShortenerService {

    private final UrlShortenerRepository repository;
    private final StringRedisTemplate redisTemplate;

    @Transactional
    public String shortenUrl(String originalUrl) {
        String shortId = generateShortId();

        ShortenedUrl url = ShortenedUrl.builder()
                .shortId(shortId)
                .originalUrl(originalUrl)
                .build();

        repository.save(url);

        // Redis에 저장 (7일 후 자동 삭제)
        redisTemplate.opsForValue().set(shortId, originalUrl, Duration.ofDays(7));

        return shortId;
    }

    @Transactional(readOnly = true)
    public String getOriginalUrl(String shortId) {
        // Redis에서 먼저 조회
        String cachedUrl = redisTemplate.opsForValue().get(shortId);
        if (cachedUrl != null) return cachedUrl;

        // 없으면 DB에서 조회
        Optional<ShortenedUrl> url = repository.findById(shortId);
        return url.map(ShortenedUrl::getOriginalUrl)
                .orElseThrow(() -> new RuntimeException("URL Not Found"));
    }

    private String generateShortId() {
        return UlidCreator.getUlid().toString().substring(0, 6);
    }
}
