
// RegistroEmpleados.java — Captura y modificación de empleados

// Este módulo se encarga de DOS cosas principales:
//   1. Capturar nuevos empleados y guardarlos en los arreglos
//   2. Modificar los datos de un empleado que ya existe
// arreglos de la clase Arreglos mientras el programa esté abierto.

import javax.swing.JOptionPane;

public class RegistroEmpleados {

    // --------------------------------------------------------
    // Captura()
    // --------------------------------------------------------
    // Le pregunta al usuario los datos de un nuevo empleado
    // y los guarda en los arreglos compartidos (Arreglos.java).
    // Repite el proceso hasta que el usuario diga "No".
    public void Captura() {
        int continuar;

        do {
            // Primero pedimos el ID y verificamos que no esté repetido
            String idEmpleado = solicitarIdUnico();
            if (idEmpleado == null) break; // Si el usuario canceló, salimos

            // Pedimos el puesto del empleado
            String puesto = JOptionPane.showInputDialog("Ingrese el Puesto:");

            // Preguntamos si el empleado está activo con un botón Sí/No
            int respuestaStatus = JOptionPane.showConfirmDialog(null,
                    "¿El empleado está Activo?", "Status", JOptionPane.YES_NO_OPTION);
            boolean status = (respuestaStatus == JOptionPane.YES_OPTION);

            // Guardamos los datos en la posición actual del arreglo
            // y aumentamos el contador de empleados
            guardarEnArreglo(idEmpleado, puesto, status);

            // Preguntamos si quieren capturar otro empleado
            continuar = JOptionPane.showConfirmDialog(null,
                    "¿Quieres seguir Capturando?", "Confirmación", JOptionPane.YES_NO_OPTION);

        } while (continuar == JOptionPane.YES_OPTION);
    }

    // --------------------------------------------------------
    // solicitarIdUnico()
    // --------------------------------------------------------
    // Pide un ID al usuario y se queda preguntando en bucle
    // hasta que el ID introducido NO exista en los arreglos.
    // Así evitamos duplicados sin necesidad de leer ningún archivo.
    private String solicitarIdUnico() {
        while (true) {
            String id = JOptionPane.showInputDialog("Ingrese el ID del Empleado:");
            if (id == null) return null; // El usuario presionó Cancelar

            if (existeId(id)) {
                // Si el ID ya está registrado, avisamos y volvemos a preguntar
                JOptionPane.showMessageDialog(null,
                        "El ID '" + id + "' ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                return id; // ID válido y único, lo devolvemos
            }
        }
    }

    // --------------------------------------------------------
    // existeId()
    // --------------------------------------------------------
    // Recorre el arreglo de IDs buscando si ya hay uno igual.
    // Antes esto requería abrir y leer un archivo línea por línea.
    // Ahora es mucho más directo: simplemente comparamos strings
    // en memoria con .equals().
    public boolean existeId(String idBuscado) {
        for (int i = 0; i < Arreglos.total; i++) {
            if (Arreglos.ids[i].equals(idBuscado)) {
                return true; // Lo encontramos, ya existe
            }
        }
        return false; // No está en ninguna posición del arreglo
    }

    // --------------------------------------------------------
    // modificarEmpleado()
    // --------------------------------------------------------
    // Busca un empleado por su ID y permite cambiar su puesto
    // y su status (activo/inactivo).
    // Antes había que crear un archivo temporal y reemplazarlo.
    // Con arreglos, simplemente sobreescribimos la posición encontrada.
    public void modificarEmpleado() {
        String idBuscar = JOptionPane.showInputDialog("Ingrese el ID del empleado a modificar:");
        if (idBuscar == null) return;

        // Buscamos en el arreglo el índice donde está ese ID
        int indice = buscarIndice(idBuscar);

        if (indice == -1) {
            // Si devuelve -1, el empleado no existe
            JOptionPane.showMessageDialog(null, "ID no encontrado.");
            return;
        }

        // Pedimos los nuevos datos
        String nuevoPuesto = JOptionPane.showInputDialog("Nuevo puesto:");
        int resp = JOptionPane.showConfirmDialog(null,
                "¿El empleado está activo?", "Status", JOptionPane.YES_NO_OPTION);
        boolean nuevoStatus = (resp == JOptionPane.YES_OPTION);

        // Sobreescribimos directamente en la misma posición del arreglo
        Arreglos.puestos[indice] = nuevoPuesto;
        Arreglos.activos[indice] = nuevoStatus;

        JOptionPane.showMessageDialog(null, "Empleado modificado correctamente.");
    }

    // --------------------------------------------------------
    // guardarEnArreglo()
    // --------------------------------------------------------
    // Guarda un nuevo empleado al final del arreglo.
    // Verifica primero que no hayamos llegado al límite máximo.
    private void guardarEnArreglo(String id, String puesto, boolean status) {
        if (Arreglos.total >= Arreglos.MAX) {
            JOptionPane.showMessageDialog(null, "No hay espacio para más empleados.");
            return;
        }

        // Guardamos cada dato en su arreglo correspondiente, en la misma posición
        Arreglos.ids[Arreglos.total]     = id;
        Arreglos.puestos[Arreglos.total] = puesto;
        Arreglos.activos[Arreglos.total] = status;

        // Avanzamos el contador para la siguiente captura
        Arreglos.total++;
    }

    // --------------------------------------------------------
    // buscarIndice()
    // --------------------------------------------------------
    // Recorre el arreglo y devuelve la posición (índice) donde
    // está el ID buscado. Si no lo encuentra, devuelve -1.
    // Este valor de -1 es una convención clásica en programación
    // para indicar "no encontrado".
    public int buscarIndice(String idBuscado) {
        for (int i = 0; i < Arreglos.total; i++) {
            if (Arreglos.ids[i].equals(idBuscado)) {
                return i; // Devolvemos la posición exacta
            }
        }
        return -1; // No existe
    }
}