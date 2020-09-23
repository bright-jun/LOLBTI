# 🌏 AWS
- server : ubuntu 18.04 
	- 서버가 하나의 노트북이라고 생각하면 됩니다. 
- hostname : j3a109.p.ssafy.io
- 서버 접속
	- [윈도우 10 에서 pem 파일을 사용하기 – 아마존 라이트세일 리눅스 서버에 접속하는 방법(SSH)](https://swiftcoding.org/lightsail-from-window10)
	- [OpenSSH 클라이언트 설치 확인](https://m.blog.naver.com/PostView.nhn?blogId=alice_k106&logNo=220882708567&proxyReferer=https:%2F%2Fwww.google.com%2F)
	- `ssh -i ubuntu@j3a109.p.ssafy.io`
		```
		$ ssh -i I3A310T.pem ubuntu@i3a310.p.ssafy.io
		load pubkey "I3A310T.pem": invalid format
		The authenticity of host 'i3a310.p.ssafy.io (52.78.99.106)' can't be established.
		ECDSA key fingerprint is SHA256:7kmpFDLXVPVWEYBnJ7GjaTUwh9EL7VkturtGkeoljwM.
		Are you sure you want to continue connecting (yes/no/[fingerprint])? yes
		Warning: Permanently added 'i3a310.p.ssafy.io,52.78.99.106' (ECDSA) to the list of known hosts.
		Connection closed by 52.78.99.106 port 22
		```
	- [리눅스 서버 root와 사용자, 그리고 CLI 명령어 실행 시 주의사항](https://swiftcoding.org/remind-of-cli-commands)
	- 서버 시간 설정

- Docker
	- 설치
		- `curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -`
		- `sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"`
		- `sudo apt-get update && sudo apt-get install docker-ce`
- Maria DB
	- user : root
	- password : 9kzpyfZ9th?XpA_G
	- 생성
		- `docker run --name maria-db -p 3306:3306 -e MYSQL_ROOT_PASSWORD=9kzpyfZ9th?XpA_G -d mariadb`
	- 접속
		- `docker exec -it maria-db mysql -u root -p`

- git clone
	```
	ubuntu@ip-172-26-2-101:~/sub-pjt2-2$ git clone https://lab.ssafy.com/s03-webmobile2-sub2/S03P13A310.git
	```
- 프론트
	- nginx로 http에 요청을 보내면 tomcat
		- node.js 설치 [[참고]](https://linuxize.com/post/how-to-install-node-js-on-ubuntu-18.04/)
			- npm 사용을 위해서
			- `node --version`
			- `npm --version`
	- global 환경설정
		- yarn 설치 [[참고]](https://linuxize.com/post/how-to-install-yarn-on-ubuntu-18-04/)
			- `yarn --version`
		- vue-cli 설치
			- `yarn global add @vue/cli`
		- ~~vue 설치~~
			- `npm install vue`
			- `vue -version`
	- `/frontend`에서 환경설정
		- 통합명령어
            - `yarn install`
			- `yarn add node-sass sass-loader && yarn add vue-router vuex && yarn add vue-session && yarn add vue bootstrap-vue bootstrap && yarn add axios && yarn add firebase && yarn add proj4`
		- sass-loader 설치
			- `yarn add node-sass sass-loader`
		- vuex 설치
			- `yarn add vue-router vuex`
		- vue session 설치
			- `yarn add vue-session`
		- bootstrap 설치
			- `yarn add vue bootstrap-vue bootstrap`
		- axios 설치
			- `yarn add axios`
		- firebase 설치
			- `yarn add firebase`
		- 좌표변경
			- `yarn add proj4`
	- build
		- `yarn build` 또는
		- `npm run build`
	- nginx
		- 설치
			- `sudo apt-get update && sudo apt-get upgrade && sudo apt-get install nginx`
		- dist 경로 생성후 root경로로 설정해주기.
		- nginx에서 자동으로 설정한 경로를 임의로 바꿔줘야함
		- `sudo vim /etc/nginx/sites-available/default`
		```
		ubuntu@ip-172-26-1-226:~/a310/sub_pjt2_3_develop/frontend/dist$ pwd
		/home/ubuntu/a310/sub_pjt2_3_develop/frontend/dist
		```
		- nginx 재시작 (dist 경로에서하기)
			- `sudo systemctl restart nginx`
	- SSL 설정
		- [참고](https://velog.io/@pinot/Ubuntu-18.04%EC%97%90%EC%84%9C-Lets-Encrypt%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%98%EC%97%AC-Nginx%EC%97%90-SSL%EC%9D%84-%EC%A0%81%EC%9A%A9%ED%95%98%EB%8A%94-%EB%B0%A9%EB%B2%95)
		
		```
		IMPORTANT NOTES:
		- Congratulations! Your certificate and chain have been saved at:
		/etc/letsencrypt/live/i3a310.p.ssafy.io/fullchain.pem
		Your key file has been saved at:
		/etc/letsencrypt/live/i3a310.p.ssafy.io/privkey.pem
		Your cert will expire on 2020-11-18. To obtain a new or tweaked
		version of this certificate in the future, simply run certbot again
		with the "certonly" option. To non-interactively renew *all* of
		your certificates, run "certbot renew"
		- If you like Certbot, please consider supporting our work by:

		Donating to ISRG / Let's Encrypt:   https://letsencrypt.org/donate
		Donating to EFF:                    https://eff.org/donate-le

		```
		```
		- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
		new certificate deployed with reload of nginx server; fullchain is
		/etc/letsencrypt/live/i3a310.p.ssafy.io/fullchain.pem
		- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

		- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
		** DRY RUN: simulating 'certbot renew' close to cert expiry
		**          (The test certificates below have not been saved.)

		Congratulations, all renewals succeeded. The following certs have been renewed:
		/etc/letsencrypt/live/i3a310.p.ssafy.io/fullchain.pem (success)
		** DRY RUN: simulating 'certbot renew' close to cert expiry
		**          (The test certificates above have not been saved.)
		- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

		IMPORTANT NOTES:
		- Your account credentials have been saved in your Certbot
		configuration directory at /etc/letsencrypt. You should make a
		secure backup of this folder now. This configuration directory will
		also contain certificates and private keys obtained by Certbot so
		making regular backups of this folder is ideal.

		```
- 백엔드(Spring)
	- global 환경설정
		- maven 설치
			- `sudo apt install maven`
	- build(backend에서)
		- `mvn clean package`
			```
			ubuntu@ip-172-26-2-101:~/sub-pjt2-2/feature-userpage/backend$ cd target
			ubuntu@ip-172-26-2-101:~/sub-pjt2-2/feature-userpage/backend/target$ ls
			classes                 maven-archiver  webcuration-0.0.1-SNAPSHOT.jar
			generated-sources       maven-status    webcuration-0.0.1-SNAPSHOT.jar.original
			generated-test-sources  test-classes
			```
			- `mvn clean package`
				- 에러남 무슨차이?
		- 백엔드 서버 가동 : 내부의 tomcat 서버 가동
			- `.jar` 파일이 있는 `target`경로에 들어가서
			- `sudo java -jar webcuration-0.0.1-SNAPSHOT.jar &`
	- PM2
		- [PM2란?](https://cheese10yun.github.io/PM2/)
			- 앱에서 충돌이 발생할 경우 앱을 자동으로 다시 시작
			- 런타임 성능 및 자원 소비에 대한 통찰력을 획득
			- 성능 향상을 위해 설정을 동적으로 수정
			- 클러스터링을 제어
		- [documentation](https://pm2.keymetrics.io/docs/usage/pm2-doc-single-page/)
		- 설치
			- `npm install pm2 -g`
		- 설정
			- app.json 설정 (backend & frontend 와 같은 레벨에 생성)
				```
				{
					"apps": [{
						"name": "tenten",
						"cwd": ".",
						"script": "java",
						"args": [
							"-jar",
							"/home/ubuntu/a310/sub_pjt2_3_develop/backend/target/webcuration-0.0.1-SNAPSHOT.jar"
						],
						"env": {
						},

						"node_args": [],
						"log_date_format": "YYYY-MM-DD HH:mm Z",
						"exec_interpreter": "none",
						"exec_mode": "fork"
					}]
				}
				```
			- 자동으로 `java -jar /...경로.../webcuration-0.0.1-SNAPSHOT.jar`를 실행하게 설정
			
		- 시작
			- `pm2 start app.json`
		- 웹 뷰 지원
			- `pm2 plus`


	- WAS(Tomcat8 설치)

- 백엔드(django)설치
    - python 설치
        - https://aliwo.github.io/swblog/linux/ubuntu/ubuntu-new-python/#
    - pip3 설치
        - `sudo apt install python3-pip`
    - django 설치
        - `pip install django~=2.2.7`
