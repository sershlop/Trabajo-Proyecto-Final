import javax.swing.JOptionPane;
import java.io.*;

public class RegistroEmpleados {
    private String nombreArchivo;


    public RegistroEmpleados(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }


    public void Captura() {
        int continuar;

        do {
            String idEmpleado = solicitarIdUnico();
            if (idEmpleado == null) break;

            String puesto = JOptionPane.showInputDialog("Ingrese el Puesto:");
            
            int respuestaStatus = JOptionPane.showConfirmDialog(null, 
                "¿El empleado está Activo?", "Status", JOptionPane.YES_NO_OPTION);
            boolean status = (respuestaStatus == JOptionPane.YES_OPTION);

            String[] datos = {idEmpleado, puesto, String.valueOf(status)};
            
            guardarEnArchivo(datos);

            continuar = JOptionPane.showConfirmDialog(null, 
                "¿Quieres seguir Capturando?", "Confirmación", JOptionPane.YES_NO_OPTION);

        } while (continuar == JOptionPane.YES_OPTION);
    }

    private String solicitarIdUnico() {
        while (true) {
            String id = JOptionPane.showInputDialog("Ingrese el ID del Empleado:");
            if (id == null) return null;

            if (existeId(id)) {
                JOptionPane.showMessageDialog(null, "El ID '" + id + "' ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                return id;
            }
        }
    }

    public boolean existeId(String idBuscado) {
        File archivo = new File(this.nombreArchivo);
        if (!archivo.exists()) return false;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains("ID: " + idBuscado + " |")) return true;
            }
        } catch (IOException e) {
            System.err.println("Error de lectura: " + e.getMessage());
        }
        return false;
    }
    
    public void modificarEmpleado() {
    String idBuscar = JOptionPane.showInputDialog("Ingrese el ID del empleado a modificar:");
    if (idBuscar == null) return;

    File archivo = new File(this.nombreArchivo);
    File temp = new File("temp.txt");

    boolean encontrado = false;

    try (BufferedReader br = new BufferedReader(new FileReader(archivo));
         PrintWriter pw = new PrintWriter(new FileWriter(temp))) {

        String linea;

        while ((linea = br.readLine()) != null) {

            if (linea.contains("ID: " + idBuscar + " |")) {
                encontrado = true;

                // Pedir nuevos datos
                String nuevoPuesto = JOptionPane.showInputDialog("Nuevo puesto:");

                int resp = JOptionPane.showConfirmDialog(null,
                        "¿El empleado está activo?", "Status",
                        JOptionPane.YES_NO_OPTION);

                boolean nuevoStatus = (resp == JOptionPane.YES_OPTION);

                // Escribir línea modificada
                pw.println("ID: " + idBuscar + " | Puesto: " + nuevoPuesto + " | Activo: " + nuevoStatus);

            } else {
                pw.println(linea); // copiar igual
            }
        }

    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        return;
    }

    // Reemplazar archivo original
    if (archivo.delete()) {
        temp.renameTo(archivo);
    }

    if (encontrado) {
        JOptionPane.showMessageDialog(null, "Empleado modificado correctamente.");
    } else {
        JOptionPane.showMessageDialog(null, "ID no encontrado.");
        temp.delete();
    }
}

    private void guardarEnArchivo(String[] datos) {
        try (FileWriter fw = new FileWriter(this.nombreArchivo, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            
            out.println("ID: " + datos[0] + " | Puesto: " + datos[1] + " | Activo: " + datos[2]);
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar: " + e.getMessage());
        }
    }
}