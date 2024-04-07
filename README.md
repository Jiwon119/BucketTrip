# 🛫 BucketTrip
**Bucket List**  : 죽기 전에 꼭 한 번 쯤은 해 보고 싶은 것들을 정리한 목록  
가고 싶은 여행지 목록을 장바구니처럼 담아 같이 가고 싶은 친구와 여행 일정을 계획하는 여행 일정 서비스 

**프로젝트 기간** : 2023.11.16-2023.11.24 (1주)  
**플랫폼** : Web  
**개발 인원** : 2인  

## 🎁 서비스 소개
1.	사용자는 원하는 여행지를 검색하고, 버킷리스트(즐겨찾기)에 추가할 수 있음. 
2.	즐겨찾기에 추가된 여행지로 여행 일정을 잡을 수 있음
    -	친구를 일정에 초대할 수 있음
3.	일기를 작성하여 스탬프를 찍을 수 있음.
4.	공유된 버킷리스트 일정에는 친구들과 공유 일기를 작성하고, 댓글로 소통할 수 있음.

## 🎦 동영상
[![Video Label](http://img.youtube.com/vi/YAT4j8MRdno/0.jpg)](https://youtu.be/YAT4j8MRdno)  

## ⚒️ 기술 스택
언어 : `Java`, `JavaScript`, `CSS3`, `HTML5`   
프레임워크 : `SpringBoot`, `Vue.js`    
DB: `MySQL`  
IDE : `Intellij`, `VSCode`, `Workbench`  
협업 : `Git`, `Figma`, `Notion`  

## ✏️ SW Document

#### 🧍‍♂️ 요구사항 분석 / 설계 Usecase
**회원 시스템 Usecase**  
![image](https://github.com/Jiwon119/BucketTrip/assets/92111247/3ac7fe1d-fb56-4fad-a6a5-5e13b6f59a4d)  
**여행 계획 Usecase**  
![image](https://github.com/Jiwon119/BucketTrip/assets/92111247/f62d507b-6d91-42a9-a53a-9507c4ca81c9)  
**여행 후기 시스템 Usecase**  
![image](https://github.com/Jiwon119/BucketTrip/assets/92111247/355cf3f5-3062-43ee-b0a2-9756b233a1ed)  
**친구 시스템 Usecase**  
![image](https://github.com/Jiwon119/BucketTrip/assets/92111247/4b441a68-9720-44b4-8a92-0654f1265a15)  
  
#### 📃 테이블 구조도(ERD)
![image](https://github.com/Jiwon119/BucketTrip/assets/92111247/ba6a2723-bcfc-4beb-9b5d-4a3540a8739e)

#### 🗒 클래스 다이어그램

## 💾 기능 소개
**메인페이지**
<img width="1256" alt="메인페이지" src="https://github.com/Jiwon119/BucketTrip/assets/92111247/42b52b57-9971-4937-a560-7160f82b25c5">
**로그인/회원가입**
<img width="383" alt="로그인" src="https://github.com/Jiwon119/BucketTrip/assets/92111247/00cf8432-ad02-48cd-b538-4274022e3b13">
<img width="396" alt="회원가입" src="https://github.com/Jiwon119/BucketTrip/assets/92111247/83c65a9a-494c-49a6-bac8-1e689d3646e4">

**마이페이지**
<img width="809" alt="마이페이지" src="https://github.com/Jiwon119/BucketTrip/assets/92111247/6146879d-a798-4076-8f47-3040a4018b08">

**핫플레이스**
<img width="1259" alt="핫플레이스" src="https://github.com/Jiwon119/BucketTrip/assets/92111247/e9fb4ebe-bdb5-4606-afbd-112b23783a53">

**여행지 검색**
<img width="1267" alt="관광지 검색" src="https://github.com/Jiwon119/BucketTrip/assets/92111247/a5019654-c89d-4086-86ea-00c6798000f1">

**마이 버킷리스트**
<img width="1253" alt="버킷리스트 목록" src="https://github.com/Jiwon119/BucketTrip/assets/92111247/55dcddf5-118d-48bf-8cef-e40bfc42df25">

**버킷리스트 생성**
<img width="1251" alt="버킷리스트 생성" src="https://github.com/Jiwon119/BucketTrip/assets/92111247/581c3b0d-7d35-47e8-bc39-5163b4e2de93">

**즐겨찾기**
<img width="1264" alt="즐겨찾가'" src="https://github.com/Jiwon119/BucketTrip/assets/92111247/aae45c63-d684-4348-96bc-8b15dd504c4e">

**일기**
<img width="1253" alt="일기" src="https://github.com/Jiwon119/BucketTrip/assets/92111247/28155f83-9dc6-424d-8030-eeeb549a4530">


#### 📁 Server 폴더 구조
```
├── java
│   ├── Board.cld
│   ├── com
│   │   └── ssafy
│   │       ├── Application.java
│   │       ├── attraction
│   │       │   ├── controller
│   │       │   │   └── AttractionController.java
│   │       │   └── model
│   │       │       ├── AttractionDescriptionDto.java
│   │       │       ├── AttractionDetailDto.java
│   │       │       ├── AttractionInfoDto.java
│   │       │       ├── GugunDto.java
│   │       │       ├── SidoDto.java
│   │       │       ├── mapper
│   │       │       │   └── AttractionMapper.java
│   │       │       └── service
│   │       │           ├── AttractionService.java
│   │       │           └── AttractionServiceImpl.java
│   │       ├── board
│   │       │   ├── controller
│   │       │   │   └── BoardController.java
│   │       │   └── model
│   │       │       ├── BoardDto.java
│   │       │       ├── BoardListDto.java
│   │       │       ├── CommentDto.java
│   │       │       ├── FileInfoDto.java
│   │       │       ├── mapper
│   │       │       │   └── BoardMapper.java
│   │       │       └── service
│   │       │           ├── BoardService.java
│   │       │           └── BoardServiceImpl.java
│   │       ├── config
│   │       │   ├── SwaggerConfiguration.java
│   │       │   └── WebMvcConfiguration.java
│   │       ├── exception
│   │       │   └── UnAuthorizedException.java
│   │       ├── favorite
│   │       │   ├── controller
│   │       │   │   └── FavoriteController.java
│   │       │   └── model
│   │       │       ├── FavoriteDto.java
│   │       │       ├── mapper
│   │       │       │   └── FavoriteMapper.java
│   │       │       └── service
│   │       │           ├── FavoriteService.java
│   │       │           └── FavoriteServiceImpl.java
│   │       ├── interceptor
│   │       │   ├── ConfirmInterceptor.java
│   │       │   └── ConfirmInterceptor2.java
│   │       ├── member
│   │       │   ├── controller
│   │       │   │   └── MemberController.java
│   │       │   └── model
│   │       │       ├── MemberDto.java
│   │       │       ├── mapper
│   │       │       │   └── MemberMapper.java
│   │       │       └── service
│   │       │           ├── MemberService.java
│   │       │           └── MemberServiceImpl.java
│   │       ├── plan
│   │       │   ├── controller
│   │       │   │   └── PlanController.java
│   │       │   └── model
│   │       │       ├── PlanDto.java
│   │       │       ├── mapper
│   │       │       │   └── PlanMapper.java
│   │       │       └── service
│   │       │           ├── PlanService.java
│   │       │           └── PlanServiceImpl.java
│   │       └── util
│   │           ├── JWTUtil.java
│   │           ├── PageNavigation.java
│   │           ├── ParameterCheck.java
│   │           └── SizeConstant.java
│   ├── favorite.cld
│   ├── member.cld
│   ├── newfile.cld
│   └── plan.cld
└── resources
    ├── application.properties
    └── mapper
        ├── attraction.xml
        ├── board.xml
        ├── favorite.xml
        ├── member.xml
        └── plan.xml
```
