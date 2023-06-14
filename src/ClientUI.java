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
        chat = new JMenu("聊天");
        inform = new JMenu("公告");
        album = new JMenu("相册");
        file  = new JMenu("文件");
        application = new JMenu("应用");
        setting  = new JMenu("设置");
        show = new JTextArea();
        members = new JTextArea("群成员：\n");
        message = new JTextArea();
        shut = new JButton("关闭");
        send = new JButton("发送");
        send.addActionListener(this);
        choice.add(chat);
        choice.add(inform);
        choice.add(album);
        choice.add(file);
        choice.add(application);
        choice.add(setting);
        choice.setBounds(0,0,500,30);
        add(choice);
        show.setLineWrap(true);//文字比控件的宽度还长时会自动换行
        show.setWrapStyleWord(true);//在单词边界换行
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
        setTitle("欢迎来到xxx聊天室");
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
