package Chat;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Cliente {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.startClient();
    }

    public void startClient() {
        try {
            // Conecta ao servidor na porta 7777 (localhost)
            socket = new Socket("127.0.0.1", 7777);
            System.out.println("Conectado ao servidor!");

            // Cria os fluxos de entrada e saída para o servidor
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            // Inicia threads de leitura e escrita
            startReading();
            startWriting();

        } catch (Exception e) {
            System.out.println("Erro na conexão com o servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Thread para ler as mensagens do servidor
    public void startReading() {
        Runnable readTask = () -> {
            try {
                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println("Servidor: " + message);
                }
            } catch (Exception e) {
                System.out.println("Conexão com o servidor foi encerrada.");
            }
        };
        new Thread(readTask).start();
    }

    // Thread para enviar mensagens ao servidor
    public void startWriting() {
        Runnable writeTask = () -> {
            try {
                BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
                while (!socket.isClosed()) {
                    String message = consoleReader.readLine();
                    writer.println(message);

                    if (message.equalsIgnoreCase("EXIT")) {
                        socket.close();
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Erro ao enviar mensagem: " + e.getMessage());
            }
        };
        new Thread(writeTask).start();
    }
}
