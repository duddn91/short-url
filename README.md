# 🚀 URL 단축 서비스

## 📌 프로젝트 개요
- 긴 URL을 짧은 URL로 변환하는 서비스
- Redis를 활용하여 빠른 조회 제공

## 🛠 사용 기술
- `SpringBoot` `JPA` `Redis` `MySQL` `ShortID`

## 🔗 API 명세
### 📌 단축 URL 생성
**[POST] /api/url/shorten**
- **요청**: 원본 URL
- **응답**: 단축 URL

### 📌 단축 URL 조회
**[GET] /api/url/{shortId}**
- **요청**: 단축된 URL ID
- **응답**: 원래 URL로 리디렉션