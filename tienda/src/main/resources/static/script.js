// URL base para las solicitudes
const baseUrl = '/empleados';

// Función para guardar un empleado
async function guardarEmpleado(empleado) {
    try {
        const response = await fetch(`${baseUrl}/crear`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(empleado),
        });

        if (response.ok) {
            alert('Empleado creado con éxito');
            obtenerEmpleadosActivos(); // Refresca la lista de empleados
        } else {
            const errorText = await response.text();
            alert(`Error al crear empleado: ${errorText}`);
        }
    } catch (error) {
        console.error('Error en la solicitud:', error);
    }
}

document.addEventListener('DOMContentLoaded', function () {
    const tutorSi = document.getElementById('tutorSi');
    const tutorNo = document.getElementById('tutorNo');
    const tutorQuien = document.getElementById('tutorQuien');

    // Agregar event listeners para los checkboxes
    tutorSi.addEventListener('change', function () {
        if (this.checked) {
            tutorQuien.disabled = false;
            cargarSupervisores(); // Cargar supervisores cuando se selecciona "Sí"
        }
    });

    tutorNo.addEventListener('change', function () {
        if (this.checked) {
            tutorQuien.disabled = true;
            tutorQuien.innerHTML = ''; // Limpiar la lista cuando se selecciona "No"
        }
    });

    function cargarSupervisores() {
        fetch('/empleados/supervisores') // Cambia la URL según tu configuración
            .then(response => response.json())
            .then(supervisores => {
                tutorQuien.innerHTML = ''; // Limpiar las opciones actuales
                supervisores.forEach(supervisor => {
                    const option = document.createElement('option');
                    option.value = supervisor.id;
                    option.textContent = `${supervisor.nombre} ${supervisor.apellido}`;
                    tutorQuien.appendChild(option);
                });
            })
            .catch(error => console.error('Error al cargar supervisores:', error));
    }
});



// Función para editar un empleado
async function editarEmpleado(id, empleado) {
    try {
        const response = await fetch(`${baseUrl}/editar/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(empleado),
        });

        if (response.ok) {
            alert('Empleado actualizado con éxito');
            obtenerEmpleadosActivos(); // Refresca la lista de empleados
        } else {
            const errorText = await response.text();
            alert(`Error al actualizar empleado: ${errorText}`);
        }
    } catch (error) {
        console.error('Error en la solicitud:', error);
    }
}

// Función para eliminar un empleado
async function eliminarEmpleado(id) {
    try {
        const response = await fetch(`${baseUrl}/borrar/${id}`, {
            method: 'DELETE',
        });

        if (response.ok) {
            alert('Empleado eliminado con éxito');
            obtenerEmpleadosActivos(); // Refresca la lista de empleados
        } else {
            const errorText = await response.text();
            alert(`Error al eliminar empleado: ${errorText}`);
        }
    } catch (error) {
        console.error('Error en la solicitud:', error);
    }
}
//Maneja la confirmación de eliminación
document.getElementById('deleteIcon').addEventListener('click', function() {
    if (confirm('¿Desea eliminar este empleado?')) {
        // Código para eliminar el empleado
        alert('Empleado eliminado'); // Ejemplo de alerta, reemplaza con la lógica de eliminación
    }
});


// Función para obtener todos los empleados activos
async function obtenerEmpleadosActivos() {
    try {
        const response = await fetch(`${baseUrl}/traer`);
        const empleados = await response.json();

        if (response.ok) {
            mostrarEmpleados(empleados);
        } else {
            console.error('Error al obtener empleados activos:', empleados);
        }
    } catch (error) {
        console.error('Error en la solicitud:', error);
    }
}

function mostrarEmpleados(empleados) {
    console.log('Empleados recibidos:', empleados); // Añade esta línea para depuración

    const employeeList = document.getElementById('employeeList');
    employeeList.innerHTML = ''; // Limpia la lista actual

    empleados.forEach(empleado => {
        console.log('Empleado:', empleado); // Añade esta línea para depuración

        const listItem = document.createElement('li');
        listItem.style.display = 'flex';
        listItem.style.justifyContent = 'space-between'; // Espacio entre nombre y apellido
        listItem.style.padding = '6px 0'; // Espacio arriba y abajo de cada ítem
        listItem.style.borderBottom = '1px solid #ccc'; // Línea divisoria entre ítems
        listItem.style.fontSize = '16px'; // Tamaño del texto

        listItem.innerHTML = `${empleado.nombre} <span style="margin-left: 20px;">${empleado.apellido}</span>`;
        employeeList.appendChild(listItem);
    });
}


// Manejo del formulario de empleado
document.getElementById('employeeForm').addEventListener('submit', (event) => {
    event.preventDefault();
    const form = event.target;

    const empleado = {
        nombre: form.nombre.value,
        apellido: form.apellido.value,
        celular: form.celular.value,
        genero: form.genero.value,
        tutor: form.tutorSi.checked ? 'si' : (form.tutorNo.checked ? 'no' : null),
        tutorQuien: form.tutorQuien.value,
        // Agrega más campos si es necesario
    };

    // Obtén el ID si estás editando un empleado
    const id = form.dataset.id; // Asume que tienes un atributo data-id para el ID

    if (id) {
        editarEmpleado(id, empleado);
    } else {
        guardarEmpleado(empleado);
    }
});

// Manejo del botón de eliminar
document.getElementById('deleteBtn').addEventListener('click', () => {
    const id = prompt('Ingrese el ID del empleado a eliminar:');
    if (id) {
        eliminarEmpleado(id);
    }
});

// Carga los empleados al cargar la página
document.addEventListener('DOMContentLoaded', obtenerEmpleadosActivos);

