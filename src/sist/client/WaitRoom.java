package sist.client;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class WaitRoom extends JPanel { // JPanel 을 상속 받아야 JFrame 위로 올라간다
    JTable table1, table2;
    DefaultTableModel model1, model2;
    JTextArea ta;
    JTextField tf;
    JButton b1, b2, b3, b4, b5, b6;
    JScrollBar bar;

    public WaitRoom() {
        setLayout(null); // 절대 위치 지정

        String[] col1 = {"방이름", "상태", "인원"}; // 상태(공개, 비공개방)
        String[][] row1 = new String[0][3];
        model1 = new DefaultTableModel(row1, col1); // 데이터 관리
        table1 = new JTable(model1);
        JScrollPane js1 = new JScrollPane(table1);

        js1.setBounds(10, 15, 450, 300);
        add(js1);

        String[] col2 = {"아이디", "이름", "성별", "위치"}; // 상태(공개, 비공개방)
        String[][] row2 = new String[0][4];
        model2 = new DefaultTableModel(row2, col2) {
            // 익명 클래스
            @Override
            public boolean isCellEditable(int row, int column) {
                // TODO Auto-generated method stub
                return false;
            }

        }; // 데이터 관리
        table2 = new JTable(model2);
        JScrollPane js2 = new JScrollPane(table2);

        js2.setBounds(10, 320, 450, 220);
        add(js2);

        ta = new JTextArea();
        JScrollPane js3 = new JScrollPane(ta);
        ta.setEditable(false); // 채팅 내용 출력창
        bar = js3.getVerticalScrollBar();

        js3.setBounds(465, 15, 300, 260);
        add(js3);

        tf = new JTextField();
        tf.setBounds(465, 280, 300, 30);
        add(tf);

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(3, 2, 5, 5));
        b1 = new JButton("방 만들기");
        b2 = new JButton("방 입장하기");
        b3 = new JButton("정보 보기");
        b4 = new JButton("쪽지 보내기");
        b5 = new JButton("1:1 채팅");
        b6 = new JButton("나가기");
        p.add(b1);
        p.add(b2);
        p.add(b3);
        p.add(b4);
        p.add(b5);
        p.add(b6);
        p.setBounds(465, 320, 300, 220);
        add(p);

    }


}