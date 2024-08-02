

package com.example.tienda.Service;

import com.example.tienda.model.Rol;
import java.util.List;
import java.util.Optional;


public interface IRolService {
    
    /**
     * Crea un nuevo rol o actualiza uno existente.
     * 
     * @param rol El rol a guardar.
     * @return El rol guardado.
     */
    public Rol save(Rol rol);

    /**
     * Obtiene todos los roles.
     * 
     * @return Una lista de todos los roles.
     */
    public List<Rol> obtenerRolesActivos();

    /**
     * Obtiene un rol por su ID.
     * 
     * @param id El ID del rol.
     * @return Un Optional que contiene el rol si se encuentra, o vacío si no.
     */
    public Optional<Rol> buscarXid(Long id);

    /**
     * Actualiza un rol existente.
     * 
     * @param id El ID del rol a actualizar.
     * @param rol El rol con los datos actualizados.
     * @return El rol actualizado.
     */
    public Rol actualizar(Long id, Rol rol);

    /**
     * Elimina un rol por su ID.
     * 
     * @param id El ID del rol a eliminar.
     */
    public void eliminar(Long id);
}
