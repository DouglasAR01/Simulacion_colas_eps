
/**
 *
 * @author Douglas
 */
public class Medico {
    private boolean atendiendo;
    private Persona paciente;
    private int tiempoAtendido;
    
    public Medico(){
        atendiendo = false;
        paciente = null;
        tiempoAtendido = 0;
    }

    public boolean isAtendiendo() {
        return atendiendo;
    }

    public void setAtendiendo(boolean atendiendo) {
        this.atendiendo = atendiendo;
    }
    
    public void recibirPaciente(Persona paciente){
        if(!atendiendo){
            this.paciente = paciente;
            atendiendo = true;
        }
    }
    
    public Persona terminarConsulta(){
        atendiendo = false;
        tiempoAtendido = 0;
        return paciente;
    }
    
    public void atender(){
        if(atendiendo){
            if(!isConsultaConcluida()){
                tiempoAtendido++;
            }
        }
    }
    
    public boolean isConsultaConcluida(){
        if(tiempoAtendido <= paciente.TIEMPO_CONSULTA){
            return false;
        }
        return true;
    }
}
