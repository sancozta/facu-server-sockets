package serversockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

    private Socket conn;

    public Server(Socket server) {

        //RECEBE O VALOR DO SOCKET ENVIADO NA THREAD
        conn = server;

    }

    public static void main(String args[]) {

        ServerSocket server = null;

        try {

            //CRIA UM SOCKETSERVER COM PORTA 8080
            server = new ServerSocket(8080);

            System.out.println("## Server Start ##");
            System.out.println("NOTIFICATION - Iniciado na Porta " + server.getLocalPort());

            while (true) {

                //CRIA UM OBJETO SOCKET, MAS PASSANDO INFORMAÇÕES CARACTERÍSTICAS DE UM SERVIDOR,
                //NO QUAL SOMENTE ABRE UMA PORTA E AGUARDA A CONEXÃO DE UM CLIENTE.
                Socket connl = server.accept();

                //CRIA UMA THREAD QUE ENVIA A CONEXAO
                Thread thread = new Server(connl);

                //INICIA A THREAD
                thread.start();

            }

        } catch (IOException e) {

            System.out.println("IOException : " + e);

        }

    }

    //
    public void run() {

        try {

            //IP DO CLIENTE CONECTADO
            System.out.println("SYSTEM - Cliente " + conn.getInetAddress().getHostAddress() + " Realizou Login!");

            //CRIA UMA BUFFER QUE IRA ARMAZENAR AS INFORMACOES ENVIADAS PELO CLIENTE
            try (BufferedReader inFromClient = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {

                while (true) {

                    //CASO O SERVIDOR RECEBA ALGUM DADO DO CLIENTE
                    String string = inFromClient.readLine();

                    if (string.contains("exit")) {

                        //CASO O CLIENTE FECHE A CONEXAO
                        System.out.println("SYSTEM - Cliente " + conn.getInetAddress().getHostAddress() + " Realizou Logout !");
                        break;

                    } else {

                        //CASO O CLIENTE ENVIE ALGUM DADO
                        System.out.println("NOTIFICATION - Cliente " + conn.getInetAddress().getHostAddress() + " : " + string);

                    }

                }

            }

            //ENCERRANDO CONEXAO COM O CLIENTE
            conn.close();

        } catch (IOException e) {

            System.out.println("IOException : " + e);

        }

    }

}
