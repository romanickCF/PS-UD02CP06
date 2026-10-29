import java.util.ArrayList;

public class Fibonacci {

    Integer limite;


    ArrayList<Integer> secuencia = new ArrayList<>();
    ArrayList<Hebra> hilos = new ArrayList<>();


    public Fibonacci(int limite) {
    this.limite = limite;

    }

    public ArrayList<Hebra> crearHilos(){
        for(int i = 0; i <= limite; i++) {
            hilos.add(new Hebra(i,this));
        }
        return hilos;
    }


    synchronized int sumar(int posicion) {
        if (posicion <= 1) {
            return posicion;
        }
        return secuencia.get(posicion - 1) + secuencia.get(posicion - 2);
    }

    synchronized void aniadirValor(int posicion, int suma){
        secuencia.add(posicion, suma);
        }

    synchronized int tamanio() { return secuencia.size(); }

    synchronized int obtener(int posicion) {
        return secuencia.get(posicion);
    }



}
