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
    public static final int CANTIDAD_MEDICOS = 3; // Medicos disponibles
    public static final int TIEMPO_MAXIMO_CONSULTA = 30; // Minutos
    public static final int TIEMPO_SIMULACION = 2*60; //Horas*Minutos
    public static final int NUMERO_ITERACIONES = 5;

    public static void main(String[] args) {
        
        Cola cola = new Cola();
        for (int iteracion = 1; iteracion<=NUMERO_ITERACIONES; iteracion++){
            inicializarCola(cola);
            cola.ordenar();
            for (int minuto = 1; minuto<=TIEMPO_SIMULACION; minuto++){
                cola.iterar();
            }
            System.out.println("INFORME ITERACION "+iteracion);
            double[] resultados = cola.getPromedioTiemposEspera();
            System.out.println("Tiempos de espera promedios en colas: \n"+
                                "  ->Alta prioridad: "+resultados[0]+"\n"+
                                "  ->Media prioridad: "+resultados[1]+"\n"+
                                "  ->Baja prioridad: "+resultados[2]+"\n"+
                                "  ->Todas: "+resultados[3]+"\n");
            System.out.println("Número de personas por cola: \n"+
                               "  ->Alta prioridad: "+cola.getCantidadAltaPrioridad()+"\n"+
                               "  ->Media prioridad: "+cola.getCantidadMediaPrioridad()+"\n"+
                               "  ->Baja prioridad: "+cola.getCantidadBajaPrioridad()+"\n"+
                               "  ->Todas: "+cola.getCantidadPacientesTotal()+"\n");
            cola.destruir();
            System.out.println("FIN INFORME \n");
        }
    }
    
    public static void inicializarCola(Cola cola){
        for (int i = 0; i<CANTIDAD_MEDICOS; i++){
            cola.agregarMedico(new Medico());
        }
        for (int i = 0; i<CANTIDAD_MAXIMA_PERSONAS; i++){
            cola.agregarPaciente(new Persona(TIEMPO_MAXIMO_CONSULTA));
        }
    }
}
