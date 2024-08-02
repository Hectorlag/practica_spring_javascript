
package com.example.tienda.repository;

import com.example.tienda.model.Tarea;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITareaRepository extends JpaRepository<Tarea, Long> {
    
     List<Tarea> findByDescription(String description);
    
}
