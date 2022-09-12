package telran.chat.client.taks;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;

public class MsgSender implements Runnable {
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
        try (Socket socket = this.socket) {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


            String message = (bufferedReader.readLine());
            String fullMessage = (userName + " wrote " + "at " + LocalDateTime.now() + "\n" + message);


            while (!"leave".equalsIgnoreCase(message)) {
                oos.writeObject(fullMessage);

                System.out.println("-------------------------------\n");
                message = bufferedReader.readLine();

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
