import java.util.ArrayList;

public class Fibonacci {

    Integer limite;
    Integer inicial;

    ArrayList<Integer> secuencia = new ArrayList<>();
    ArrayList<Hebra> hilos = new ArrayList<>();


    public Fibonacci(int limite, int inicial) {
    this.limite = limite;
    this.inicial = inicial;
    }
    public ArrayList<Hebra> crearHilos(){
        for(int i = 1; i <= limite; i++) {
            hilos.add(new Hebra(i,this));
        }
        return hilos;
    }

    public void crearFibonacci(ArrayList<Hebra> secuencia){
        secuencia.set(0, inicial);
        secuencia.set(1, inicial);
    }

    synchronized int  sumar(int posicion){

        if(posicion <= 1 ){
            return inicial;
        }else{
            int nActual = secuencia.get(posicion-1);
            int nAnterior = secuencia.get(posicion-2);
            return nAnterior + nActual;
        }
    }

    synchronized void añadirValor(int posicion, int suma){

        secuencia.set(posicion, suma);

        }





}
