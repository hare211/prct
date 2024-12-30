package sist.client;
import javax.swing.*;
public class Login extends JPanel { // ChatMain 에서 그룹화
    JLabel la1, la2, la3;
    JTextField tf1, tf2;
    JRadioButton rb1, rb2;
    JButton b1, b2;

    public Login() {
        la1 = new JLabel("아이디");
        la2 = new JLabel("이름");
        la3 = new JLabel("성별");

        tf1 = new JTextField();
        tf2 = new JTextField();

        rb1 = new JRadioButton("남자");
        rb2 = new JRadioButton("여자");
        // JRadioButton -> 반드시 그룹 처리 => HTML 에서도 그룹 처리
        ButtonGroup bg = new ButtonGroup();
        bg.add(rb1);
        bg.add(rb2);

        // default 선택
        rb1.setSelected(true); // 남자 선택

        b1 = new JButton("로그인");
        b2 = new JButton("취소");

        // 배치
        setLayout(null); // 절대 위치 지정
        la1.setBounds(10, 15, 60, 30);
        tf1.setBounds(75, 15, 150, 30);
        add(la1);
        add(tf1);

        la2.setBounds(10, 50, 60, 30);
        tf2.setBounds(75, 50, 150, 30);
        add(la2);
        add(tf2);

        la3.setBounds(10, 85, 60, 30);
        rb1.setBounds(75, 85, 70, 30);
        rb2.setBounds(150, 85, 70, 30);
        add(la3);
        add(rb1);
        add(rb2);

        JPanel p = new JPanel();
        p.add(b1);
        p.add(b2);
        p.setBounds(10, 120, 215, 35);
        add(p);

    }
}