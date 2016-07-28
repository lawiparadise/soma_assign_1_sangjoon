Software Maestro의 첫 번째 과제입니다.
==============
###이 과제는 프론트엔드+백엔드로 이루어져있으며, 
###프론트엔드는 유니티를 export해서 안드로이드에서 작동하고, 백엔드는 아파치톰캣 자바서블릿을 사용하였습니다.


####프론트엔드

1.유니티
- 버전 : 5.1.3f1
- 환경 : 윈도우10
- 위치 : Unity폴더 내

2.안드로이드
- 버전 : 1.5.1
- 환경 : 윈도우10
- 위치 : Android폴더 내


####백엔드

1.아파치톰캣자바서블릿
- 버전 :
 - 아파치 : 2.4.7
 - 톰캣 : 7
- 환경 : 우분투14.04 LTS (AWS)
- 위치 : temp폴더 내

2.mysql
 - 버전 : 14.14 Distrib 5.5.50
 - 환경 : 우분투14.04 LTS (AWS)
 - 위치 : mysql




필수사항

1. 앱(게임)과 서버 통시하는 로직이 포함되어야함.
 - Android\app\src\main\java\com\law\gong_test\common 내부의 connect와 백엔드 측 서블릿(/temp/WEB-INF/src/main/Main.java)으로 통신
2. 클라이언트 게임은 게임첫 설치 시간(signup시간). 접속시간. 접속후 게임종료시간. 마지막 플레이시간등이 DB에 저장되어야 함.
(/temp/WEB-INF/src/main/Main.java 193번쨰줄과 220번째 줄에서 dao객체를 통해 db에 저장)
 - ![mysql](http://52.78.4.220/time.png)
3. 페이스북, 트위터, 이메일등 타 서비스 연계가 1개이상 되어야 함.
(/Android/app/src/main/java/com/law/gong_test/kakao/ 카카오와 연동하여 닉네임, id, 이미지 받아옴)
 - <img src="http://52.78.4.220/kakao.jpg" width="200" height="200"></img>
4. 앱스토어에 업로드 되어야함.
 - https://play.google.com/store/apps/details?id=com.law.gong_test
 - ![mysql](http://52.78.4.220/app.png)
5. 서버 프로그램이 클라우드나 호스팅이 되는 서버에 배포가 되어야함.
 - ![mysql](http://52.78.4.220/aws.png)
6. 데이터는 DB에 저장되어 SQL을 통해서 조회 및 추가가 가능해야함.
(/temp/WEB-INF/src/controll/DAOAll.java를 통해서 mysql에 데이터 저장)
 - ![mysql](http://52.78.4.220/mysql.png)
7. 백엔드는 BaaS나 클라우드 제한 없으나 웹서비스 형태이어야 함.
 - ![mysql](http://52.78.4.220/web.png)
 
가점 사항

1. 클라우드 이용시 가점.
 - ![mysql](http://52.78.4.220/aws.png)
2. 친구추가, 랭킹, 선물하기등의 기능 추가시 가점.(랭킹따로, 친구추가선물하기는 따로)
(/Android/app/src/main/java/com/law/gong_test/act/ 내의 FriendActivity와 PresentActivity를 통해 친추, 선물 가능,
/Android/app/src/main/java/com/law/gong_test/unity/MainActivity.java의 140번째줄에서 랭킹 정보 받아옴)
- <img src="http://52.78.4.220/friend_list.png" width="500" height="300"></img>
 - <img src="http://52.78.4.220/rank.jpg" width="500" height="300"></img>
 - <img src="http://52.78.4.220/present.png" width="500" height="300"></img>
3. Restful API 형태 설계 적용시 가점.
 - ![mysql](http://52.78.4.220/rest.png)
4. push 구현 되었으면 가점.
(/Android/app/src/main/java/com/law/gong_test/gcm/ gcm을 통해 push구현으로 선물하기, 친추하기 기능 구현)
 - <img src="http://52.78.4.220/push1.jpg" width="300" height="500"></img>
 - <img src="http://52.78.4.220/push2.jpg" width="300" height="500"></img>
5. 안드로이드와 아이폰 둘다 대응시 가점.
6. DB와 연동되는 별도의 CMS(관리시스템)을 구축시 가점.
 - http://52.78.4.220/temp/form.html

