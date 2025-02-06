# 1. Base Image (JDK 17 기반 이미지 사용)
FROM openjdk:17-jdk-slim

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. 빌드된 JAR 파일을 컨테이너로 복사
COPY build/libs/shorturl-0.0.1-SNAPSHOT.jar app.jar

# 4. 애플리케이션 실행
CMD ["java", "-jar", "app.jar"]