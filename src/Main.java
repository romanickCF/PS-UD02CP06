//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    Fibonacci fibonacci = new Fibonacci(10,0);

    ArrayList<Hebra> hilos = fibonacci.crearHilos();

    for (Hebra h : hilos) {
        h.start();
    }



    }



