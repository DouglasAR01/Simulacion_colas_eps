
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
    private int cantidadPacientesTotal;
    private int cantidadAltaPrioridad;
    private int cantidadMediaPrioridad;
    private int cantidadBajaPrioridad;
    private int tiempoTotalEspera;
    private int tiempoAltaPrioridad;
    private int tiempoMediaPrioridad;
    private int tiempoBajaPrioridad;

    public Cola() {
        tiempoTotalEspera = 0;
        cantidadPacientesTotal = 0;
        pacientes = new ArrayList();
        medicos = new ArrayList();
    }

    public int getCantidadPacientesTotal() {
        return cantidadPacientesTotal;
    }

    public int getCantidadAltaPrioridad() {
        return cantidadAltaPrioridad;
    }

    public int getCantidadMediaPrioridad() {
        return cantidadMediaPrioridad;
    }

    public int getCantidadBajaPrioridad() {
        return cantidadBajaPrioridad;
    }

    public int getTiempoTotalEspera() {
        return tiempoTotalEspera;
    }

    public int getTiempoAltaPrioridad() {
        return tiempoAltaPrioridad;
    }

    public int getTiempoMediaPrioridad() {
        return tiempoMediaPrioridad;
    }

    public int getTiempoBajaPrioridad() {
        return tiempoBajaPrioridad;
    }
    
    public double[] getPromedioTiemposEspera(){
        double[] res = new double[4];
        res[0] = (float) tiempoAltaPrioridad/cantidadAltaPrioridad;
        res[1] = (float) tiempoMediaPrioridad/cantidadMediaPrioridad;
        res[2] = (float) tiempoBajaPrioridad/cantidadBajaPrioridad;
        res[3] = (float) tiempoTotalEspera/cantidadPacientesTotal;
        return res;
    }
    
    public void agregarMedico(Medico medico){
        medicos.add(medico);
    }
    
    public void asignarTiempo(Persona paciente){
        switch(paciente.PRIORIDAD_PACIENTE){
            case 0:
                tiempoAltaPrioridad += paciente.getTiempoEspera();
                break;
            case 1:
                tiempoMediaPrioridad += paciente.getTiempoEspera();
                break;
            case 2:
                tiempoBajaPrioridad += paciente.getTiempoEspera();
                break;
        }
    }
    
    public void agregarPaciente(Persona paciente){
        pacientes.add(paciente);
        cantidadPacientesTotal++;
        switch(paciente.PRIORIDAD_PACIENTE){
            case 0:
                cantidadAltaPrioridad++;
                break;
            case 1:
                cantidadMediaPrioridad++;
                break;
            case 2:
                cantidadBajaPrioridad++;
                break;
        }
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
                    tiempoTotalEspera += paciente.getTiempoEspera();
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
        cantidadPacientesTotal = 0;
        cantidadAltaPrioridad = 0;
        cantidadMediaPrioridad = 0;
        cantidadBajaPrioridad = 0;
        tiempoTotalEspera = 0;
        tiempoAltaPrioridad = 0;
        tiempoMediaPrioridad = 0;
        tiempoBajaPrioridad = 0;
    }
    
    public void ordenar(){
        Collections.sort(pacientes, new Comparator<Persona>() {
            public int compare(Persona  paciente1, Persona  paciente2) {
                return  paciente1.PRIORIDAD_PACIENTE-paciente2.PRIORIDAD_PACIENTE;
            }
        });
    }
}
