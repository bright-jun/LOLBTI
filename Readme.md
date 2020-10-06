# readmExample

> 리드미 양식입니다. 꼭 따라야 할 필요는 없지만 뭘 넣을 지 고민된다면 참고해주세요.

## 목차
- [개요](#개요)
- [기능](#기능)
- [유사 서비스](#유사-서비스) 
- [향후 전망](#향후-전망)
- [기술 스택](#기술-스택)
- [기술 설명](#기술-설명)
	- [ERD](#erd)
	- [PICKLE](#pickle)
	- [팀별 개발표준](#팀별-개발표준)
	- [RIOT API](#RIOT-API)
- [테스트 방법](#테스트-방법)
- [디렉토리 구조도](#디렉토리-구조도)

## 데이터
> https://velog.io/@marcus/TOY-1-%EB%9D%BC%EC%9D%B4%EC%97%87-API%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%B4%EC%84%9C-%EB%A1%A4-%EC%A0%84%EC%A0%81-%EC%82%AC%EC%9D%B4%ED%8A%B8%EB%A5%BC-%EB%A7%8C%EB%93%A4%EC%96%B4%EB%B3%B4%EC%9E%90-gojpscoym4

> MBTI 설문 데이터
	-	없는 소환사명 filter

> 추가 아이디어들

## ✋개요
> 프로젝트를 간략하게 설명해주세요
> 프로젝트를 개발하게 된 동기도 들어가 있으면 좋습니다.

> 리그 오브 레전드를 플레이 하시는 분, 플레이 해보고 싶으신 분 모두 환영합니다
>
> 어떤 새로운 챔피언을 해야 할 지 고민인 분!
>
> 게임을 처음 해봐서 어떤 챔피언을 해야 할 지 모르겠는 분!
>
> 어떤 아이템을 사야 할 지 모르겠는 분!
>
> 리그 오브 레전드게임의 챔피언, 아이템들을 추천해드립니다!

## ⭐기능
> 프로젝트의 기능들을 설명해주세요

> 스크린샷이나 gif등으로 한눈에 볼 수 있게 하면 더 좋습니다
1. 사용자의 챔피언 숙련도를 분석

	1-1. 챔피언 추천

	1-2. MBTI 예상

2. 사용자의 MBTI를 분석

	2-1. 챔피언 추천

3. 플레이 할 챔피언 vs 상대 챔피언에 맞는 아이템 분석

	3-1. 16만건이상의 매치 데이터를 분석하여 활용도가 높은 

4. 실시간 소환사 정보 수집 기능


## 🛠사용 알고리즘

1. 협업 필터링
	-	순서
		1.	사용자들간의 유사도를 구한다
		2.	유사도가 높은 상위n개의 사용자들의 데이터를 추출한다
		3.	추출한 사용자들의 값을 유사도만큼 가중치를 두어 목표 사용자가 평가하지 않은 항목에 대해 점수를 매긴다
		4.	예상 점수를 기준으로 정렬하여 추천한다
	-	유사도
		-	Cosine Similarity
			-	식 그림
		- 	문제점
		- 	0인 값을 제외한 행을 Cosine_similariy로 비교하여 구한다.
			>	유사도(a,b) = 유사도(b,a)
			>
			>	굳이 같을필요는 없다.
			>
			>	target에 대해서 -> 유사도를 알기만 하면 된다.
			>
			>	5 5 0 0 0
			>
			>	5 5 0 0 0 --- 1000
			>
			>	5 5 0 1 0 ---
			>
			>	5 5 4 0 5 --- 1
			>
			>	`5 5 0 0 0`은 `5 5 0 1 0`과 `5 5 4 0 5`에서 골고루 영향을 받아야한다.
			>
			>	하지만 `5 5 0 1 0`에 영향을 더 많이 받는다.
			>
			>	극단적으로 `5 5 0 0 0`이 1000개 있으면 추천못받음
		-	전체 행을 Cosine_similarity 비교하지 않는다
	-	점수 예측하는 식
		-	
	-	사용된 테이블
		-	사용자 성향의 테이블
			-	len(user)*len(champ)
		-	사용자 유사도 테이블
			-	len(user)*len(user)
	-	효율성
		-	사용자 유사도 테이블을 매 유저의 정보다 하나 갱신될 때 마다 사용자 유사도 테이블을 갱신하는 경우 => len(user)*2 만큼처리함
			-	CosineSimilarity를 target에 따라 다르게 구하기 때문
		-	사용자 유사도를 추천요청을 받을 때 마다 타겟 사용자에 대한 다른 사용자들의 유사도를 구하는 경우 => len(user)만큼만 처리함
		-	후자를 선택하여 추천하였음

## ⚽유사 서비스
> 프로젝트와 유사한 서비스들이 있다면 소개해 주고 여러분의 프로젝트 만의 차이점을 기술해주세요

>	높은 품질의 기본적인 서비스를 개발하기에는 차별성이 부족하다 판단하여, 우리 서비스의 차별점을 중심으로 집중 개발하였음

<table style="border-collapse: collapse; width: 100%; height: 100px;" border="1" >
<thead>
<tr style="height: 20px;">
	<td style="width: 33%; height: 20px; text-align: center">기능</td>
	<td style="width: 33%; height: 20px; text-align: center">op.gg, fow.kr</td>
	<td style="width: 33%; height: 20px; text-align: center">LoLBTI</td>
</tr>
</thead>
<tbody>
<tr style="height: 20px;">
	<td style="width: 33%; height: 20px;text-align: center">아이템추천기능</td>
	<td style="width: 33%; height: 20px;text-align: center">단일챔피언기준</td>
	<td style="width: 33%; height: 20px;text-align: center">상대챔피언기준</td>
</tr>
<tr style="height: 20px;">
	<td style="width: 33%; height: 20px;text-align: center">실시간 업데이트</td>
	<td style="width: 33%; height: 20px;text-align: center">가능</td>
	<td style="width: 33%; height: 20px;text-align: center">가능</td>
</tr>
<tr style="height: 20px;">
	<td style="width: 33%; height: 20px;text-align: center">사용자 숙련도 기반 추천</td>
	<td style="width: 33%; height: 20px;text-align: center">없음</td>
	<td style="width: 33%; height: 20px;text-align: center">있음</td>
</tr>
<tr style="height: 20px;">
	<td style="width: 33%; height: 20px;text-align: center">사용자 MBTI 추천</td>
	<td style="width: 33%; height: 20px;text-align: center">없음</td>
	<td style="width: 33%; height: 20px;text-align: center">있음</td>
</tr>
<tr style="height: 20px;">
	<td style="width: 33%; height: 20px;text-align: center">선호 포지션</td>
	<td style="width: 33%; height: 20px;text-align: center">제공</td>
	<td style="width: 33%; height: 20px;text-align: center">제공</td>
</tr>
<tr style="height: 20px;">
	<td style="width: 33%; height: 20px;text-align: center">전적검색기능</td>
	<td style="width: 33%; height: 20px;text-align: center">상</td>
	<td style="width: 33%; height: 20px;text-align: center">하</td>
</tr>
</tbody>
</table>


## 🤷‍♂️향후 전망
> 부득이한 사정으로 프로젝트에 구현하지는 못했지만 보완할 점이나 추가할 점이 있다면 적어주세요
1.	API key 자원 확보
	-	사용자마다 게임결과 데이터를 100개이상씩 수집하여야 하는데, 100개 이상 수집하기 위해서는 실시간으로 많은 requests를 감당할 만큼의 API key 자원이 필요함.
	-	자원이 주어진다면 개발 가능 할 것.
	-	판수를 이용한 사용자 분석 서비스
	-	판수 데이터를 이용 가능
		-	챔피언 추천, MBTI 추천이 가능 할 것.
		-	MBTI 승률이 좋은 궁합 추천 가능 할 것.
2.	외부 서버에 배포
	-	실 서비스
	-	지속적 개발 및 확장 가능
	-	Heroku
	-	Free tier AWS
	-	NAS
3.	Tensorflow, Keras를 활용
	-	구현은 완료 되었으나, Django에 tensorflow, keras module 설치에 어려움을 겪음
	-	사용자 데이터를 통한 MBTI 예측 서비스
4.	기타 통계분석
	-	MBTI별 티어, 라인, 역할군 차트 및 추천
	-	MBTI와 티어의 상관관계

## 기술 스택
> 프로젝트를 구현 할 때 사용한 기술들을 적어주세요
1. Frontend
	-	Vue
	-	chartjs
		-	통계 시각화

2. Backend(Spring)
	-	JPA
		-	DB와 연동
	-	JWT

3. Backend(Django)
	-	pandas
		-	데이터 정제 및 분석
	-	requests
		-	API requests를 전송
	-	multiprocessor
		-	10개의 API key를 사용하여 병렬적으로 데이터 수집
	-	tensorflow/keras
		-	데이터 학습 및 예측

4. 협업 도구
	-	GitLab
		-	Commit 269회
		-	MR 47회
	-	JIRA
		-	6+(23+40)+(27+17) = 113개의 이슈&에픽
	-	Jenkins
		-	빌드 자동화 283회 
	-	MatterMost
		-	Jenkins~GitLab~Jira=>MatterMost 연동

## 기술 설명

### ERD
> DB 및 백엔드를 구현할 때 ERD를 그려보고 리드미에 남겨주세요

### Pickle

-	[저장 및 로딩속도에 관한 게시물](https://m.blog.naver.com/PostView.nhn?blogId=wideeyed&logNo=221250772912&proxyReferer=https:%2F%2Fwww.google.com%2F
)

<table style="border-collapse: collapse; width: 100%; height: 100px;" border="1" >
<thead>
<tr style="height: 20px;">
	<td style="width: 25%; height: 20px; text-align: center">기능</td>
	<td style="width: 25%; height: 20px; text-align: center">PICKLE</td>
	<td style="width: 25%; height: 20px; text-align: center">CSV</td>
	<td style="width: 25%; height: 20px; text-align: center">XLSX</td>
</tr>
</thead>
<tbody>
<tr style="height: 20px;">
	<td style="width: 25%; height: 20px; text-align: center">저장</td>
	<td style="width: 25%; height: 20px; text-align: center">206 ms </td>
	<td style="width: 25%; height: 20px; text-align: center">4s 870ms</td>
	<td style="width: 25%; height: 20px; text-align: center">1min 39s</td>
</tr>
<tr style="height: 20px;">
	<td style="width: 25%; height: 20px; text-align: center">읽기</td>
	<td style="width: 25%; height: 20px; text-align: center">222 ms </td>
	<td style="width: 25%; height: 20px; text-align: center">541ms</td>
	<td style="width: 25%; height: 20px; text-align: center">30s</td>
</tr>
</tbody>
</table>

-	Python에서 분석을 할 경우 pickle을 사용하여 데이터를 읽고 쓰기를 하기로 결정.

-	이슈
	-	Jenkins 자동 업로드 시, git 경로에있는 pickle이 덮어쓰기가 되는 경우가 있었음.
		-	git 외부 경로에 pickle저장 폴더 생성 및 접근
	-	AWS 서버 실행 경로에서는 읽는 권한만 있고, 쓰기 권한이 없었음
		-	쓰기 권한이 있는 경로에 django server를 자동으로 이관하게 Jenkins설정 하였음.



### 팀별 개발표준

-	Naming Rule
	-	[Camel case](https://www.geeksforgeeks.org/java-naming-conventions/)
		- 여러 단어인지 한 단어인지 구분이 안갈땐 space를 넣어보고 판단한다.
	-	request url에는 소문자만

-	💾 Git
	- Commit
		- {Resolved | } IssueID | description
		- description : 기능(english) 작업 내용(한글 명사형) 끝내기
	- Merge request
		- Title : title - 재량껏
		- Description : IssueID | description
	- Branch Handling
		- conflict를 최소화 하는 방향으로 진행.
		- 최대한 비슷한 branch들끼리 병합 후에(account, feed 따로) -> develop 에 merge
		- 최종 서버 업로드는 master branch 사용.
			- 개발 중인 상태에서는 develop branch 사용.
	- Branch naming
		- `master -> develop -> <developer-name>`
		- developer별로 개발 기능을 잘 나누면 점검이 용이하다.
		- 충돌 최소화
	- Issue
		- gitlab -> [issues 탭](https://lab.ssafy.com/s03-bigdata-sub3/s03p23a109/issues) 활용

### RIOT API

-	⭐⭐API KEY 제약사항 및 해결과정⭐⭐
	-	personal key(7days) & public key(1day)
		```
		20 requests every 1 seconds(s)
		100 requests every 2 minutes(s)
		```
	-	production key
		```
		500 requests every 10 seconds
		30,000 requests every 10 minutes
		```
	-	personal key 밖에 사용 불가능, 심지어 personal key도 발급받는데 한달넘게 걸렸음. 그래서 매일마다 갱신해야하는 public key를 사용해야 했음

	-	아이템 추천을 하기 위해서 16만개의 매치 데이터를 수집 했음. 총 1GB가량 되는 양. 최소 16만개의 requests요청해야했음.
	-	💥160000 / 100 * 2 min = 3200 min = 53.3 hour = 2일밤낮으로 돌려야 겨우 받아올 수 있음.
	>	❕ API key를 여러개를 확보하여, requests제한에 걸렸을때 key를 바꾸면서 요청하면 괜찮지 않을까?!
	-	API key를 8개를 확보했다
	-	이론상 160000 / 100 * 2 / 8 = 400 min = 6.7 hour 시간!
	-	💥하지만 제약사항에 걸리지않고 계속 request를 보낼 수 있는 속도는 5(requests/sec)였음
	-	600 (requests/2min)
	-	6개의 API KEY를 가졌을 때가 최상의 성능을 내고 그 이후부터는 API KEY의 증가가 의미가 없어짐
	-	왜냐? 병렬적으로 동시에 requests를 보내지 않고 기다리기때문.
	>	❕ 6개 이상의 API key를 모두 효율적으로 사용하기 위해서는 병렬적으로 처리하는 방법을 찾아야 한다.
	-	python에는 multiprocess 방식으로 병렬처리가 가능하다!
	-	n개의 API key를 모두 효율적으로 사용가능하게 되었다.
	-	최종적으로는 10개의 API key를 사용하여 16만개의 데이터를 수집하였음
	-	10 * 100 (requests/2min)
	-	160000 / 1000 * 2 = 320 min = 5.3 hour
	-	온전히 public key 만으로 production key의 1/3의 효율성으로 데이터를 수집할 수 있게 되었음

-	⭐⭐복잡한 API 구조⭐⭐
	-	RDBMS가 아닌 NoSQL 형식의 데이터들임.
		-	업데이트를 통한 서비스의 확장성을 고려하여 NoSQL형식을 사용하는 것 같음
	-	구조도
	-	여러개의 Key를 사용하면 암호화된 정보를 암호화시킨 Key와 매칭해서 뽑아줘야 한다. 그렇지 않으면 제대로 된 요청을 받을 수 없게 만들었음.
		-	매치데이터의 식별자인 MatchID는 다행스럽게 암호화되지 않음.
		-	여러개의 Key를 병렬적으로 처리하기 쉬움
		-	유저 식별자인 SummonerId, AccountId는 Key와 매칭해서 암호화가 되어있음.
		-	유저를 통한 숙련도 검색, 플레이 기록 검색은 Key와 매칭해서 검색해야 함.
		-	로직을 짜는게 💥💥💥복잡함💥💥💥
		-	중간중간 키의 요청권한을 넘거나, 키가 만료되는 경우를 다 생각해주어야 함. 

## 테스트 방법
>	프로젝트를 배포한 url과 테스트하기 위한 계정 ID/PW를 적어주세요

>	[https://j3a109.p.ssafy.io/](https://j3a109.p.ssafy.io/)
>
>	소환사명 입력
>
>	데이터가 없다면 갱신하기 누르기

### 디렉토리 구조도
> 폴더 구조가 어떻게 되는지 폴더, 파일별 역할들을 간략하게 적어주세요  
> 너무 자세히 적을 필요는 없습니다
```
 📦backend_spring
 ┣ 📂src
 ┃ ┣ 📂main
 ┃ ┃ ┣ 📂java
 ┃ ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┃ ┗ 📂web
 ┃ ┃ ┃ ┃ ┃ ┗ 📂curation
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂config
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SwaggerConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RecommendController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SummonerController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dao
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂user
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MbtiDao.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RecommendDao.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RecommendDaoImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserDao.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserRankDao.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserRankDaoImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂interceptor
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜JwtInterceptor.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂model
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂lol
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Champion.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Item.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂user
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Idpass.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Mbti.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜User.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜BasicResponse.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜JwtService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SummonerService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜WebCurationApplication.java
 ┃ ┃ ┗ 📂resources
 ┃ ┃ ┃ ┗ 📜application.properties
 ┃ ┗ 📂test
 ┃ ┃ ┗ 📂java
 ┃ ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┃ ┗ 📂web
 ┃ ┃ ┃ ┃ ┃ ┗ 📂curation
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜WebCurationApplicationTests.java
 ┣ 📂target
 ┃ ┣ 📂classes
 ┃ ┃ ┣ 📂com
 ┃ ┃ ┃ ┗ 📂web
 ┃ ┃ ┃ ┃ ┗ 📂curation
 ┃ ┃ ┃ ┃ ┃ ┣ 📂config
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SwaggerConfig.class
 ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RecommendController.class
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SummonerController.class
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserController.class
 ┃ ┃ ┃ ┃ ┃ ┣ 📂dao
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂user
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MbtiDao.class
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RecommendDao.class
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RecommendDaoImpl.class
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserDao.class
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserRankDao.class
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserRankDaoImpl.class
 ┃ ┃ ┃ ┃ ┃ ┣ 📂interceptor
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜JwtInterceptor.class
 ┃ ┃ ┃ ┃ ┃ ┣ 📂model
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂lol
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Champion.class
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Item.class
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂user
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Idpass.class
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Mbti.class
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜User.class
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜BasicResponse.class
 ┃ ┃ ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜JwtService.class
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SummonerService.class
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserService.class
 ┃ ┃ ┃ ┃ ┃ ┗ 📜WebCurationApplication.class
 ┃ ┃ ┗ 📜application.properties
 ┃ ┣ 📂generated-sources
 ┃ ┃ ┗ 📂annotations
 ┃ ┣ 📂generated-test-sources
 ┃ ┃ ┗ 📂test-annotations
 ┃ ┗ 📂test-classes
 ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┗ 📂web
 ┃ ┃ ┃ ┃ ┗ 📂curation
 ┃ ┃ ┃ ┃ ┃ ┗ 📜WebCurationApplicationTests.class
 ┣ 📜.classpath
 ┣ 📜.factorypath
 ┣ 📜.gitignore
 ┣ 📜.project
 ┣ 📜mvnw
 ┣ 📜mvnw.cmd
 ┣ 📜pom.xml
 ┗ 📜Readme.md

📦backend_django
 ┣ 📂lolBTI
 ┃ ┣ 📂lolBTI
 ┃ ┣ 📂userGameData
 ┃ ┃ ┣ 📜admin.py
 ┃ ┃ ┣ 📜apps.py
 ┃ ┃ ┣ 📜champ_dict.pkl
 ┃ ┃ ┣ 📜defs.py
 ┃ ┃ ┣ 📜dummy.pkl
 ┃ ┃ ┣ 📜item.pkl
 ┃ ┃ ┣ 📜mbti.py
 ┃ ┃ ┣ 📜models.py
 ┃ ┃ ┣ 📜recommend.py
 ┃ ┃ ┣ 📜recommendItem.py
 ┃ ┃ ┣ 📜setting.py
 ┃ ┃ ┣ 📜tests.py
 ┃ ┃ ┣ 📜update.py
 ┃ ┃ ┣ 📜urls.py
 ┃ ┃ ┣ 📜views.py
 ┃ ┣ 📜manage.py
 ┃ ┗ 📜requirements.txt
 ┣ 📜.gitignore
 ┗ 📜Readme.md

📦frontend
 ┣ 📂public
 ┃ ┣ 📜favicon.ico
 ┃ ┗ 📜index.html
 ┣ 📂src
 ┃ ┣ 📂api
 ┃ ┃ ┗ 📜UserApi.js
 ┃ ┣ 📂assets
 ┃ ┃ ┣ 📂images
 ┃ ┃ ┃ ┣ 📜lolbti_logo_1.png
 ┃ ┃ ┃ ┣ 📜lolbti_logo_2.png
 ┃ ┃ ┃ ┣ 📜lolbti_logo_green.png
 ┃ ┃ ┃ ┗ 📜lolbti_logo_greenblack.png
 ┃ ┃ ┣ 📂img
 ┃ ┃ ┃ ┣ 📜amumu.png
 ┃ ┃ ┃ ┣ 📜ashe.jpg
 ┃ ┃ ┃ ┣ 📜emblem_bronze.png
 ┃ ┃ ┃ ┣ 📜emblem_challenger.png
 ┃ ┃ ┃ ┣ 📜emblem_diamond.png
 ┃ ┃ ┃ ┣ 📜emblem_gold.png
 ┃ ┃ ┃ ┣ 📜emblem_grandmaster.png
 ┃ ┃ ┃ ┣ 📜emblem_iron.png
 ┃ ┃ ┃ ┣ 📜emblem_master.png
 ┃ ┃ ┃ ┣ 📜emblem_platinum.png
 ┃ ┃ ┃ ┣ 📜emblem_silver.png
 ┃ ┃ ┃ ┣ 📜khazix.jpg
 ┃ ┃ ┃ ┣ 📜lolbackground.jpg
 ┃ ┃ ┃ ┣ 📜loldcup.jpg
 ┃ ┃ ┃ ┗ 📜timo.png
 ┃ ┃ ┣ 📜logo.png
 ┃ ┃ ┗ 📜logo.svg
 ┃ ┣ 📂components
 ┃ ┃ ┣ 📂css
 ┃ ┃ ┃ ┗ 📜style.scss
 ┃ ┃ ┣ 📂home
 ┃ ┃ ┃ ┣ 📜ChampRecom.vue
 ┃ ┃ ┃ ┣ 📜Doughnut.js
 ┃ ┃ ┃ ┣ 📜FreChampList.vue
 ┃ ┃ ┃ ┣ 📜FreLineChart.vue
 ┃ ┃ ┃ ┣ 📜HomeMenu.vue
 ┃ ┃ ┃ ┣ 📜ItemRecom.vue
 ┃ ┃ ┃ ┣ 📜MbtiRecom.vue
 ┃ ┃ ┃ ┣ 📜RadarChart.js
 ┃ ┃ ┃ ┣ 📜RecentChampList.vue
 ┃ ┃ ┃ ┣ 📜RecommendChampList.vue
 ┃ ┃ ┃ ┣ 📜RecommendChampList2.vue
 ┃ ┃ ┃ ┣ 📜UserGameInfo.vue
 ┃ ┃ ┃ ┣ 📜userInfoDialog.vue
 ┃ ┃ ┃ ┣ 📜UserMbtiType.vue
 ┃ ┃ ┃ ┗ 📜WinLoseChart.vue
 ┃ ┃ ┣ 📜Banner.vue
 ┃ ┃ ┣ 📜Footer.vue
 ┃ ┃ ┗ 📜NavBar.vue
 ┃ ┣ 📂plugins
 ┃ ┃ ┗ 📜vuetify.js
 ┃ ┣ 📂views
 ┃ ┃ ┣ 📂error
 ┃ ┃ ┃ ┗ 📜PageNotFound.vue
 ┃ ┃ ┣ 📂user
 ┃ ┃ ┃ ┣ 📜Join.vue
 ┃ ┃ ┃ ┗ 📜Login.vue
 ┃ ┃ ┣ 📜Home.vue
 ┃ ┃ ┗ 📜Welcome.vue
 ┃ ┣ 📂vuex
 ┃ ┃ ┣ 📜actions.js
 ┃ ┃ ┣ 📜getters.js
 ┃ ┃ ┣ 📜mutations.js
 ┃ ┃ ┗ 📜store.js
 ┃ ┣ 📜App.vue
 ┃ ┣ 📜main.js
 ┃ ┗ 📜routes.js
 ```