package semi;

import javax.swing.*;
import java.awt.*;

/*
    1. 프레임 생성
        => frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 종료 시 프로세스 끝내기
        => setSize() 통해서 프레임 크기 구현
        => setLayout(null) 통해 각 창을 절대 위치로 구현
    2. 채팅창 출력
        => JTextArea 객체 생성
        => setEditable(false) 통해 편집 불가로 구현
        => setBorder(BorderFactory.createTitledBorder("")) 통해 title 이 있는 테두리를 생성
        => JScrollPane 객체 생성을 통해 생성한 JTextArea 객체를 감쌈
        => 생성한 JScrollPane 객체를 setBounds() 통해 절대 위치 지정
        => frame.add() 로 프레임에 생성
    3. 접속자 목록 출력
        => String 배열은 임시로 유저 생성
        => JList<String> 을 통해 String 값만으로 이루어진 유저 목록 객체를 생성
        => 이하 영역 지정, 프레임에 생성은 2 번과 동일
    4. 채팅 입력창, 전송 버튼 출력
        => JPanel 객체 생성을 통해 하단 패널을 지정, 위 채팅창, 접속자 목록과 분리된 영역
        => 절대 위치를 지정하기 위해 setLayout(null) 을 선언함
        => setBounds() 통해 절대 위치 지정
        => JTextField() 객체 생성을 통해 텍스트 입력창을 하단 패널에 생성
        => setBounds() 통해 절대 위치 지정
        => JButton() 객체 생성을 통해 전송 버튼을 하단 패널에 생성
        => setBounds() 통해 절대 위치 지정
        => 패널 객체의 메서드 add() 통해 각 생성한 JTextField 객체와 JButton 객체를 감쌈
        => frame.add() 로 패널로 감싼 텍스트 입력창과 전송 버튼을 프레임에 생성
 */
public class ChatUI extends JFrame {
    public ChatUI() {
        JFrame frame = new JFrame("채팅 프로그램");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(null);

        // 채팅창 (왼쪽)
        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setBorder(BorderFactory.createTitledBorder("채팅창"));
        JScrollPane chatAreaScrollPane = new JScrollPane(chatArea);
        chatAreaScrollPane.setBounds(10, 10, 375, 470);
        frame.add(chatAreaScrollPane);

        // 접속자 목록 (오른쪽)
        String[] users = {"User1", "User2", "User3"};
        JList<String> userList = new JList<>(users);
        userList.setBorder(BorderFactory.createTitledBorder("접속자 목록"));
        JScrollPane userListScrollPane = new JScrollPane(userList);
        userListScrollPane.setBounds(390, 10, 90, 470);
        frame.add(userListScrollPane);

        // 하단 패널: 채팅 입력창 + 전송 버튼
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(null);
        inputPanel.setBounds(10, 480, 480, 100);


        JTextField chatInput = new JTextField();
        chatInput.setBounds(0, 10, 375, 50);

        JButton sendButton = new JButton("입력");
        sendButton.setBounds(380, 10, 90, 50);

        inputPanel.add(chatInput);
        inputPanel.add(sendButton);

        frame.add(inputPanel);

        // 프레임 표시
        frame.setVisible(true);


    }
}
