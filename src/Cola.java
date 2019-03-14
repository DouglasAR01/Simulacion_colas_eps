
/**
 *
 * @author Douglas
 */
import java.util.ArrayList;
public class Cola {
    private ArrayList<Persona> pacientes;
    private ArrayList<Medico> medicos;
    private int cantidadPacientesTotal;
    private int tiempoTotalEspera;

    public Cola() {
        tiempoTotalEspera = 0;
        cantidadPacientesTotal = 0;
        pacientes = new ArrayList();
        medicos = new ArrayList();
    }

    public int getCantidadPacientesTotal() {
        return cantidadPacientesTotal;
    }

    public int getTiempoTotalEspera() {
        return tiempoTotalEspera;
    }
    
    public double getPromedioTiempoEspera(){
        return (float) tiempoTotalEspera/cantidadPacientesTotal;
    }
    
    public void agregarMedico(Medico medico){
        medicos.add(medico);
    }
    
    public void agregarPaciente(Persona paciente){
        pacientes.add(paciente);
        cantidadPacientesTotal++;
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
        tiempoTotalEspera = 0;
    }
}
