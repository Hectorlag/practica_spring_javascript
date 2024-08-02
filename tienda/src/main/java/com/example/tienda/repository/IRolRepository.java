
package com.example.tienda.repository;

import com.example.tienda.model.Rol;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Long>{
    
    Optional<Rol> findByNombre(String nombre);
    
    List<Rol> findByDeletedFalse();
    
}
