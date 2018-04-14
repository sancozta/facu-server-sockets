package serversockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client extends Thread {

    private Socket conn;

    public Client(Socket server) {

        //RECEBE O VALOR DO SOCKET ENVIADO NA THREAD
        conn = server;

    }

    public static void main(String args[]) {

        try {

            //CRIA UM SOCKET CONEXAO PASSANDO COMO PARAMETRO O IP E A PORTA DO SERVIDOR 
            Socket connl = new Socket("127.0.0.1", 8080);

            //CRIA UMA THREAD QUE ENVIA A CONEXAO
            Thread thread = new Client(connl);

            //INICIA O THREAD
            thread.start();

        } catch (IOException e) {

            System.out.println("IOException : " + e);

        }

    }

    public void run() {

        try {

            //CRIA UM BUFFEREDREADER PARA RECEBER INFORMACOES DIGITADAS
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

            //ESCREVE UMA MENSAGEM AO USUARIO
            System.out.println("## Digite Algo Para Enviar ##");

            //CRIA UMA PRINTSTREAM PARA PEGAR AS INFORMACOES ENVIADAS DO SERVIDOR
            try (PrintStream saida = new PrintStream(conn.getOutputStream())) {

                while (true) {

                    //DIGITO RECEBE O VALOR DIGITADO PELO USUARIO
                    String string = teclado.readLine();

                    if (string.contains("exit")) {

                        //INFORMANDO AO SERVIDOR O FECHAMENTO DO CANAL
                        saida.println(string);

                        //ENCERRAR CANAL
                        break;

                    } else {

                        //ENVIANDO DADOS
                        saida.println(string);

                    }

                }

            }

            //FINALIZAR CONEXAO
            conn.close();

        } catch (IOException e) {

            System.out.println("IOException : " + e);

        }

    }
}
