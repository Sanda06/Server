import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(1080);
        while (true) {

            Socket socket = serverSocket.accept();
            System.out.println("Client Connected");
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream());

            double sum = 0;

            String request = input.readLine();
            for (String value : request.split("\\+")) {
                try {
                    double oper = Double.parseDouble(value);
                    sum += oper;
                } catch (NumberFormatException ignore) {
                    output.println("Valoarea unuia din operanzi nu este numerica \r\n");
                    output.flush();
                }
            }
            output.println("Suma numerelor este :" + sum + "\r\n");
            output.flush();
        }

    }
}
