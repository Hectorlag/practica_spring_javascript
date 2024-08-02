package com.example.tienda.repository;

import com.example.tienda.model.Empleado;
import com.example.tienda.model.Rol;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmpleadoRepository extends JpaRepository<Empleado, Long> {
    // Busca empleados por su nombre y apellido
    @Query("SELECT e FROM Empleado e WHERE (e.nombre LIKE %:nombre% OR e.apellido LIKE %:apellido%) AND e.deleted = false ORDER BY e.nombre ASC, e.apellido ASC")
    List<Empleado> findByNombreOrApellido(@Param("nombre") String nombre, @Param("apellido") String apellido);
    
    // Busca empleados solo por su nombre
    @Query("SELECT e FROM Empleado e WHERE e.nombre LIKE %:nombre% AND e.deleted = false ORDER BY e.nombre ASC, e.apellido ASC")
    List<Empleado> findByNombre(@Param("nombre") String nombre);
     
    // Busca empleados solo por su apellido 
    @Query("SELECT e FROM Empleado e WHERE e.apellido LIKE %:apellido% AND e.deleted = false ORDER BY e.nombre ASC, e.apellido ASC")
    List<Empleado> findByApellido(@Param("apellido") String apellido);


// Busca empleados por su rol y que no estén marcados como eliminados.
    List<Empleado> findByRolAndDeletedFalse(Rol rol);

// Busca todos los empleados que no estén marcados como eliminados.
    List<Empleado> findByDeletedFalse();

// Busca un empleado por su ID, pero solo si no está marcado como eliminado.
    Optional<Empleado> findByIdAndDeletedFalse(Long id);

// Busca empleados que tengan un tutor específico y que no estén marcados como eliminados.
    List<Empleado> findByTutorAndDeletedFalse(Empleado tutor);

// Busca empleados que no tengan un tutor (es decir, supervisores) y que no estén marcados como eliminados.
    List<Empleado> findByTutorIsNullAndDeletedFalse();

}
