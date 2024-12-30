package sist.server;
import java.io.*;
import java.net.*;
import java.util.*;
import sist.commons.*;
import sist.server.*;
import sist.client.*;

/*
 * class Server {
 * 	Vector waitVc = new Vector()
 * 	=> 접속자 저장 공간(IP)
 * 	class Client extends Thread {
 * 		접속자마다 따로 통신만 담당
 * 				-------- Thread
 * 						 ------
 * 						 | 접속된 사람들과 통신
 * 		멤버 클래스를 이용한다
 * 	}
 * }
 * 서버 안에 내부 클래스, Client 를 선언하는 이유
 * class Server {
 * 		Vector w = new Vector()
 *
 * 		=> new Server()
 * }
 * class Client {
 * 		Server server = new Server()
 * 		server.w
 *
 * }
 * => port 가 같아서 사용할 수 없다(server 두 번 실행)
 * Vector 를 static 으로 선언하거나 Client 를 내부 클래스로 선언한다
 */
// 접속 시에 처리 => 교환 소켓 / 대기 소켓
public class Server implements Runnable{
    private Vector<Client> waitVc = new Vector<Client>(); // Server.Client 외부에서 가져올 때는 Server.Client 로 써야한다
    // 1. 저장 공간(접속자) => 동기화
    /*
     * Vector => 네트워크(요즘엔 자바로 구축하지 않아 사라지고 있다)
     * ArrayList => 데이터베이스
     * LinkedList => 추가 / 수정 / 삭제 가 많은 곳
     */
    // 2. 서버 가동
    private ServerSocket ss;
    // 3. 접속 시 => 연결 라인 => PORT
    private final int PORT = 3050;
    // 4. 서버 가동 => ServerSocket 초기화
    public Server() {
        try {
            ss = new ServerSocket(PORT);
            // bind => 개통(유심)
            // listen() => 대기
            System.out.println("Server Start...");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void run() {
        try {
            while (true) {
                Socket s = ss.accept();
                // 접속 시에만 호출 => 발신자의 정보(ip / port) => accept 메서드
                // => Socket(ip / port)
                // => 사용자는 port => 자동으로 설정
                // => 어떤 위치든 상관없다
                Client client = new Client(s); // s -> 한 명하고만 연결이 된다
                // s => port(윈도우마다 다르다)
                // => 통신을 시작하라 명령
                client.start(); // run() 호출
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    // 5. 접속 시 처리 = 사용자 정보를 받아서 Client 클래스로 전송
    // 통신이 가능하게 만든다
    public static void main(String[] args) {
        Server server = new Server();
        new Thread(server).start(); // <-
        // while 문 미작성
    }
    // 클라이언트마다 통신을 담당
    // Server 가 가지고 있는 모든 자원(변수, 메서드)을 사용할 수 있다
    // => 쓰레드는 데이터가 없고 데이터 공유 할 때 쓴다
    /*
     * 내부 클래스 호출 방법 => Server.Client
     * 한 개의 프로그램 안에서 여러 개의 프로그램을 동시에 실행 -> 쓰레드를 통해서
     * -----------		 ---------------
     * 	프로세스				쓰레드
     */
    class Client extends Thread {
        Socket s; // 클라이언트의 IP
        OutputStream out; // 송신
        BufferedReader in; // 수신 => 한글이 포함 => 2 byte 로 받아오기 때문에 Reader 로 처리

        String id, name, sex, pos;
        // DB => id 중복체크 => 중복 없이 => PRIMARY KEY
        public Client (Socket s) {
            try {
                /*
                 * 서버 => 클라이언트 IP
                 * 클라이언트 => 서버 IP
                 *
                 * => readLine() <=> write() 주고 받아서 처리한다
                 * => write() <=> readLine() // readLine() 통해서 받는다
                 */
                this.s = s;
                // s -> 클라이언트 정보
                out = s.getOutputStream();
                // 클라이언트로 전송
                in = new BufferedReader(new InputStreamReader(s.getInputStream())); // InputSream 을 Reader 로 변환
                // 클라이언트로부터 값을 수신
            } catch (Exception ex) {
                ex.printStackTrace();
                closeStreams();
            }
            /*
             * 자바 프로그램에서 가장 쉬운 프로그램
             * 					---------- 패턴의 개수
             * => 네트워크
             * => 데이터베이스 => MyBatis / JPA(모두 같은 코딩)
             */
        }
        // gpt 추가 메서드
        private void closeStreams() {
            try {
                if (in != null) {
                    in.close();
                    in = null;
                }
                if (out != null) {
                    out.close();
                    out = null;
                }
                if (s != null) {
                    s.close();
                    s = null;
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        // 실제 통신
        public void run() {
            try {
                while (true) {
                    if (in == null) {
                        System.out.println("Input stream is null. Exiting Thread");
                        return;
                    }
                    // 1. 사용자 요청값 받기
                    String msg = in.readLine();
                    if (msg == null) {
                        System.out.println("Client disconnected");
                        break;
                    }
                    System.out.println("Client => " + msg);
                    handleProtocol(msg);

                    // 로그인 => 100|id|name|sex -> 전송
//                    StringTokenizer st = new StringTokenizer(msg, "|");
//                    int protocol = Integer.parseInt(st.nextToken()); // 100

//                    switch (protocol) {
//                        case Function.LOGIN: { // 로그인 요청 시
//                            // 로그인 시 회원가입 내용을 전송(회원가입 미구현(DB X) -> StringTokenizer 로 대체)
//                            id = st.nextToken();
//                            name = st.nextToken();
//                            sex = st.nextToken();
//                            pos = "대기실";
//
//                            // 1. 접속이 된 사람 => 정보 전송
//                            messageAll(Function.LOGIN + "|"
//                                    + id + "|" + name + "|" + sex + "|" + pos);
//                            // 2. 입장 메세지 전송
//                            messageAll(Function.WAITCHAT + "|[알림]" + name + "님 입장하셨습니다");
//                            // 3. waitVc 에 저장
//                            waitVc.add(this);
//                            // 5. 화면을 변경
//                            messageTo(Function.MYLOG + "|" + id);
//                            // 6. 접속된 모든 정보를 로그인 된 사람에게 전송
//                            for (Client c : waitVc) {
//                                messageTo(Function.LOGIN + "|" + c.id + "|" + c.name + "|" + c.sex + "|" + c.pos);
//                            }
//                        }
//                        break;
//                        case Function.WAITCHAT:{
//                            messageAll(Function.WAITCHAT + "|[" + name + "] " + st.nextToken());
//                        }
//                        break;
//                        case Function.EXIT:{
//                            messageAll(Function.EXIT + "|" + id);
//                            messageAll(Function.WAITCHAT + "|[알림]" + name + "님이 퇴장하셨습니다.");
//                            messageTo(Function.MYEXIT + "|");
//                            // 행위를 하는 사람 => this
//                            for (int i = 0; i < waitVc.size(); i++) { // 나가기 시 제거
//                                Client c = waitVc.get(i);
//                                if (c.id.equals(id)) {
//                                    waitVc.remove(i);
//                                    try {
//                                        if (in != null) {
//                                            in.close();
//                                            in = null;
//                                        }
//                                        if (out != null) {
//                                            out.close();
//                                            out = null;
//                                        }
//                                        //in.close();
//                                        //out.close();
//                                    } catch (Exception ex) {
//                                        ex.printStackTrace();
//                                    } break;
//                                }
//                            }
//                        }
//                        break;
//                        /*
//                         * 서버의 기능
//                         * 1. 저장
//                         * 2. 수정
//                         * 3. 읽기 / 쓰기(송수신)
//                         * 4. 검색
//                         */
//                        case Function.INFO: {
//                            String yid = st.nextToken();
//                            for (Client c : waitVc) {
//                                if (yid.equals(c.id)) {
//                                    messageTo(Function.INFO + "|" + c.id + "|" + c.name + "|" + c.sex + "|" + c.pos);
//                                    break;
//                                }
//                            }
//                        }
//                        break;
//                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        private void handleProtocol(String msg) {
            try {
                StringTokenizer st = new StringTokenizer(msg, "|");
                int protocol = Integer.parseInt(st.nextToken());

                switch (protocol) {
                    case Function.LOGIN: {
                        id = st.nextToken();
                        name = st.nextToken();
                        sex = st.nextToken();
                        pos = "대기실";

                        messageAll(Function.LOGIN + "|"
                                + id + "|" + name + "|" + sex + "|" + pos);
                        messageAll(Function.WAITCHAT + "|[알림]" + name + "님 입장하셨습니다");
                        waitVc.add(this);
                        messageTo(Function.MYLOG + "|" + id);
                        for (Client c : waitVc) {
                            messageTo(Function.LOGIN + "|" + c.id + "|" + c.name + "|" + c.sex + "|" + c.pos);
                        }
                    }
                    break;
                    case Function.WAITCHAT: {
                        messageAll(Function.WAITCHAT + "|[" + name + "] " + st.nextToken());
                    }
                    break;
                    case Function.EXIT: {
                        messageAll(Function.EXIT + "|" + id);
                        messageAll(Function.WAITCHAT + "|[알림]" + name + "님이 퇴장하셨습니다.");
                        messageTo(Function.MYEXIT + "|");
                        for (int i = 0; i < waitVc.size(); i++) {
                            Client c = waitVc.get(i);
                            if (c.id.equals(id)) {
                                waitVc.remove(i);
                                closeStreams();
                                break;
                            }
                        }
                    }
                    break;
                    case Function.INFO: {
                        String yid = st.nextToken();
                        for (Client c : waitVc) {
                            if (yid.equals(c.id)) {
                                messageTo(Function.INFO + "|" + c.id + "|" + c.name + "|" + c.sex + "|" + c.pos);
                                break;
                            }
                        }
                    }
                    break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        // 접속자 전체 : 대기실
        public synchronized void messageAll(String msg) {
            for (int i = 0; i < waitVc.size(); i++) {
                Client c = waitVc.get(i); // Client 에 저장
                try {
                    c.messageTo(msg); // 모두에게 전송
                } catch (Exception ex) {
                    waitVc.remove(i--); // 여러 번 연결 시도에도 오류 발생 시, 명단에서 삭제
                    c.closeStreams();
                    ex.printStackTrace();
                }
            }
        }
        // 접속자 개인 : 쪽지 보내기, 정보 보기, 귓속말
        public synchronized void messageTo(String msg) {
            try {
                if (out != null) {
                    out.write((msg + "\n").getBytes());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                try {
                    if (out != null) {
                        out.close();
                        out = null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
/*
### 1. **문제 분석**

- 예외가 발생한 부분: `Server$Client.run`의 `in.readLine()` 호출.
- `in`이 `null`인 이유:
    - `Client` 객체 생성 시 스트림 초기화 실패 (`new BufferedReader(...)`에서 예외 발생).
    - `in.close()`로 스트림이 이미 닫혔지만, 다른 쓰레드에서 `run()`이 계속 실행됨.
    - 스트림이 닫힌 후 `in`을 `null`로 설정하지 않았기 때문에 다른 코드에서 접근 시도.

### 2. **해결 방안**

### **(1) `Client` 생성자에서 스트림 초기화 확인**

`BufferedReader` 초기화 실패 시 로그를 출력하고, 객체 생성이 완전하지 않을 경우 종료.

```java
public Client(Socket s) {
    try {
        this.s = s;
        out = s.getOutputStream();
        in = new BufferedReader(new InputStreamReader(s.getInputStream()));
    } catch (Exception ex) {
        System.err.println("Error initializing Client streams: " + ex.getMessage());
        ex.printStackTrace();
        // 스트림 초기화 실패 시, 객체를 생성하지 않도록 종료
        closeStreams();
    }
}
```

### **(2) `run` 메서드에서 스트림 상태 확인**

`in`이 `null`인 상태에서 접근하지 않도록 체크.

```java
public void run() {
    try {
        while (true) {
            if (in == null) { // 스트림이 초기화되지 않은 경우
                System.err.println("Input stream is null. Exiting thread.");
                break; // 쓰레드 종료
            }
            String msg = in.readLine();
            if (msg == null) { // 클라이언트가 연결을 끊은 경우
                System.out.println("Client disconnected.");
                break;
            }
            System.out.println("Client => " + msg);
            handleProtocol(msg);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {
        closeStreams();
    }
}
```

### **(3) 스트림을 안전하게 닫는 메서드 추가**

`closeStreams()`를 만들어 스트림을 닫고, `null`로 설정해 다음 접근을 방지.

```java
private void closeStreams() {
    try {
        if (in != null) {
            in.close();
            in = null;
        }
        if (out != null) {
            out.close();
            out = null;
        }
        if (s != null) {
            s.close();
            s = null;
        }
    } catch (IOException ex) {
        ex.printStackTrace();
    }
}
```

### **(4) 예외가 발생한 클라이언트 안전 제거**

`waitVc` 리스트에서 문제가 있는 클라이언트를 안전하게 제거하고 관련 리소스를 정리.

```java
public synchronized void messageAll(String msg) {
    for (int i = 0; i < waitVc.size(); i++) {
        Client c = waitVc.get(i);
        try {
            c.messageTo(msg);
        } catch (Exception ex) {
            System.err.println("Error sending message to client: " + ex.getMessage());
            waitVc.remove(i--); // 인덱스 보정
            c.closeStreams();   // 리소스 정리
            ex.printStackTrace();
        }
    }
}
```

```java
// 기존 run() 메서드 안에 있던 switch 문을 따로 캡슐화 한 hanldeProtocol 메서드
private void handleProtocol(String msg) {
    try {
        StringTokenizer st = new StringTokenizer(msg, "|");
        int protocol = Integer.parseInt(st.nextToken());

        switch (protocol) {
            case Function.LOGIN: {
                id = st.nextToken();
                name = st.nextToken();
                sex = st.nextToken();
                pos = "대기실";

                messageAll(Function.LOGIN + "|"
                        + id + "|" + name + "|" + sex + "|" + pos);
                messageAll(Function.WAITCHAT + "|[알림]" + name + "님 입장하셨습니다");
                waitVc.add(this);
                messageTo(Function.MYLOG + "|" + id);
                for (Client c : waitVc) {
                    messageTo(Function.LOGIN + "|" + c.id + "|" + c.name + "|" + c.sex + "|" + c.pos);
                }
            }
            break;
            case Function.WAITCHAT: {
                messageAll(Function.WAITCHAT + "|[" + name + "] " + st.nextToken());
            }
            break;
            case Function.EXIT: {
                messageAll(Function.EXIT + "|" + id);
                messageAll(Function.WAITCHAT + "|[알림]" + name + "님이 퇴장하셨습니다.");
                messageTo(Function.MYEXIT + "|");
                for (int i = 0; i < waitVc.size(); i++) {
                    Client c = waitVc.get(i);
                    if (c.id.equals(id)) {
                        waitVc.remove(i);
                        closeStreams();
                        break;
                    }
                }
            }
            break;
            case Function.INFO: {
                String yid = st.nextToken();
                for (Client c : waitVc) {
                    if (yid.equals(c.id)) {
                        messageTo(Function.INFO + "|" + c.id + "|" + c.name + "|" + c.sex + "|" + c.pos);
                        break;
                    }
                }
            }
            break;
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}
```

- 기존의 run() 메서드 안에 있던 switch 문이 너무 복잡하고 가독성이 떨어져 따로 메서드로 캡슐화하였음

### 3. **최종 요약**

- **`Client` 생성자**에서 스트림 초기화 실패를 처리.
- **`run` 메서드**에서 `in`의 상태를 체크해 `NullPointerException` 방지.
- **`closeStreams` 메서드**로 스트림과 소켓을 안전하게 닫음.
- `waitVc` 리스트에서 문제가 있는 클라이언트를 제거하며 리소스를 정리.
 */
