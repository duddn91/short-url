package com.byw.shorturl.service;

import com.byw.shorturl.model.ShortenedUrl;
import com.byw.shorturl.repository.UrlShortenerRepository;
import com.github.f4b6a3.ulid.UlidCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlShortenerService {
    private static final String BASE62_ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private final UrlShortenerRepository repository;
    private final StringRedisTemplate redisTemplate;

    @Transactional
    public List<ShortenedUrl> shortenUrl(String originalUrl) {
        Optional<ShortenedUrl> shortenedUrlOpt = repository.findByOriginalUrl(originalUrl);

        String shortId;
        if (shortenedUrlOpt.isPresent()) {
            shortId = shortenedUrlOpt.get().getShortId();

            if (Boolean.TRUE.equals(redisTemplate.hasKey(shortId))) {
                redisTemplate.expire(shortId, Duration.ofDays(7));
            } else {
                redisTemplate.opsForValue().set(shortId, originalUrl, Duration.ofDays(7));
            }
        } else {
            // 새로운 URL이면 shortId 생성 후 저장
            shortId = generateShortId();
            ShortenedUrl url = ShortenedUrl.builder()
                    .shortId(shortId)
                    .originalUrl(originalUrl)
                    .build();
            repository.save(url);
            redisTemplate.opsForValue().set(shortId, originalUrl, Duration.ofDays(7));
        }

        return repository.findTop10ByOrderByCreatedAtDesc();
    }

    @Transactional(readOnly = true)
    public String getOriginalUrl(String shortId) {
        // Redis에서 먼저 조회
        String cachedUrl = redisTemplate.opsForValue().get(shortId);
        if (cachedUrl != null) {
            redisTemplate.expire(shortId, Duration.ofDays(7));
            return cachedUrl;
        }
        // 없으면 DB에서 조회
        Optional<ShortenedUrl> url = repository.findById(shortId);
        if (url.isPresent()) {
            // Redis에 다시 저장 (조회 시 만료 갱신)
            redisTemplate.opsForValue().set(shortId, url.get().getOriginalUrl(), Duration.ofDays(7));
            return url.get().getOriginalUrl();
        } else {
            throw new RuntimeException("URL Not Found");
        }
    }

    private String generateShortId() {
        String ulid = UlidCreator.getUlid().toString().replace("-", ""); // ULID에서 "-" 제거
        return base62Encode(new BigInteger(ulid, 36)).substring(0, 8);
    }

    private String base62Encode(BigInteger number) {
        StringBuilder sb = new StringBuilder();
        while (number.compareTo(BigInteger.ZERO) > 0) {
            sb.append(BASE62_ALPHABET.charAt(number.mod(BigInteger.valueOf(62)).intValue()));
            number = number.divide(BigInteger.valueOf(62));
        }
        return sb.reverse().toString();
    }
}
