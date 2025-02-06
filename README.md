# 📌 ShortURL - Docker 기반 URL 단축 서비스

## 🚀 프로젝트 소개
**ShortURL**은 간단한 URL 단축 서비스를 제공하는 웹 애플리케이션입니다. Docker 기반으로 실행되며, MySQL과 Redis를 활용하여 데이터를 저장하고 캐싱합니다.

## 🛠️ 기술 스택
- **Backend**: Spring Boot 3.4.2 (Kotlin)
- **Database**: MySQL 8.0
- **Cache**: Redis
- **Containerization**: Docker, Docker Compose
- **ORM**: Hibernate, JPA
- **Build Tool**: Gradle

## 📂 프로젝트 구조
```
shorturl/
├── src/              # 애플리케이션 소스 코드
├── build.gradle      # Gradle 빌드 설정 파일
├── docker-compose.yml # Docker Compose 설정
├── dockerfile        # 애플리케이션의 Dockerfile
├── README.md         # 프로젝트 문서
└── ...
```

## 🏗️ 실행 방법
### 1️⃣ 프로젝트 클론
```sh
git clone https://github.com/your-repo/shorturl.git
cd shorturl
```
### 2️⃣ 애플리케이션 빌드 및 Docker 이미지 생성
```sh
./gradlew clean build
docker build -t shorturl .
```

### 3️⃣ Docker 빌드 및 실행
```sh
docker-compose up -d --build
```
> - `-d`: 백그라운드에서 실행
> - `--build`: 변경된 코드 반영하여 이미지 재빌드

### 3️⃣ 애플리케이션 접속
- **웹 애플리케이션**: [http://localhost:8080](http://localhost:8080)
- **MySQL 접속**: `mysql -h 127.0.0.1 -P 3307 -u root -p`
- **Redis CLI 접속**: `docker exec -it redis redis-cli`

## 🔍 주요 기능
✅ **URL 단축 기능**
✅ **단축 URL 리디렉션**
✅ **Redis 캐싱 최적화**
✅ **Docker 기반 컨테이너화**
