보안 HTTP
=


HTTP(Hyper Text Transfer Protocol)
-

서버/클라이언트 모델을 따라 데이터를 주고 받기 위한 프로토콜이다.

즉, HTTP는 인터넷에서 하이퍼텍스트를 교환하기 위한 통신 규약으로, 80번 포트를 사용하고 있다. 따라서 HTTP 서버가 80번 포트에서 요청을 기다리고 있으며, 클라이언트는 80번 포트로 요청을 보내게 된다.

![image](https://user-images.githubusercontent.com/70934609/112887098-b871cc00-910d-11eb-9a29-9c6499e84012.png)


HTTPS
-
- 보안 HTTP중 가장 인기있는 방식이다.

- http://가 아닌 https:// 형식을 사용한다.

- HTTPS를 사용할 때, 모든 HTTP 요청과 응답은 네트워크로 보내지기 전에 암호화를 한다.
HTTP(어플리케이션 계층)와 TCP(전송 계층) 사이에 보안 계층을 제공, 이곳에서 인코딩, 디코딩을 실행한다.

- Secure Sockets Layer: SSL

- Transport Layer Security: TLS

![image](https://user-images.githubusercontent.com/70934609/112884791-e275bf00-910a-11eb-805d-273c842cb155.png)


HTTPS는 SSL과 같은 프로토콜을 사용하여 공개키/개인키 기반으로 데이터를 암호화하고 있다. 데이터는 암호화되어 전송되기 때문에 임의의 사용자가 데이터를 조회하여도 원본의 데이터를 보는 것은 불가능하다.

### 공개키, 개인키

HTTPS는 공개키/개인키 암호화 방식을 이용해 데이터를 암호화하고 있다. 공개키와 개인키는 서로를 위한 1쌍의 키이다.

- 공개키: 모두에게 공개가능한 키

- 개인키: 나만 가지고 알고 있어야 하는 키


공개키 암호화: 공개키로 암호화를 하면 개인키로만 복호화할 수 있다. -> 개인키는 나만 가지고 있으므로, 나만 볼 수 있다.

개인키 암호화: 개인키로 암호화하면 공개키로만 복호화할 수 있다. -> 공개키는 모두에게 공개되어 있으므로, 내가 인증한 정보임을 알려 신뢰성을 보장할 수 있다.

![image](https://user-images.githubusercontent.com/70934609/112887337-038bdf00-910e-11eb-82b2-c02399ec2bd5.png)


보안 전송 셋업

- A기업은 HTTP 기반의 애플리케이션에 HTTPS를 적용하기 위해 공개키/개인키를 발급함

- CA 기업에게 돈을 지불하고, 공개키를 저장하는 인증서의 발급을 요청함

- CA 기업은 CA기업의 이름, 서버의 공개키, 서버의 정보 등을 기반으로 인증서를 생성하고, CA 기업의 개인키로 암호화하여 A기업에게 이를 제공함

- A기업은 클라이언트에게 암호화된 인증서를 제공함

- 브라우저는 CA기업의 공개키를 미리 다운받아 갖고 있어, 암호화된 인증서를 복호화함

- 암호화된 인증서를 복호화하여 얻은 A기업의 공개키로 데이터를 암호화하여 요청을 전송함


![image](https://user-images.githubusercontent.com/70934609/112885843-2e753380-910c-11eb-9b98-dbc1aa6f4f7a.png)


### SSL 핸드셰이크

1. 프로토콜 버전 번호 교환

2. 양쪽이 알고 있는 암호 선택

3. 양쪽의 신원을 인증

4. 채널을 암호화하기 위한 임시 세션 키 생성


*서버 인증서란: 누군가가 보안 트랜잭션을 수행 할 때, 개인 정보를 보내기 전, ㅓ그 서버를 얼마나 신뢰할 수 있는지 평가하는 것을 도와줌*