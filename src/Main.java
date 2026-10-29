import java.util.ArrayList;

void main(String[] args) throws InterruptedException {

       final Integer limite =10;
        Fibonacci fibonacci = new Fibonacci(limite);

        ArrayList<Hebra> hilos = fibonacci.crearHilos();


        for (Hebra h : hilos) {
            h.start();
        }

     for (Hebra h : hilos) {
        h.join();
    }

    }









