package telran.chat.client.taks;

import java.io.*;
import java.net.Socket;
import java.time.LocalTime;

public class MsgSender implements Runnable{
    Socket socket;
    String userName;
    boolean clientIsActive = true;

    public MsgSender(Socket socket, String userName) {
        this.socket = socket;
        this.userName = userName;
    }

    public boolean isClientIsActive() {
        return clientIsActive;
    }

    @Override
    public void run() {
        try(Socket socket = this.socket) {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String message = (userName + " wrote at " + LocalTime.now() + " :\n" + bufferedReader.readLine());

            while(!"exit".equalsIgnoreCase(message)) {
                oos.writeObject(message);
                System.out.println("-------------------------------\n");
                message = (userName + " wrote at " + LocalTime.now() + " :\n" + bufferedReader.readLine());

            }
//            clientIsActive = false;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
