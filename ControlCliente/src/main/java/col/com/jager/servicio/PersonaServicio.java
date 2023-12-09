package col.com.jager.servicio;

import col.com.jager.domain.Persona;
import java.util.List;

public interface PersonaServicio {

    public List<Persona> listarPersonas();

    public void guardar(Persona persona);

    public void eliminar(Persona persona);

    public Persona encontrarPersona(Persona persona);

}
