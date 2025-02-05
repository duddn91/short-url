package com.byw.shorturl.repository;

import com.byw.shorturl.model.ShortenedUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface UrlShortenerRepository extends JpaRepository<ShortenedUrl, String> {

    @Modifying
    @Query("DELETE FROM ShortenedUrl u WHERE u.createdAt < :expiryDate")
    void deleteExpiredUrls(LocalDateTime expiryDate);
}
