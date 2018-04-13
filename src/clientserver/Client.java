package clientserver;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    
    public Client(String ThreadName){
    
        try {

            String string;
            Scanner teclado = new Scanner(System.in);
            Socket cliente = new Socket("127.0.0.1", 8080);

            System.out.println("Sou o cliente "+ThreadName+"!");
            System.out.println("O cliente se conectou ao servidor !");

            try (PrintStream saida = new PrintStream(cliente.getOutputStream())) {

                while (teclado.hasNextLine()) {
                    string = teclado.nextLine();
                    if (string.contains("exit")) {
                        break;
                    }
                    saida.println(ThreadName);
                }

                cliente.close();
                System.out.println("Cliente encerrou a conexão !");

            }

        } catch (UnknownHostException e) {

        } catch (IOException e) {

        }
        
    }
    
    public static void main(String[] args) {
        
        try {

            String string;
            Scanner teclado = new Scanner(System.in);
            Socket cliente = new Socket("127.0.0.1", 8080);

            System.out.println("Sou o cliente !");
            System.out.println("O cliente se conectou ao servidor !");

            try (PrintStream saida = new PrintStream(cliente.getOutputStream())) {

                while (teclado.hasNextLine()) {
                    string = teclado.nextLine();
                    if (string.contains("exit")) {
                        break;
                    }
                    saida.println(string);
                }

                cliente.close();
                System.out.println("Cliente encerrou a conexão !");

            }

        } catch (UnknownHostException e) {

        } catch (IOException e) {

        }
        
    }

}
