import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


class ClientUI extends JFrame implements ActionListener {
    private Socket s;
    private JMenuBar choice;
    private JMenu chat,inform,album,file,application,setting;
    private JTextArea show,members,message;
    private JButton shut,send;


    ClientUI(Socket sc){
        this.s = sc;
        setLayout(null);
        choice  = new JMenuBar();
        chat = new JMenu("����");
        inform = new JMenu("����");
        album = new JMenu("���");
        file  = new JMenu("�ļ�");
        application = new JMenu("Ӧ��");
        setting  = new JMenu("����");
        show = new JTextArea();
        members = new JTextArea("Ⱥ��Ա��\n");
        message = new JTextArea();
        shut = new JButton("�ر�");
        send = new JButton("����");
        send.addActionListener(this);
        choice.add(chat);
        choice.add(inform);
        choice.add(album);
        choice.add(file);
        choice.add(application);
        choice.add(setting);
        choice.setBounds(0,0,500,30);
        add(choice);
        show.setLineWrap(true);//���ֱȿؼ��Ŀ�Ȼ���ʱ���Զ�����
        show.setWrapStyleWord(true);//�ڵ��ʱ߽绻��
        JScrollPane sp1 = new JScrollPane(show);
        sp1.setBounds(5,30,390,350);
        //sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(sp1);
        JScrollPane sp2 = new JScrollPane(members);
        sp2.setBounds(400,30,90,350);
        add(sp2);
        message.setBounds(5,390,390,70);
        add(message);
        JPanel p = new JPanel();
        p.add(send);
        p.add(shut);
        p.setBounds(400,400,90,100);
        add(p);
        setTitle("��ӭ����xxx������");
        setSize(500,500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            DataOutputStream dos = new DataOutputStream(this.s.getOutputStream());
            dos.writeUTF(message.getText());
            message.setText("");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
