
package com.example.tienda.Service;

import com.example.tienda.model.Rol;
import com.example.tienda.repository.IRolRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolService implements IRolService {
    
    @Autowired
    private IRolRepository rolRepository;

    @Override
    public Rol save(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public List<Rol> obtenerRolesActivos() {
        return rolRepository.findAll();
    }

    @Override
    public Optional<Rol> buscarXid(Long id) {
        return rolRepository.findById(id);
    }

    @Override
    public Rol actualizar(Long id, Rol rol) {
        if (rolRepository.existsById(id)) {
            rol.setId(id);
            return rolRepository.save(rol);
        }
        return null; // o lanzar una excepción personalizada
    }

    @Override
    public void eliminar(Long id) {
        rolRepository.deleteById(id);
    }
    
    
}
    
