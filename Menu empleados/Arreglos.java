
// Arreglos.java — La "base de datos" del programa

// Esta clase no hace nada por sí sola, solo GUARDA los datos.
// Al declarar los arreglos como "static", todos los demás módulos
// pueden acceder y modificar la misma información sin pasarla
// de un lado a otro. Es como una pizarra compartida.

public class Arreglos {

    // Tamaño máximo de empleados que el programa puede manejar
    public static final int MAX = 100;

    // Aquí guardamos los datos de cada empleado.
    // El índice es el mismo para los tres arreglos:
    // empleado número 5 → ids[5], puestos[5], activos[5]
    public static String[] ids     = new String[MAX];  // ID único de cada empleado
    public static String[] puestos = new String[MAX];  // Puesto o cargo
    public static boolean[] activos = new boolean[MAX]; // true = activo, false = dado de baja

    // Contador de cuántos empleados hay registrados actualmente
    public static int total = 0;
}