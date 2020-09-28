﻿# readmExample

> 리드미 양식입니다. 꼭 따라야 할 필요는 없지만 뭘 넣을 지 고민된다면 참고해주세요.

## 목차
- [개요](#개요)
- [기능](#기능)
- [유사 서비스](#유사-서비스) 
- [향후 전망](#향후-전망)
- [기술 스택](#기술-스택)
- [기술 설명](#기술-설명)
	- [ERD](#erd)
	- [디렉토리 구조도](#디렉토리-구조도)
	- [기타](#기타)
- [테스트 방법](#테스트-방법)

## 데이터
> https://velog.io/@marcus/TOY-1-%EB%9D%BC%EC%9D%B4%EC%97%87-API%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%B4%EC%84%9C-%EB%A1%A4-%EC%A0%84%EC%A0%81-%EC%82%AC%EC%9D%B4%ED%8A%B8%EB%A5%BC-%EB%A7%8C%EB%93%A4%EC%96%B4%EB%B3%B4%EC%9E%90-gojpscoym4

> MBTI 설문 데이터
	-	없는 소환사명 filter

> 추가 아이디어들

## 개요
> 프로젝트를 간략하게 설명해주세요  
> 프로젝트를 개발하게 된 동기도 들어가 있으면 좋습니다.



## 기능
> 프로젝트의 기능들을 설명해주세요  
> 스크린샷이나 gif등으로 한눈에 볼 수 있게 하면 더 좋습니다

1. 소환사 - 챔피언 추천 by 플레이한 챔피언 숙련도 기준
	- 숙련도
2. 소환사 - 챔피언 추천 by 플레이한 챔피언 판수 기준
	- matching data 모아야함.
	- 트롤픽처리해야할수도
3. 챔피언 매칭에 따른 아이템트리 추천
	+ 승률까지 따질수도?
		+ 결과론적인 해석이 가능함, 이겼기에 이런템을 갔다.뭐 이런거...?
4. MBTI - 챔피언 추천 by MBTI 유저별 플레이 성향
	- 

## 사용 알고리즘

1. https://macan.tistory.com/1
-	오픈소스 쓰면 성능이 ㅈ호아
- 내 챔피언과 상대 챔피언에 따른 템트리 추천 시스템

## 유사 서비스
> 프로젝트와 유사한 서비스들이 있다면 소개해 주고 여러분의 프로젝트 만의 차이점을 기술해주세요

## 향후 전망
> 부득이한 사정으로 프로젝트에 구현하지는 못했지만 보완할 점이나 추가할 점이 있다면 적어주세요

## 기술 스택
> 프로젝트를 구현 할 때 사용한 기술들을 적어주세요

## 기술 설명

### ERD
> DB 및 백엔드를 구현할 때 ERD를 그려보고 리드미에 남겨주세요

### 디렉토리 구조도
> 폴더 구조가 어떻게 되는지 폴더, 파일별 역할들을 간략하게 적어주세요  
> 너무 자세히 적을 필요는 없습니다

### 기타
> 이외에도 프로젝트를 이해하기 위해 필요한 것들을 적어주세요 (팀별 개발표준, API Documentation 등등...)

## 테스트 방법
> 프로젝트를 배포한 url과 테스트하기 위한 계정 ID/PW를 적어주세요

## 데이터 정제

0.전처리용 테이블
> 최상위유저 테이블 /lol/league/v4/challengerleagues/by-queue/{queue}
/lol/league/v4/grandmasterleagues/by-queue/{queue}

1. Django
소환사 테이블 /lol/league/v4/challengerleagues/by-queue/{queue}
/lol/summoner/v4/summoners/by-name/{summonerName} -> accountId가져오기

summonerId, summonerName, leaguePoint, rank, wins, losses, accountId

챔피언정보테이블
아이템정보테이블

소환사별 챔피언 숙련도 /lol/champion-mastery/v4/champion-masteries/by-summoner/{encryptedSummonerId}
championId, championLevel, championPoints, lastPlayTime,
 chestGranted, tokensEarned, summonerId

소환사별 매치기록 /lol/match/v4/matchlists/by-account/{encryptedAccountId}
championId, gameId

매치테이블 /lol/match/v4/matches/{matchId}
input: championId, gameId
output: win, item0, item1, item2, item3, item4, item5, item6, lane, teamId
 -> input: lane, teamId(100,200)
     output: championId


MBTI테이블
summonerId, MBTI

2. Spring
유저테이블
uId, password, summonerId, summonerName, accountId

3. 예시 및 방식
id = k1yKswuFwjLUjBLCM1OSiZqje_i8TJURNeblpioB6RdVnA
accountid = PtVDGmeP546DaWTdsjdBwdwxZGQUbvZHXs3l5tR9voGh
puuid = FTuNdqNCmoLUsmQFNp8jHfI3JklwPLQajWoX-85SkR5VEhznRq78VMnh5VVhWe9ULW9f5UXVGzANew
4628056135

matchlists -> championID == match -> championID
-> stats -> item
type
participantId -> 몇번째 유저
itemId -> 아이템 아이디

user가 플레이한 챔피언 성향에 따른 추천  - 명준

user MBTI 성향에 따른 챔피언 추천 - 명준

챔피언 매치에 따른 아이템 템트리 추천 - 효준

mbti 궁합(간단), mbti + 챔프 ->듀오 추천 -나
예) ENTP(나랑 궁합이 잘맞는 mbti)가 많이쓰는 챔프들
     중에 나의 모스트와? 승률이 높은 챔프 추천?
    
    나의 모스트와 승률이 가장 높은 챔피언을 많이쓰는 mbti유저를 추천

    INFP(나)와 다른 mbti와 듀오했을때 승률이 가장 높은 mbti를 추천(데이터가 적음)

    나의 mbti의 모스트 5개에서 승률이 높은 챔피언 각 2개씩 뽑아서 이 챔프를 플레이를 많이하는 mbti를 추천
    (mbti궁합추천 및 챔프 추천)
    챔프 10개를 띄워줌 이 10개에 각각 mbti 출력?

	이즈	레오나(승률) - mbti
		쓰레쉬(승률) - mbti
	케틀
	모데
	아트
	아칼리

통계(ex mbti 별 티어, 라인 ) 차트 등
예) mbti별 모스트1(판수)
     mbti와 티어의 상관관계
     


라인추천 주, 부 (생각좀 ㄱㄱ)
챔피언 역할군 (암살자, 전사, 마법사 등)
itemId -> 아이템 아이디


