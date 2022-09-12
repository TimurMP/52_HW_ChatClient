package telran.chat.client.taks;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

public class MsgReceiver implements Runnable {
    Socket socket;


    public MsgReceiver(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (Socket socket = this.socket) {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            while (true) {
                String response = ois.readObject().toString();
                System.out.println(response);
            }
        } catch (SocketException e) {
            System.out.println("Connection to " + socket.getInetAddress() + " is closed");

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

//    System.out.println("Connection to " + socket.getInetAddress() + " is closed");
    }
}
