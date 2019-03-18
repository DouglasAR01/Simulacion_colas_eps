
/**
 *
 * @author Douglas R
 */
import java.util.Random;
public class Persona{
    private int tiempoEspera; //Tiempo que lleva en la cola
    private boolean atendido; //Si está siendo atendido
    public final int PRIORIDAD_PACIENTE;
    public final int TIEMPO_CONSULTA; //Tiempo que tardará en el consultorio
    
    public Persona(int TIEMPO_MAX_CONSULTA, int cantidadPrioridades){
        tiempoEspera = 0;
        atendido = false;
        Random random = new Random();
        TIEMPO_CONSULTA = random.nextInt(TIEMPO_MAX_CONSULTA-1)+1;
        PRIORIDAD_PACIENTE = random.nextInt(cantidadPrioridades+1);
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }

    public boolean isAtendido() {
        return atendido;
    }

    public void setAtendido(boolean atendido) {
        this.atendido = atendido;
    }
    
    public void esperar(){
        tiempoEspera++;
    }
    
}
