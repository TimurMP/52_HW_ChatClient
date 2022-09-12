package telran.chat.client.taks;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class MsgReceiver implements Runnable {
    Socket socket;


    public MsgReceiver(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try(Socket socket = this.socket) {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            while(true) {
                String response = ois.readObject().toString();
                System.out.println(response);
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Connection to " + socket.getInetAddress() + " is closed");
        }

    }
}
