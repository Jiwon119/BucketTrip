Server 폴더 구조
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
