package com.example.tienda.Service;

import com.example.tienda.DTO.EmpleadoDTO;
import com.example.tienda.DTO.EmpleadoNombreApellidoDTO;
import com.example.tienda.DTO.RolDTO;
import com.example.tienda.model.Empleado;
import com.example.tienda.model.Rol;
import com.example.tienda.repository.IEmpleadoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService implements IEmpleadoService {

    @Autowired
    private IEmpleadoRepository empleadoRepository;

    @Override
    public Empleado guardar(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

  @Override
    public List<EmpleadoDTO> obtenerEmpleadosActivos() {
        List<Empleado> empleados = empleadoRepository.findByDeletedFalse();
        return empleados.stream()
                        .map(this::convertirAEmpleadoDTO)
                        .collect(Collectors.toList());
    }

    @Override
    public Optional<EmpleadoDTO> buscar(Long id) {
        Optional<Empleado> empleadoOptional = empleadoRepository.findByIdAndDeletedFalse(id);
        return empleadoOptional.map(this::convertirAEmpleadoDTO);
    }

    private EmpleadoDTO convertirAEmpleadoDTO(Empleado empleado) {
        RolDTO rolDTO = empleado.getRol() != null ? new RolDTO(empleado.getRol().getId()) : null;
        return new EmpleadoDTO(
                empleado.getId(),
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getCelular(),
                empleado.getGenero(),
                empleado.isDeleted(),
                rolDTO
        );
    }

    @Override
    public Empleado actualizar(Long id, Empleado empleado) {
        // Buscar el empleado existente por su ID
        Optional<Empleado> empleadoOpt = empleadoRepository.findByIdAndDeletedFalse(id);
        if (empleadoOpt.isPresent()) {
            Empleado empleadoExistente = empleadoOpt.get();

            // Actualizar campos directamente
            empleadoExistente.setNombre(empleado.getNombre());
            empleadoExistente.setApellido(empleado.getApellido());
            empleadoExistente.setCelular(empleado.getCelular());
            empleadoExistente.setGenero(empleado.getGenero());
            empleadoExistente.setRol(empleado.getRol());
            empleadoExistente.setTutor(empleado.getTutor());

            // Guardar y devolver el empleado actualizado
            return empleadoRepository.save(empleadoExistente);
        }
        // Si el empleado no se encuentra, se podría lanzar una excepción o devolver null
        return null; // o lanzar una excepción personalizada
    }

    @Override
    public void eliminar(Long id) {
        Optional<Empleado> empleadoOpt = empleadoRepository.findById(id);
        if (empleadoOpt.isPresent()) {
            Empleado empleado = empleadoOpt.get();
            empleado.setDeleted(true);
            empleadoRepository.save(empleado);
        } else {
            throw new RuntimeException("Empleado no encontrado");
        }
    }

    @Override
public List<EmpleadoNombreApellidoDTO> buscarNombreOApellido(String nombre, String apellido) {
    List<Empleado> empleados;
    
    if (nombre != null && !nombre.isEmpty() && apellido != null && !apellido.isEmpty()) {
        empleados = empleadoRepository.findByNombreOrApellido(nombre, apellido);
    } else if (nombre != null && !nombre.isEmpty()) {
        empleados = empleadoRepository.findByNombre(nombre);
    } else if (apellido != null && !apellido.isEmpty()) {
        empleados = empleadoRepository.findByApellido(apellido);
    } else {
        empleados = new ArrayList<>();
    }

    // Depuración: imprimir los datos obtenidos
    System.out.println("Número de empleados encontrados: " + empleados.size());
    empleados.forEach(empleado -> {
        System.out.println("Empleado ID: " + empleado.getId());
        System.out.println("Nombre: " + empleado.getNombre());
        System.out.println("Apellido: " + empleado.getApellido());
        System.out.println("Celular: " + empleado.getCelular());
        System.out.println("Género: " + empleado.getGenero());
        System.out.println("Rol ID: " + (empleado.getRol() != null ? empleado.getRol().getId() : "N/A"));
        System.out.println("Tutor ID: " + (empleado.getTutor() != null ? empleado.getTutor().getId() : "N/A"));
        System.out.println("Deleted: " + empleado.isDeleted());
        System.out.println("-------------------------------------------------");
    });

    return empleados.stream()
            .map(empleado -> new EmpleadoNombreApellidoDTO(empleado.getNombre(), empleado.getApellido()))
            .collect(Collectors.toList());
}

    @Override
    public List<Empleado> buscarXRol(Rol rol) {
        return empleadoRepository.findByRolAndDeletedFalse(rol);
    }

    @Override
    public List<Empleado> buscarXTutor(Empleado tutor) {
        return empleadoRepository.findByTutorAndDeletedFalse(tutor);
    }

    @Override
    public List<Empleado> obtenerSupervisores() {
        return empleadoRepository.findByTutorIsNullAndDeletedFalse();
    }

}
