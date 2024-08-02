
package com.example.tienda.Service;

import com.example.tienda.DTO.TareaDTO;
import com.example.tienda.model.Tarea;
import java.util.List;
import java.util.Optional;


public interface ITareaService {
    
    public Tarea save(Tarea tarea);

    public List<Tarea> findAll();
    
    List<TareaDTO> obtenerTodasLasTareas();

    
}
