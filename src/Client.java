import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String request;
        String response;

        String operand1, operand2;

        do {
            System.out.println("Tasteaza 1 nr:");
            operand1 = scanner.nextLine();

            System.out.println("Tasteaza 2 nr:");
            operand2 = scanner.nextLine();


            request = operand1 + "+" + operand2 + "\r\n";
            try (Socket socket = new Socket("localhost", 1080)) {
                BufferedReader echoes = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter stringToEcho = new PrintWriter(socket.getOutputStream(), true);
                stringToEcho.println(request);
                stringToEcho.flush();

                if (!request.equals(null)) {
                    response = echoes.readLine();
                    System.out.println(response);
                }
            } catch (IOException e) {
                System.out.println("Client error" + e.getMessage());
            }
        } while (!request.equals(null));
    }
}

