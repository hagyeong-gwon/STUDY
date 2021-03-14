HTTP2
=

**HTTP는 TCP 연결 기반 위에서 동작하는 프로토콜**

기존의 문제점

1. 3-way Handshake

신뢰성 확보를 위해 연결을 맺고 끊는 데 있어서 핸드 셰이크가 이루어진다. 

거기에다 HTTP는 비연결성 프로토콜이기 때문에 한 번 연결로 한 번의 요청과 응답을 하고 응답이 끝나면 연결을 끊어 버린다. 연결을 맺고 끊을 때마다 핸드 셰이크를 하기 때문에 비연결성 프로토콜에선 오버헤드가 생긴다.

*오버헤드(overhead)는 어떤 처리를 하기 위해 들어가는 간접적인 처리 시간 · 메모리 등을 말한다.*

2. HOL(Head Of Line) Blocking 문제

HTTP/1.1에서 성능 개선을 위해 파이프라이닝이라는 기술이 도입되었다.

하지만 순차적으로 데이터를 요청하고 받아야 하다 보니 먼저 받은 요청이 끝나지 않으면 그 뒤에 있는 요청의 처리가 아무리 빨리 끝나도 먼저 온 요청이 끝날 때까지 기다려야 한다. 이를 HTTP의 HOL(Head Of Line) Blocking 문제라고 하고 파이프라이닝의 큰 문제이다. 


**파이프라이닝?**

*하나의 커넥션에서 한 번에 순차적인 여러 요청을 연속적으로 하고 그 순서에 맞춰 응답을 받는 방식으로 지연 시간을 줄이는 방법*

