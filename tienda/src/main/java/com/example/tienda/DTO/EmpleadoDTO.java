
package com.example.tienda.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class EmpleadoDTO {
    
    private Long id;
    private String nombre;
    private String apellido;
    private String celular;
    private String genero;
    private boolean deleted;
    private RolDTO rol;

   
}
