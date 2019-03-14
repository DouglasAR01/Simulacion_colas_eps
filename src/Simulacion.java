/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Douglas
 */
import java.util.Random;
public class Simulacion {
    
    /**
     * PARÁMETROS
     */
    public static final int CANTIDAD_MAXIMA_PERSONAS = 30; // Personas
    public static final int CANTIDAD_MEDICOS_PRIORIDAD_0 = 3; // Medicos
    public static final int CANTIDAD_MEDICOS_PRIORIDAD_1 = 2;
    public static final int CANTIDAD_MEDICOS_PRIORIDAD_2 = 2;
    public static final int TIEMPO_MAXIMO_CONSULTA = 30; // Minutos
    public static final int TIEMPO_SIMULACION = 2*60; //Horas*Minutos
    public static final int NUMERO_ITERACIONES = 3;

    public static void main(String[] args) {
        
        Cola prioridad_0 = new Cola();
        Cola prioridad_1 = new Cola();
        Cola prioridad_2 = new Cola();
        for (int iteracion = 1; iteracion<=NUMERO_ITERACIONES; iteracion++){
            inicializarCola(prioridad_0, CANTIDAD_MEDICOS_PRIORIDAD_0, 0);
            inicializarCola(prioridad_1, CANTIDAD_MEDICOS_PRIORIDAD_1, 1);
            inicializarCola(prioridad_2, CANTIDAD_MEDICOS_PRIORIDAD_2, 2);
            for (int minuto = 1; minuto<=TIEMPO_SIMULACION; minuto++){
                prioridad_0.iterar();
                prioridad_1.iterar();
                prioridad_2.iterar();
            }
            System.out.println("INFORME ITERACION "+iteracion);
            System.out.println("Tiempo de espera promedio en la cola de prioridad 0: "+
                                prioridad_0.getPromedioTiempoEspera());
            System.out.println("Número de personas tratadas en la cola: "+prioridad_0.getCantidadPacientesTotal()+"\n");
            prioridad_0.destruir();
            System.out.println("Tiempo de espera promedio en la cola de prioridad 1: "+
                                prioridad_1.getPromedioTiempoEspera());
            System.out.println("Número de personas tratadas en la cola: "+prioridad_1.getCantidadPacientesTotal()+"\n");
            prioridad_1.destruir();
            System.out.println("Tiempo de espera promedio en la cola de prioridad 2: "+
                                prioridad_2.getPromedioTiempoEspera());
            System.out.println("Número de personas tratadas en la cola: "+prioridad_2.getCantidadPacientesTotal());
            prioridad_2.destruir();
            System.out.println("FIN INFORME \n");
        }
    }
    
    public static void inicializarCola(Cola cola, int cantidadMedicos, int prioridad){
        for (int i = 0; i<cantidadMedicos; i++){
            cola.agregarMedico(new Medico());
        }
        Random random = new Random();        
        int personas = random.nextInt(CANTIDAD_MAXIMA_PERSONAS-1)+1;
        for (int i = 0; i<personas; i++){
            cola.agregarPaciente(new Persona(TIEMPO_MAXIMO_CONSULTA, prioridad));
        }
    }
}
