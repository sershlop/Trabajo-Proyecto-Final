import javax.swing.JOptionPane;
import java.io.*;

public class borrar {
    private String nombreArchivo;

    public borrar(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    // 🔴 BORRADO FÍSICO
    public void borrarFisico() {
        String idBuscar = JOptionPane.showInputDialog("Ingrese el ID del empleado a eliminar:");
        if (idBuscar == null) return;

        File archivo = new File(this.nombreArchivo);
        File temp = new File("temp.txt");

        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo));
             PrintWriter pw = new PrintWriter(new FileWriter(temp))) {

            String linea;

            while ((linea = br.readLine()) != null) {
                if (linea.contains("ID: " + idBuscar + " |")) {
                    encontrado = true; // no se copia
                } else {
                    pw.println(linea);
                }
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            return;
        }

        reemplazarArchivo(archivo, temp);

        JOptionPane.showMessageDialog(null,
                encontrado ? "Empleado eliminado físicamente." : "ID no encontrado.");
    }

    // 🟡 BORRADO LÓGICO
    public void borrarLogico() {
        String idBuscar = JOptionPane.showInputDialog("Ingrese el ID del empleado a desactivar:");
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

                    String[] partes = linea.split("\\|");
                    String puesto = partes[1].replace("Puesto:", "").trim();

                    pw.println("ID: " + idBuscar + " | Puesto: " + puesto + " | Activo: false");

                } else {
                    pw.println(linea);
                }
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            return;
        }

        reemplazarArchivo(archivo, temp);

        JOptionPane.showMessageDialog(null,
                encontrado ? "Empleado desactivado (borrado lógico)." : "ID no encontrado.");
    }

    // 🔧 Método reutilizable (buena práctica)
    private void reemplazarArchivo(File original, File temp) {
        if (original.delete()) {
            temp.renameTo(original);
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo reemplazar el archivo.");
        }
    }
}