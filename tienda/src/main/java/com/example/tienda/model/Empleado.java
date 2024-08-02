
package com.example.tienda.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class Empleado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    
    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;
    private String celular;
    
    @NotBlank(message = "El genero es obligatorio")
    private String genero;
    
    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;
    
    @ManyToOne
    private Rol rol;
    
    @ManyToOne
    private Empleado tutor;

    @OneToMany(mappedBy = "tutor")
    private List<Empleado> supervisados;

    @ManyToMany
    @JoinTable(
      name = "empleado_tareas", 
      joinColumns = @JoinColumn(name = "empleado_id"), 
      inverseJoinColumns = @JoinColumn(name = "tarea_id"))
    private Set<Tarea> tareas = new HashSet<>();
    
}
