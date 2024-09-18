package Chat;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Server {
    private ServerSocket serverSocket;

    public static void main(String[] args) {
        Server server = new Server();
        server.startServer();
    }

    public void startServer() {
        try {
            // Inicia o servidor na porta 7777
            serverSocket = new ServerSocket(7777);
            System.out.println("Servidor está pronto para receber conexões na porta 7777✅");

            // Loop infinito para aceitar múltiplos clientes
            while (true) {
                // Aceita uma nova conexão de cliente
                Socket socket = serverSocket.accept();
                System.out.println("Novo cliente conectado!");

                // Inicia uma nova thread para lidar com o cliente
                ClientHandler clientHandler = new ClientHandler(socket);
                new Thread(clientHandler).start();
            }
        } catch (Exception e) {
            System.out.println("Erro ao iniciar o servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Classe interna para lidar com cada cliente em uma thread separada
    private class ClientHandler implements Runnable {
        private Socket socket;
        private BufferedReader reader;
        private PrintWriter writer;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                // Cria os fluxos de entrada e saída para o cliente
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(socket.getOutputStream(), true);

                // Leitura de mensagens do cliente
                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println("Cliente: " + message);

                    // Se o cliente enviar "EXIT", encerra a conexão
                    if (message.equalsIgnoreCase("EXIT")) {
                        System.out.println("Cliente desconectado.");
                        break;
                    }

                    // Envia a mesma mensagem de volta ao cliente
                    writer.println("Servidor: " + message);
                }

                // Fecha os fluxos e o socket quando o cliente se desconectar
                reader.close();
                writer.close();
                socket.close();

            } catch (Exception e) {
                System.out.println("Erro ao lidar com o cliente: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
