package telran.chat.client.taks;

import telran.chat.model.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.format.DateTimeFormatter;

public class MsgSender implements Runnable {
    Socket socket;
    String userName;
    boolean clientIsActive = true;
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public MsgSender(Socket socket) {
        this.socket = socket;
        try {
            this.userName = userName();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isClientIsActive() {
        return clientIsActive;
    }

    @Override
    public void run() {
        try (Socket socket = this.socket) {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            Message messageObj = new Message(userName, null);
            messageObj.setMessage(bufferedReader.readLine());
//            messageObj.setMessage(bufferedReader.readLine());
//            String message = (bufferedReader.readLine());
//            String fullMessage = (userName + " wrote " + "at " + LocalDateTime.now().format(dateFormatter) + "\n" + message);


            while (!"leave".equalsIgnoreCase(messageObj.getMessage())) {
                messageObj.setTime(true);
                oos.writeObject(messageObj);

                System.out.println("-------------------------------\n");
                messageObj.setMessage(bufferedReader.readLine());


//                messageObj.setTime(true);
//                message = bufferedReader.readLine();
//                fullMessage = (userName + " wrote " + "at " + LocalDateTime.now().format(dateFormatter) + "\n" + message);

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static String userName() throws IOException {
        System.out.print("Enter your name: ");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        return bufferedReader.readLine();
    }


}
