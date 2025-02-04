package com.byw.shorturl;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlShortenerRepository extends JpaRepository<ShortenedUrl, String> {
}
