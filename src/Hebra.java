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
            Thread.onSpinWait();
        }
        int valor = fibonacci.sumar(id);
        System.out.println("Término " + (id +1) + ": " + valor);
        fibonacci.aniadirValor(id,valor);
    }

    @Override
    public String toString() {
        return "Hebra{" + "id=" + id + ", nombre=" + nombre + '}';
    }
}
