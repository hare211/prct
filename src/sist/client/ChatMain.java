package sist.client;
import java.util.*;
import java.net.*;
import java.awt.CardLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import javax.swing.*;

import sist.commons.*;
import java.awt.*;
// 마우스 / 버튼 / 엔터 => 서버로 전송
// 서버에서 전송하는 값 읽기 => 자동화 처리 => 쓰레드
// c
public class ChatMain extends JFrame implements ActionListener, Runnable, MouseListener {
    Login login = new Login();
    WaitRoom wr = new WaitRoom();
    CardLayout card = new CardLayout(); // 화면을 숨긴 뒤 보여준다
    // <jsp:include>
    // 네트워크 관련 클래스
    Socket s;
    OutputStream out;
    BufferedReader in;
    // 본인 여부 확인
    String myId;

    int selRow = -1;

    public ChatMain() {
        setLayout(card);
        add("LOG", login);
        add("WR", wr);

        setSize(800, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        login.b1.addActionListener(this);
        login.b2.addActionListener(this);

        wr.tf.addActionListener(this);
        wr.b6.addActionListener(this); // 나가기

        wr.table2.addMouseListener(this);
    }
    // 서버 => 수신 => 화면 출력
    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            while (true) {
                String msg = in.readLine();
                StringTokenizer st = new StringTokenizer(msg, "|");
                int protocol = Integer.parseInt(st.nextToken());

                switch (protocol) {
                    case Function.LOGIN:{ // 먼저 로그인 된 사람 처리
                        String[] data = {
                                st.nextToken(),
                                st.nextToken(),
                                st.nextToken(),
                                st.nextToken()
                        };
                        wr.model2.addRow(data);
                    }
                    break;
                    case Function.MYLOG:{ // 로그인 하고 있는 사람
                        card.show(getContentPane(), "WR");
                        myId = st.nextToken();
                        setTitle(myId);
                    }
                    break;
                    case Function.WAITCHAT:{
                        wr.ta.append(st.nextToken() + "\n");
                        wr.bar.setValue(wr.bar.getMaximum());
                    }
                    break;
                    case Function.EXIT: { // 남아 있는 사람 처리
                        String yid = st.nextToken();
                        // exit.jsp?id=admin
                        // String yid = request.getParamter("id")
                        for (int i = 0; i < wr.model2.getRowCount(); i++) {
                            String id = wr.model2.getValueAt(i, 0).toString();
                            if (yid.equals(id)) {
                                wr.model2.removeRow(i);
                                break;
                            }
                        }
                    }
                    break;
                    case Function.MYEXIT: { // 실제 나간 사람
                        dispose();
                        System.exit(0);
                    }
                    break;
                    case Function.INFO: {
                        String data = "아이디 : " + st.nextToken() + "\n" + "이름 : " + st.nextToken() + "\n 성별 : " + st.nextToken() + "\n 방위치 : "
                                + st.nextToken();
                        JOptionPane.showMessageDialog(this, data);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    // 서버로 요청 값 전송
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == login.b2) {
            dispose(); // 윈도우 메모리 해제
            System.exit(0); // 프로그램 종료
            // React-Query => TanStack-Query
        } else if (e.getSource() == login.b1) {
            // 1. 입력값 읽기
            String id = login.tf1.getText();
            if (id.trim().length() < 1) { // 텍스트가 공백일 때
                JOptionPane.showMessageDialog(this, "아이디를 입력하세요.");
                login.tf1.requestFocus();
                return;
            }
            String name = login.tf2.getText();
            if (name.trim().length() < 1) { // 텍스트가 공백일 때
                JOptionPane.showMessageDialog(this, "이름을 입력하세요.");
                login.tf2.requestFocus();
                return;
            }

            String sex = "남자";
            if (login.rb1.isSelected()) { // rb1 -> 남자 버튼
                sex = "남자";
            } else {
                sex = "여자";
            }
            // 서버와 연결
            connection(id, name, sex);
        } else if (e.getSource() == wr.tf) { // 엔터 쳤을 때
            String msg = wr.tf.getText();
            if (msg.trim().length() < 1) {
                wr.tf.requestFocus(); // 커서 고정
                return;
            }

            // 서버로 전송
            try {
                out.write((Function.WAITCHAT + "|" + msg + "\n").getBytes()); // 데이터 보내기 / 보낼 때 write, 항상 byte 로
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            wr.tf.setText("");
        } else if (e.getSource() == wr.b6) {
            // 나가기
            try {
                out.write((Function.EXIT + "|\n").getBytes()); // 나가기
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    public void connection(String id, String name, String sex) {
        try {
            s = new Socket("localhost", 3050);
            // 서버 정보
            out = s.getOutputStream();
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            // login 요청
            out.write((Function.LOGIN + "|" + id + "|" + name + "|" + sex + "\n").getBytes());
            // ObjectOutputStream
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // 서버와 통신 요청
        new Thread(this).start();
    }

    public static void main(String[] args) {
        new ChatMain();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == wr.table2) {
            if (e.getClickCount() == 2) { // 더블 클릭이면
                int row = wr.table2.getSelectedRow();
                String id = wr.model2.getValueAt(row, 0).toString();
                try {
                    out.write((Function.INFO + "|" + id + "\n").getBytes());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}