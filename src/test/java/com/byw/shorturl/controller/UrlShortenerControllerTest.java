package com.byw.shorturl.controller;

import com.byw.shorturl.service.UrlShortenerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class UrlShortenerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UrlShortenerService urlShortenerService;

    @InjectMocks
    private UrlShortenerController urlShortenerController;

    @Test
    void shortenUrl_shouldReturnShortenedUrl() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(urlShortenerController).build();

        String originalUrl = "https://example.com";
        when(urlShortenerService.shortenUrl(originalUrl)).thenReturn("short.ly/abc123");

        mockMvc.perform(post("/api/url/shorten")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"originalUrl\":\"https://example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("short.ly/abc123"));
    }
}
