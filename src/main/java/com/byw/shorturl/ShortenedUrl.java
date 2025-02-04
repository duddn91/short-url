package com.byw.shorturl;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ShortenedUrl {
    @Id
    private String shortId;

    @Column(nullable = false)
    private String originalUrl;

    public ShortenedUrl(String shortId, String originalUrl) {
        this.shortId = shortId;
        this.originalUrl = originalUrl;
    }
}
