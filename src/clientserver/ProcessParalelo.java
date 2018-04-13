package clientserver;

public class ProcessParalelo {

    public static void main(String[] args) {
        
        Thread Thread1 = new Thread((Runnable) new Client("Thread1"));
        Thread1.start();
        Thread Thread2 = new Thread((Runnable) new Client("Thread2"));
        Thread2.start();
        Thread Thread3 = new Thread((Runnable) new Client("Thread3"));
        Thread3.start();

    }
    
}
