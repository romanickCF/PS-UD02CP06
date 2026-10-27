public class Hebra extends Thread {

    Integer id ;
    String nombre;

    Fibonacci fibonacci;

    public Hebra(Integer id, Fibonacci fibonacci) {
        this.id = id;
        nombre = "hilo " + id;
    }

    @Override
    public void run() {
        while (fibonacci.secuencia.size() != id){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        fibonacci.añadirValor(id,fibonacci.sumar(this.id));
    }

    @Override
    public String toString() {
        return "Hebra{" + "id=" + id + ", nombre=" + nombre + '}';
    }
}
