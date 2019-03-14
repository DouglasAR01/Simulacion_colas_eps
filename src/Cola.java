
/**
 *
 * @author Douglas
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class Cola {
    private ArrayList<Persona> pacientes;
    private ArrayList<Medico> medicos;
    private int CANTIDAD_PRIORIDADES = 4;
    private int[] cantidadPacientes;
    private int[] cantidadPacientesAtendidos;
    private int[] tiemposEspera;

    public Cola() {
        pacientes = new ArrayList();
        medicos = new ArrayList();
        cantidadPacientes = new int[CANTIDAD_PRIORIDADES];
        cantidadPacientesAtendidos = new int[CANTIDAD_PRIORIDADES];
        tiemposEspera = new int[CANTIDAD_PRIORIDADES];
    }
    public Cola(int cantidadPrioridades) {
        pacientes = new ArrayList();
        medicos = new ArrayList();
        CANTIDAD_PRIORIDADES = cantidadPrioridades+1;
        cantidadPacientes = new int[CANTIDAD_PRIORIDADES];
        cantidadPacientesAtendidos = new int[CANTIDAD_PRIORIDADES];
        tiemposEspera = new int[CANTIDAD_PRIORIDADES];
    }
    
    public int[] getCantidadPacientes(){
        return cantidadPacientes;
    }
    
    public int[] getCantidadPacientesAtendidos(){
        return cantidadPacientesAtendidos;
    }
    
    public int[] getTiemposEspera(){
        return tiemposEspera;
    }
    
    public double[] getPromedioTiemposEspera(){
        double[] res = new double[4];
        for (int i = 0; i<CANTIDAD_PRIORIDADES; i++){
            res[i] = (float) tiemposEspera[i]/cantidadPacientesAtendidos[i];
        }
        return res;
    }
    
    public void agregarMedico(Medico medico){
        medicos.add(medico);
    }
    
    public void asignarTiempo(Persona paciente){
        tiemposEspera[CANTIDAD_PRIORIDADES-1] += paciente.getTiempoEspera();
        cantidadPacientesAtendidos[CANTIDAD_PRIORIDADES-1]++;
        cantidadPacientesAtendidos[paciente.PRIORIDAD_PACIENTE]++;
        tiemposEspera[paciente.PRIORIDAD_PACIENTE] += paciente.getTiempoEspera();
    }
    
    public void agregarPaciente(Persona paciente){
        pacientes.add(paciente);
        cantidadPacientes[CANTIDAD_PRIORIDADES-1]++;
        cantidadPacientes[paciente.PRIORIDAD_PACIENTE]++;
    }
    
    public void removerPaciente(Persona paciente){
        pacientes.remove(paciente);
    }
    
    public void iterar(){
        ArrayList<Medico> medicosDisponibles = new ArrayList();
        for (Medico medico : medicos){
            if(medico.isAtendiendo()){
                if(medico.isConsultaConcluida()){
                    removerPaciente(medico.terminarConsulta());
                    medicosDisponibles.add(medico);
                }else{
                    medico.atender();
                }
            }else{
                medicosDisponibles.add(medico);
            }
        } // Ya se tienen los medicos con el consultorio vacío
        for (Persona paciente : pacientes){
            if(!paciente.isAtendido()){
                if(!medicosDisponibles.isEmpty()){
                    medicosDisponibles.get(0).recibirPaciente(paciente);
                    asignarTiempo(paciente);
                    medicosDisponibles.remove(0);
                    paciente.setAtendido(true);
                }else{
                    paciente.esperar();
                }
            }            
        }
    }

    public void destruir() {
        pacientes.clear();
        medicos.clear();
        cantidadPacientes = new int[CANTIDAD_PRIORIDADES];
        cantidadPacientesAtendidos = new int[CANTIDAD_PRIORIDADES];
        tiemposEspera = new int[CANTIDAD_PRIORIDADES];
    }
    
    public void ordenar(){
        Collections.sort(pacientes, new Comparator<Persona>() {
            public int compare(Persona  paciente1, Persona  paciente2) {
                return  paciente1.PRIORIDAD_PACIENTE-paciente2.PRIORIDAD_PACIENTE;
            }
        });
    }
}
