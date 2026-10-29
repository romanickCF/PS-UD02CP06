import java.util.ArrayList;

void main() throws InterruptedException {

        int limite =10;

        Fibonacci fibonacci = new Fibonacci(limite);

        ArrayList<Hebra> hilos = fibonacci.crearHilos();


        for (Hebra h : hilos) {
            h.start();
        }

     for (Hebra h : hilos) {
        h.join();
    }

    }









