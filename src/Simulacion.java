/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Douglas
 */
public class Simulacion {
    
    /**
     * PARÁMETROS
     */
    public static final int CANTIDAD_MAXIMA_PERSONAS = 30; // Personas
    public static final int CANTIDAD_MEDICOS = 5; // Medicos disponibles
    public static final int TIEMPO_MAXIMO_CONSULTA = 30; // Minutos
    public static final int TIEMPO_SIMULACION = 2*60; //Horas*Minutos
    public static final int NUMERO_ITERACIONES = 5;
    public static final int NUMERO_PRIORIDADES = 3;

    public static void main(String[] args) {
        
        Cola cola = new Cola(NUMERO_PRIORIDADES);
        for (int iteracion = 1; iteracion<=NUMERO_ITERACIONES; iteracion++){
            inicializarCola(cola);
            cola.ordenar();
            for (int minuto = 1; minuto<=TIEMPO_SIMULACION; minuto++){
                cola.iterar();
            }
            System.out.println("INFORME ITERACION "+iteracion);
            double[] tiempos = cola.getPromedioTiemposEspera();
            int[] atendidos = cola.getCantidadPacientesAtendidos();
            int[] totalPacientes = cola.getCantidadPacientes();
            for (int i = 0; i<NUMERO_PRIORIDADES; i++){
                System.out.println(
                        "Cola #:"+i+"\n"+
                        "  -> Tiempo promedio: "+tiempos[i]+"\n"+
                        "  -> Pacientes totales: "+totalPacientes[i]+"\n"+
                        "  -> Pacientes atentidos: "+atendidos[i]+"\n"
                        );
            }
            System.out.println(
                    "Total pacientes: "+totalPacientes[NUMERO_PRIORIDADES]+"\n"+
                    "Total pacientes atendidos: "+atendidos[NUMERO_PRIORIDADES]+"\n"+
                    "Total tiempos espera promedios: "+tiempos[NUMERO_PRIORIDADES]+"\n"
                    );
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
