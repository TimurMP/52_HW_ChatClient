package telran.chat.client.taks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MsgSender implements Runnable {
    Socket socket;
    String userName;
    boolean clientIsActive = true;
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

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
            String fullMessage = (userName + " wrote " + "at " + LocalDateTime.now().format(dateFormatter) + "\n" + message);


            while (!"leave".equalsIgnoreCase(message)) {
                oos.writeObject(fullMessage);

                System.out.println("-------------------------------\n");
                message = bufferedReader.readLine();
                fullMessage = (userName + " wrote " + "at " + LocalDateTime.now().format(dateFormatter) + "\n" + message);

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
