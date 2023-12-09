
package col.com.jager.dao;

import col.com.jager.domain.Persona;
import org.springframework.data.repository.CrudRepository;


public interface PersonaDao extends CrudRepository<Persona, Long> {
    
}
