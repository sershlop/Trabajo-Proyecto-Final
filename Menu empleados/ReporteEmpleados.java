import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.io.*;

// Heredamos de RegistroEmpleados para usar sus atributos y lógica
public class ReporteEmpleados extends RegistroEmpleados {

    public ReporteEmpleados(String nombreArchivo) {
        super(nombreArchivo);
    }

    // Opción 1: Imprimir Todo
    public void imprimirTodo() {
        leerYMostrar(false); // false = no filtrar por activos
    }

    // Opción 2: Solo los activos
    public void imprimirActivos() {
        leerYMostrar(true); // true = filtrar solo activos
    }

    /**
     * Función base para leer el archivo. 
     * @param soloActivos Si es true, filtra las líneas que digan "Activo: true"
     */
    private void leerYMostrar(boolean soloActivos) {
        File archivo = new File("empleados.txt"); // O usar la variable nombreArchivo si es accesible
        StringBuilder reporte = new StringBuilder();
        String titulo = soloActivos ? "--- Empleados Activos ---" : "--- Todos los Empleados ---";
        
        reporte.append(titulo).append("\n\n");

        if (!archivo.exists()) {
            JOptionPane.showMessageDialog(null, "No hay registros aún.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            boolean hayDatos = false;

            while ((linea = br.readLine()) != null) {
                if (soloActivos) {
                    // Solo agregamos si la línea contiene "Activo: true"
                    if (linea.contains("Activo: true")) {
                        reporte.append(linea).append("\n");
                        hayDatos = true;
                    }
                } else {
                    // Agregamos todo
                    reporte.append(linea).append("\n");
                    hayDatos = true;
                }
            }

            if (!hayDatos) {
                reporte.append("No se encontraron registros que coincidan.");
            }

            // Mostrar en un área de texto con scroll por si la lista es larga
            JTextArea textArea = new JTextArea(reporte.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new java.awt.Dimension(400, 300));
            
            JOptionPane.showMessageDialog(null, scrollPane, "Reporte de Empleados", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + e.getMessage());
        }
    }
}
