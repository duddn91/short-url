package com.byw.shorturl.repository;

import com.byw.shorturl.model.ShortenedUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UrlShortenerRepository extends JpaRepository<ShortenedUrl, String> {
    Optional<ShortenedUrl> findByOriginalUrl(String originalUrl);
    List<ShortenedUrl> findTop10ByOrderByCreatedAtDesc();
}
