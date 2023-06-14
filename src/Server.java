import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args)  {
        ServerSocket sc = null;
        try {
            sc = new ServerSocket(6666);
            Socket s = sc.accept();
            while(true) {
                DataInputStream dis = new DataInputStream(s.getInputStream());
                String str = dis.readUTF();
                System.out.println(str);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
