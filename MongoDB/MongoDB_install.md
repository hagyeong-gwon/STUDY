## MongoDB Install
사용 cloud - amazon 2 ec2

- yum Mongo Package를 설치하기 전 설정파일을 생성한다.
```
vim etc/yum.repos.d/mongodb-org-{몽고 버전 ex. 4.2, 4.4...}.repo

[mongodb-org-{version}]
name=MongoDB Repository
baseurl=https://repo.mongodb.org/yum/amazon/2/mongodb-org/{version}/aarch64/
gpgcheck=1
enabled=1
gpgkey=https://www.mongodb.org/static/pgp/server-{version}.asc
```
*리눅스 아키텍처 x86사용시   baseurl=https://repo.mongodb.org/yum/amazon/2/mongodb-org/4.2/x86_64/로 설정한다.*
```
yum install -y mongodb-org
```
- 위 명령어 실행 시 mongod.conf 파일이 없다면 default로 설정된다.

default conf 위치: /etc/mongod.conf

```
system start mongod
```
- Mongo 실행