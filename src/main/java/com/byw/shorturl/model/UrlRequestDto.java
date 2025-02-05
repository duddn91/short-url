package com.byw.shorturl.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UrlRequestDto {

    @NotBlank(message = "URL은 필수 입력값입니다.")
    @Pattern(
            regexp = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$",
            message = "유효한 URL 형식이 아닙니다."
    )
    private String originalUrl;
}
