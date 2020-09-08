# readmExample

> 리드미 양식입니다. 꼭 따라야 할 필요는 없지만 뭘 넣을 지 고민된다면 참고해주세요

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

## 개요
> 프로젝트를 간략하게 설명해주세요  
> 프로젝트를 개발하게 된 동기도 들어가 있으면 좋습니다.

## 기능
> 프로젝트의 기능들을 설명해주세요  
> 스크린샷이나 gif등으로 한눈에 볼 수 있게 하면 더 좋습니다

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
최상위유저 테이블 /lol/league/v4/challengerleagues/by-queue/{queue}
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