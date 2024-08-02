package com.example.tienda.controller;

import com.example.tienda.DTO.EmpleadoDTO;
import com.example.tienda.DTO.EmpleadoNombreApellidoDTO;
import com.example.tienda.Service.IEmpleadoService;
import com.example.tienda.model.Empleado;
import com.example.tienda.model.Rol;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private IEmpleadoService empleadoService;

    @PostMapping("/crear")
    public ResponseEntity<String> crearEmpleado(@Valid @RequestBody Empleado empleado, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Maneja errores de validación
            return ResponseEntity.badRequest().body("Error de validación: " + bindingResult.getAllErrors());
        }
        // Guarda en la base de datos
        empleadoService.guardar(empleado);
        return ResponseEntity.ok("Empleado creado con éxito");
    }

    @GetMapping("/traer")
    public ResponseEntity<List<EmpleadoDTO>> obtenerEmpleadosActivos() {
        List<EmpleadoDTO> empleados = empleadoService.obtenerEmpleadosActivos();
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }

    @GetMapping("/traer/{id}")
    public ResponseEntity<?> buscarEmpleado(@PathVariable Long id) {
        Optional<EmpleadoDTO> empleadoOpt = empleadoService.buscar(id);
        if (empleadoOpt.isPresent()) {
            return new ResponseEntity<>(empleadoOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Empleado no encontrado con ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

     @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarEmpleado(@PathVariable Long id, @Valid @RequestBody Empleado empleado, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Error de validación: " + bindingResult.getAllErrors());
        }

        // Actualiza en la base de datos
        empleadoService.actualizar(id, empleado);

        return ResponseEntity.ok("Empleado actualizado con éxito");
    }
    

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> eliminarEmpleado(@PathVariable Long id) {
        // Verifica si el empleado con el ID proporcionado existe en la base de datos
        Optional<EmpleadoDTO> empleadoOpt = empleadoService.buscar(id);
        if (empleadoOpt.isPresent()) {
            // Elimina el empleado lógicamente (marcar como eliminado)
            empleadoService.eliminar(id);
            return new ResponseEntity<>("Empleado eliminado", HttpStatus.OK);
        } else {
            // Si el empleado no existe, devuelve un código de estado 404 (Not Found)
            return new ResponseEntity<>("Empleado no encontrado", HttpStatus.NOT_FOUND);
        }
    }

  @GetMapping("/filtrar")
    public ResponseEntity<List<EmpleadoNombreApellidoDTO>> buscarEmpleado(@RequestParam(required = false) String nombre, @RequestParam(required = false) String apellido) {
        List<EmpleadoNombreApellidoDTO> empleados = empleadoService.buscarNombreOApellido(nombre, apellido);
        if (empleados.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(empleados);
        }
        return ResponseEntity.ok(empleados);
    }

    @GetMapping("/rol")
    public ResponseEntity<List<Empleado>> buscarPorRol(@RequestParam Rol rol) {
        List<Empleado> empleados = empleadoService.buscarXRol(rol);
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }

    @GetMapping("/tutor/{tutorId}")
    public ResponseEntity<List<Empleado>> buscarPorTutor(@PathVariable Long tutorId) {
        Empleado tutor = new Empleado();
        tutor.setId(tutorId);
        List<Empleado> empleados = empleadoService.buscarXTutor(tutor);
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }

    @GetMapping("/supervisores")
    public ResponseEntity<List<Empleado>> obtenerSupervisores() {
        List<Empleado> supervisores = empleadoService.obtenerSupervisores();
        return new ResponseEntity<>(supervisores, HttpStatus.OK);
    }

}