![image](https://user-images.githubusercontent.com/70934609/111078885-ff22cc00-853a-11eb-9cd6-6935ef338dec.png)

3. 무거운 Header 구조

http/1.1의 헤더에는 많은 메타정보들이 저장되어져 있다.  사용자가 방문한 웹페이지는 다수의 http요청이 발생하게 되는데 이 경우 매 요청시 마다 중복된 헤더값을 전송하게 되며 또한 해당 domain에 설정된 cookie정보도 매 요청시 마다 헤더에 포함되어 전송되며 어쩔땐 요청을 통해서 전송하려는 값보다 헤더 값이 더 큰경우도 있다.


이러한 문제들로 인해 2009년 구글은 웹을 더 빠르게 하겠다는 목표 아래 SPDY 프로토콜을 내놓았다. 그리고 2012년 10월 3일 HTTP 작업 그룹은 SPDY를 기반으로 HTTP/2.0 프로토콜을 설계하기로 결정하였음을 밝혔다.

HTTP/1.1과의 차이점
-

1. Multiplexed Streams

기존에 Plain Text(평문)를 사용하고, 개행으로 구별되면 HTTP/1.x 프로토콜과 달리, 2.0에서는 바이너리 포멧으로 인코딩 된 Message, Frame으로 구성된다.

![image](https://user-images.githubusercontent.com/70934609/111079229-68570f00-853c-11eb-944b-499621cf11e3.png)


HTTP 메시지를 바이너리 형태의 프레임으로 나누고 이를 전송 후 받은 쪽에서 다시 조립한다.


한 커넥션으로 동시에 여러개의 메세지를 주고 받을 있으며, 응답은 순서에 상관없이 stream으로 주고 받는다. 요청과 응답이 동시에 이루어지니 하나의 연결에 여러 요청과 응답이 뒤섞여 있다. HTTP/1.1의 Connection Keep-Alive, Pipelining의 개선이라 보면 된다.

![image](https://user-images.githubusercontent.com/70934609/111079474-82ddb800-853d-11eb-90dc-1a8e9ac625a3.png)

2. Server Push

클라이언트(브라우저)가 HTML문서를 요청했고 해당 HTML에 여러개의 리소스(CSS, Image...) 가 포함되어 있는경우 HTTP/1.1에서 클라이언트는 요청한 HTML문서를 수신한 후  HTML문서를 해석하면서 필요한 리소스를 재 요청한다.

 반면 HTTP/2에선 Server Push기법을 통해서 클라이언트가 요청하지도 않은 (HTML문서에 포함된 리소스) 리소스를 Push 해주는 방법으로 클라이언트의 요청을 최소화 해서 성능 향상을 이끌어 낸다. 
 
 이를 PUSH_PROMISE 라고 부르며 PUSH_PROMISE를 통해서 서버가 전송한 리소스에 대해선 클라이언트는 요청을 하지 않는다.

![image](https://user-images.githubusercontent.com/70934609/111079950-a43fa380-853f-11eb-9898-cd82f014433c.png)

3. HTTP Header Data Compression (HTTP 헤더 데이터 압축)

이전 Header의 내용과 중복되는 필드를 재전송 하지 않도록 하여, 데이터를 절약한다. 또한 기존에 HTTP Header가 Plain Text(평문)이었지만, HTTP/2에서는 Huffman Coding을 사용하는 HPACK이라는 Header 압축방식을 이용하여 데이터 전송 효율을 높였다.

![image](https://user-images.githubusercontent.com/70934609/111079971-b588b000-853f-11eb-8829-5c558b547546.png)


HTTP3
-

HTTP/3는 매우 이례적으로 기반 프로토콜을 UDP를 사용한다. 정확하게는 UDP를 기반으로 하는 QUIC를 사용한다. UDP를 사용하지만 그렇다고 기존의 신뢰성 있는 통신을 포기한 것은 아니다.

![image](https://user-images.githubusercontent.com/70934609/111080239-e4535600-8540-11eb-93ce-7b88380790b3.png)


**QUIC**

![image](https://user-images.githubusercontent.com/70934609/111080249-f7febc80-8540-11eb-804d-ec1c4509399d.png)


- 암호화가 프로토콜의 일부기능으로 포함되어 있다

- 스트림 연결과 암호화 스펙등을 포함한 모든 핸드쉐이크가 단일 요청/응답으로 끝난다.

```
QUIC은 TCP를 사용하지 않기 때문에 통신을 시작할 때 번거로운 3 Way Handshake 과정을 거치지 않아도 된다.

TCP는 연결을 생성하기 위해 기본적으로 1 RTT(Round Trip Time: 클라이언트가 보낸 요청을 서버가 처리한 후 다시 클라이언트로 응답해주는 사이클)가 필요하고, 여기에 TLS를 사용한 암호화까지 하려고 한다면 TLS의 자체 핸드쉐이크까지 더해져 총 3 RTT가 필요하다.

반면 QUIC은 첫 연결 설정에 1 RTT만 소요된다. 클라이언트가 서버에 어떤 신호를 한번 주고, 서버도 거기에 응답하기만 하면 바로 본 통신을 시작할 수 있다는 것이다. 즉, 연결 설정에 소요되는 시간이 반 정도 밖에 안된다.
```
![image](https://user-images.githubusercontent.com/70934609/111081015-98a2ab80-8544-11eb-96b1-cee47b4c8723.png)


- 패킷 손실 감지에 걸리는 시간 단축

- 통신이 멀티플렉싱 되며 이를 통해 HOLB(Head of line Blocking)를 극복할 수 있다

*멀티플렉싱: 하나의 통신 채널을 통해서 둘 이상의 데이터를 전송하는데 사용되는 기술이다.*

- 클라이언트의 IP가 바뀌어도 연결이 유지된다.

```
QUIC은 Connection ID를 사용하여 서버와 연결을 생성한다. Connection ID는 랜덤한 값일 뿐, 클라이언트의 IP와는 전혀 무관한 데이터이기 때문에 클라이언트의 IP가 변경되더라도 기존의 연결을 계속 유지할 수 있다.
```

