
import javax.swing.JOptionPane;

public class borrar {

    // Reutilizamos la lógica de búsqueda de RegistroEmpleados
    // a través de composición (creamos un objeto de esa clase)
    private RegistroEmpleados registro = new RegistroEmpleados();

    // --------------------------------------------------------
    // borrarFisico()
    // --------------------------------------------------------
    // Elimina permanentemente al empleado del arreglo.
    // La técnica consiste en "correr" todos los elementos que
    // están DESPUÉS del borrado una posición hacia la izquierda,
    // pisando el hueco que dejó el eliminado.
    // Finalmente reducimos el contador total en 1.
    public void borrarFisico() {
        String idBuscar = JOptionPane.showInputDialog("Ingrese el ID del empleado a eliminar:");
        if (idBuscar == null) return;

        // Buscamos la posición del empleado en el arreglo
        int indice = registro.buscarIndice(idBuscar);

        if (indice == -1) {
            JOptionPane.showMessageDialog(null, "ID no encontrado.");
            return;
        }

        // Desplazamos todos los elementos posteriores una posición a la izquierda
        // Ejemplo: si borramos el índice 2, el 3 pasa al 2, el 4 al 3, etc.
        for (int i = indice; i < Arreglos.total - 1; i++) {
            Arreglos.ids[i]     = Arreglos.ids[i + 1];
            Arreglos.puestos[i] = Arreglos.puestos[i + 1];
            Arreglos.activos[i] = Arreglos.activos[i + 1];
        }

        // Limpiamos la última posición que quedó duplicada
        Arreglos.ids[Arreglos.total - 1]     = null;
        Arreglos.puestos[Arreglos.total - 1] = null;
        Arreglos.activos[Arreglos.total - 1] = false;

        // Reducimos el total de empleados registrados
        Arreglos.total--;

        JOptionPane.showMessageDialog(null, "Empleado eliminado físicamente.");
    }

    // --------------------------------------------------------
    // borrarLogico()
    // --------------------------------------------------------
    // No elimina al empleado, solo lo "desactiva".
    // Su ID y puesto permanecen en el arreglo, pero su campo
    // activo pasa a false. Esto es útil para mantener un historial
    // sin borrar información real.
    public void borrarLogico() {
        String idBuscar = JOptionPane.showInputDialog("Ingrese el ID del empleado a desactivar:");
        if (idBuscar == null) return;

        // Buscamos el índice del empleado
        int indice = registro.buscarIndice(idBuscar);

        if (indice == -1) {
            JOptionPane.showMessageDialog(null, "ID no encontrado.");
            return;
        }

        // Verificamos que no esté ya inactivo (para evitar confusión)
        if (!Arreglos.activos[indice]) {
            JOptionPane.showMessageDialog(null, "El empleado ya estaba inactivo.");
            return;
        }

        // Simplemente cambiamos su bandera a false
        Arreglos.activos[indice] = false;

        JOptionPane.showMessageDialog(null, "Empleado desactivado (borrado lógico).");
    }
}