# FlowAssignment
1. 커스텀 확장자 중복 체크.  
- 이미 등록된 확장자가 있을수 있으므로, 해당 부분 체크하여 개발.  
  
2. 고정 확장자에 등록되있을경우, 사용자에게 알림 표시  
- 고정확장자에 등록되어있는 확장자의 경우, 커스텀 확장자 추가 등록시, 알림표시 표출

3. 서버와 통신중 일때, 로딩화면 구현.  
- 짧은 시간내 체크박스를 연속클릭하였을때, 제대로 동작하지 않을경우를 대비하여 개발.

4. 서버와의 통신 실패, DB연결 실패, DB조회 실패 등 예외가 발생할 수 있는곳에 예외처리.  
- 예외 발생시 사용자에게 아무런 상태창도 뜨지않을경우를 대비하여 개발.

5. 비정상적인 URI로의 접근 차단.  
- 악의적인 사용자가 URI로 서버의 접근할 수 있으므로, 해당부분을 차단하여 개발.

6. 커스텀 확장자 추가에서 알파벳,숫자를 제외한 문자열 차단.  
- 알파벳과 숫자를 제외한 문자열이 입력될 경우 사용자에게 알림.  

7. 커스텀 확장자 추가시, DB에 소문자로 입력되게하는 기능 추가.  
- 확장자의 네이밍 규칙은 기본적으로 소문자이므로, 반드시 소문자로 입력되도록 함.
  
8. 커스텀 확장자 공백체크
- 커스텀 확장자 등록시, 문자열에 공백이 있을경우 해당부분을 제거.

9. 비동기 처리후 생기는 예외 예방.  
- 비동기 작업후 생길 수 있는 문제점을 고려하여 추가.(Promise,Async,Await 사용)

10. Jenkins를 활용하여 CI/CD구축.
- 배포 주기 단축 및 사용자의 피드백등을 고려하여 구축.  배포가 완료되면 메일로 알림.

  
# 제작환경  
- IDE : IntelliJ  
- Java : 1.8  
- Spring FramWork : Spring Boot 2.7.5  
  
# 배포환경  
- Cloude : AWS EC2
- OS : Ubuntu 22.04 LTS  
- Web Server : NGINX  
- WAS : tomcat9  
- RDBMS : Postgresql
- CI/CD : Jenkins  

# ERD
|칼럼명|타입|속성|
|------|---|---|
|ex_id|Integer|NOT NULL,PK|
|ex_name|Character Varyring(20)|NOT NULL,UNIQUE|
|ex_sort|Character Varyring(6)|NOT NULL|
|ex_regdate|TimeStamp With TimeZone|NOT NULL|

# 접속정보
프로젝트 : http://43.200.53.112/  
ID : admin  
Password : 1234
  
jenkins : http://43.200.53.112/jenkins
