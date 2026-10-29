public class Hebra extends Thread {

    Integer id ;
    String nombre;

    Fibonacci fibonacci;

    public Hebra(Integer id, Fibonacci fibonacci) {
        this.id = id;
        nombre = "hilo " + id;
        this.fibonacci = fibonacci;
    }

    @Override
    public void run() {

        while (fibonacci.tamanio() != id){

        }
        int valor = fibonacci.sumar(id);
        System.out.println("Término " + (id +1) + ": " + valor);

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        fibonacci.aniadirValor(id,valor);
    }

    @Override
    public String toString() {
        return "Hebra{" + "id=" + id + ", nombre=" + nombre + '}';
    }
}
