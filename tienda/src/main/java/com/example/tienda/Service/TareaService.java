
package com.example.tienda.Service;

import com.example.tienda.DTO.TareaDTO;
import com.example.tienda.model.Tarea;
import com.example.tienda.repository.ITareaRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TareaService implements ITareaService{
    
     @Autowired
    private ITareaRepository tareaRepository;

    @Override
    public Tarea save(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    @Override
    public List<Tarea> findAll() {
        return tareaRepository.findAll();
    }
    
      @Override
    public List<TareaDTO> obtenerTodasLasTareas() {
        List<Tarea> tareas = tareaRepository.findAll();
        return tareas.stream()
                     .map(tarea -> new TareaDTO(tarea.getId(), tarea.getDescription()))
                     .collect(Collectors.toList());
    }

 
    
}
