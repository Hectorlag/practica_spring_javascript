
package com.example.tienda.Service;

import com.example.tienda.DTO.EmpleadoDTO;
import com.example.tienda.DTO.EmpleadoNombreApellidoDTO;
import com.example.tienda.model.Empleado;
import com.example.tienda.model.Rol;
import java.util.List;
import java.util.Optional;


public interface IEmpleadoService {
    
    // Crear un nuevo empleado
    public Empleado guardar(Empleado empleado);

    // Obtener todos los empleados
    public List<EmpleadoDTO> obtenerEmpleadosActivos();

    // Obtener un empleado por su ID
    public Optional<EmpleadoDTO> buscar(Long id);

    // Actualizar un empleado existente
    public Empleado actualizar(Long id, Empleado empleado);

    // Eliminar un empleado por su ID
    void eliminar(Long id);

    // Buscar empleados por nombre y/o apellido
    List<EmpleadoNombreApellidoDTO> buscarNombreOApellido(String nombre, String apellido);

    // Buscar empleados por rol
    public List<Empleado> buscarXRol(Rol rol);

    // Buscar empleados supervisados por un tutor
    public List<Empleado> buscarXTutor(Empleado tutor);
    
    public List<Empleado> obtenerSupervisores();
    
}
