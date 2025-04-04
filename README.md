# 🔔일정 관리 앱 Develope
## API 명세서 및 ERD
![image](https://github.com/user-attachments/assets/8d6ecf06-5be1-4072-8b7a-e98ae1dd4d0d)

### ▶️Postman
  https://documenter.getpostman.com/view/43244231/2sB2cShPPn

### ▶️ERD
![스크린샷 2025-04-03 195238](https://github.com/user-attachments/assets/f4fdae8b-d8f1-4e52-bfb0-abe1080acc05)

### ▶️테이블

- [ ] User 테이블
-  사용자 정보를 저장하는 테이블.

  주요 필드:

    - id: 사용자 고유 식별자 (Primary Key).

    - username: 사용자 이름 (최대 10자).

    - email: 사용자 이메일 (고유).

    - password: 암호화된 비밀번호.

    - created_at: 생성 날짜.

    - updated_at: 수정 날짜.
    
- [ ] Schedule 테이블
-  일정 정보를 저장하는 테이블.

  주요 필드:

    - id: 일정 고유 식별자 (Primary Key).

    - title: 일정 제목.

    - contents: 일정 내용.

    - user_id: 작성자 ID (Foreign Key, User 테이블 참조).

    - created_at: 생성 날짜.

    - updated_at: 수정 날짜.

## 예외 발생 처리
    - 선택한 일정 정보를 조회할 수 없을 때 예외가 발생
        - 잘못된 정보로 조회하려고 할 때 => Bad Requset
        - 이미 삭제된 정보를 조회하려고 할 때 => Not Found
        
    - 필수 값이 없을 때 예외가 발생
        - null 체크 후 빈값일 때 => Not Found
        
    - 중복 사용자 등록 예외
        - 사용자 등록할 때 이미 등록되어 있는 이메일이 들어오면 가입 방지 => Bad Request 
        
    - 비밀번호 검사
        - 틀렸을 때 인증 실패 =>  UNAUTHORIZED
        
    - 로그인이 필요한 서비스 이용시
        - 로그인 하지 않았을 때  => UNAUTHORIZED
        - 권한이 없는 서비스를 이용하려고 할 때 (남의 스케줄,덧글 값들을 수정/삭제시도) => UNAUTHORIZED
---

### ⚙️ 개발 환경

`JAVA` `spring-boot` `JDK 17` `MySQL`

### 🕰️ 개발 기간

25.04.01 - 25.04.04

---
## 🤔구현된 로직 요약.

**0. 프로젝트 개요**

   - 목적: 사용자 관리 및 일정 관리를 위한 RESTful API 서비스.
   - 기술 스택
     
      Backend: Spring Boot, JPA (Hibernate), Spring Security

      Database: MySQL

      Build Tool: Gradle

      Tools: Postman (API 테스트), IntelliJ IDEA
    
**1. 회원가입 및 로그인**

   -  비밀번호는 Spring Security의 BCryptPasswordEncoder로 암호화하여 저장.

   -  로그인 성공 시 세션에 사용자 ID 저장.
     

**2. 일정 관리**

   - 사용자는 자신의 일정을 생성, 조회, 수정, 삭제할 수 있음.

   - 일정은 작성자와 연관관계를 가짐 (User → Schedule, 단방향).
   

**3. 예외 처리**

   - 잘못된 요청(400 Bad Request) 및 인증 실패(401 Unauthorized)에 대한 예외 처리 구현.

   - 존재하지 않는 리소스 요청 시(404 Not Found) 적절한 메시지 반환.

---

## 🤔프로젝트 구조

`controller`: 컨트롤러 계층

`dto`: 요청 및 응답 DTO

`entity`: JPA 인티티

`repository`: 데이터베이스 접근 계층

`service`: 비스니스 로직 계층

`filter`: 인증 필터

`config`: 설정 파일 (Spring Security, 비밀번호 암호화)
