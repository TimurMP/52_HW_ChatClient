package telran.chat.client.launcher;

import telran.chat.client.taks.MsgReceiver;
import telran.chat.client.taks.MsgSender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class LauncherAppl {

    public static void main(String[] args) throws InterruptedException {
        String userName;

        String serverIP = "127.0.0.1";
        int port = 9000;
        joinChat(serverIP, port);
        Thread.sleep(100);


    }

    private static void joinChat(String serverIP, int port) throws InterruptedException {
        try (Socket socket = new Socket(serverIP, port)) {
            MsgSender sender = new MsgSender(socket);
            MsgReceiver receiver = new MsgReceiver(socket);
            System.out.println("You can start chatting!");
            Thread senderThread = new Thread(sender);
            Thread receiverThread = new Thread(receiver);

            receiverThread.setDaemon(true);
            senderThread.start();
            receiverThread.start();
            senderThread.join();

        } catch (IOException e) {
            System.out.println("Error 400 --- Server is not reachable, please try again later.");

        }
    }

    public static String userName() throws IOException {
        System.out.print("Enter your name: ");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        return bufferedReader.readLine();
    }


}
