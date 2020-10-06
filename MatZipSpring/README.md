# Application Matzip(맛집) Ver. Spring
### <초기 설정>
##### [Window] - [Market place] 에서 sts 검색 후 Spring 확장 프로그램을 설치
##### [File] - [new] - [Other] - [Spring Legacy Project] 에서 next를 눌러 Spring MVC Project로 생성
##### [Window] - [Perspective] - [Open perspective] - [Other] 에서 Spring으로 개발 환경 전환
##### [Project]를 우클릭 후 [Property] 클릭, [Project Facet]에서 Java의 버전을 1.8로 변경, Runtimes에서 apache tomcat을 체크한 후 apply
##### [Project] - [src] - [main] - [WEB-INF] - web.xml 파일 내의 설정들을 전부 삭제
##### [Project] - pom.xml 의 java-version을 1.8로, org.springframework-version의 내용을 4.3.29.RELEASE로 변경

### 사용된 라이브러리
##### *. APACHE TOMCAT v9.0, https://tomcat.apache.org/download-90.cgi
##### 1. MYSQL 커넥터 드라이버, https://dev.mysql.com/downloads/connector/j/
##### 2. AXIOS 라이브러리(MDN 방식), <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
##### 3. GSON 라이브러리(jar 형태), https://github.com/google/gson
##### 4. JSTL(jstl-1.2), https://tomcat.apache.org/taglibs/standard/
##### 5. TAGLIBS(taglibs-standard-impl-1.2.5), https://tomcat.apache.org/download-taglibs.cgi
##### 6. COS(cos), http://www.servlets.com/cos/
##### 7. KAKAO MAP, <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=APP KEY"></script>

##### *. spring, <eclipse marketplace>
##### 8. mybatis, https://mvnrepository.com/artifact/org.mybatis/mybatis/3.5.5
##### 9. mybatis-spring, https://mvnrepository.com/artifact/org.mybatis/mybatis-spring/2.0.5
##### 10. hikariCP, https://mvnrepository.com/artifact/com.zaxxer/HikariCP/3.4.5
##### 11. spring-jdbc, https://mvnrepository.com/artifact/org.springframework/spring-jdbc
##### 12. jackson-databind, https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
##### 13. jbcrypt, https://mvnrepository.com/artifact/org.mindrot/jbcrypt
##### 14. commons-io, https://mvnrepository.com/artifact/commons-io/commons-io
##### 15. commons-fileupload, https://mvnrepository.com/artifact/commons-fileupload/commons-fileupload
##### 16. swiper, css&js, https://swiperjs.com/get-started/

### 패치 내역
#### 20.09.17.
##### 기초 구성 완료

#### 20.09.21.
##### 로그인 기능 구현
##### ID 중복 체크 구현

#### 20.09.22.
##### 마커 등록 및 표시
##### 가게 등록 및 정보 표시

#### 20.09.23.
##### 추천 메뉴 등록 및 삭제 구현

#### 20.09.25.
##### 메뉴 등록 및 삭제 구현
##### 메뉴 확대 슬라이더 구현

#### 20.09.28.
##### 좋아요 및 조회수 기능 구현

#### 20.10.06.
##### 찜 리스트 표시 기능 구현