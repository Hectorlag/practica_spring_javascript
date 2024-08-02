
package com.example.tienda.controller;

import com.example.tienda.Service.ITareaService;
import com.example.tienda.model.Tarea;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tareas")
public class TareaController {
    
     @Autowired
    private ITareaService serviceTarea;

    // Endpoint para crear una nueva tarea
    @PostMapping("/crear")
    public ResponseEntity<Tarea> crearTarea(@RequestBody Tarea tarea) {
        try {
            Tarea nuevaTarea = serviceTarea.save(tarea);
            return new ResponseEntity<>(nuevaTarea, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint para obtener todas las tareas
    @GetMapping("/traer")
    public ResponseEntity<List<Tarea>> traerTareas() {
        try {
            List<Tarea> tareas = serviceTarea.findAll();
            return new ResponseEntity<>(tareas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
