
// Este módulo se hereda de RegistroEmpleados (extends).
// Eso significa que tiene acceso a todos sus métodos, como
// buscarIndice() o existeId(), sin tener que volver a escribirlos.
//
// Su función es recorrer los arreglos y mostrar la información
// en pantalla, con o sin filtro de empleados activos.

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class ReporteEmpleados extends RegistroEmpleados {

    // El constructor hereda directamente de RegistroEmpleados
    public ReporteEmpleados() {
        super();
    }

    // --------------------------------------------------------
    // imprimirTodo()
    // --------------------------------------------------------
    // Muestra TODOS los empleados registrados, sin filtrar.
    public void imprimirTodo() {
        mostrarReporte(false); // false = no filtrar, mostrar todos
    }

    // --------------------------------------------------------
    // imprimirActivos()
    // --------------------------------------------------------
    // Muestra ÚNICAMENTE los empleados cuyo campo activo = true.
    public void imprimirActivos() {
        mostrarReporte(true); // true = solo los activos
    }

    // --------------------------------------------------------
    // mostrarReporte()
    // --------------------------------------------------------
    // Función base que construye el reporte.
    // Recorre los arreglos y va armando un texto con los datos.
    // Si soloActivos es true, omite a los que tienen activo = false.
    //
    // Usa JTextArea con scroll para que la lista se pueda desplazar
    // si hay muchos empleados registrados.
    private void mostrarReporte(boolean soloActivos) {

        // Verificamos si hay empleados registrados antes de intentar mostrar
        if (Arreglos.total == 0) {
            JOptionPane.showMessageDialog(null, "No hay empleados registrados aún.");
            return;
        }

        // StringBuilder es más eficiente que String para concatenar en un bucle
        StringBuilder reporte = new StringBuilder();
        String titulo = soloActivos ? "--- Empleados Activos ---" : "--- Todos los Empleados ---";
        reporte.append(titulo).append("\n\n");

        boolean hayDatos = false;

        // Recorremos todos los empleados registrados
        for (int i = 0; i < Arreglos.total; i++) {

            // Si el filtro está activado, saltamos a los inactivos
            if (soloActivos && !Arreglos.activos[i]) {
                continue; // Pasamos al siguiente sin agregarlo al reporte
            }

            // Construimos la línea de información de este empleado
            reporte.append("ID: ").append(Arreglos.ids[i])
                   .append(" | Puesto: ").append(Arreglos.puestos[i])
                   .append(" | Activo: ").append(Arreglos.activos[i])
                   .append("\n");

            hayDatos = true;
        }

        // Si después del recorrido no se agregó ningún empleado al reporte
        if (!hayDatos) {
            reporte.append("No se encontraron registros que coincidan.");
        }

        // Mostramos el reporte en una ventana con scroll
        JTextArea textArea = new JTextArea(reporte.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(400, 300));

        JOptionPane.showMessageDialog(null, scrollPane,
                "Reporte de Empleados", JOptionPane.INFORMATION_MESSAGE);
    }
}