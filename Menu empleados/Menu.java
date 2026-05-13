
// Este es el módulo principal. Aquí arranca todo con main().
// Su única responsabilidad es mostrar el menú y delegar cada
// acción al módulo correspondiente.



import javax.swing.*;

public class Menu {

    public static void main(String[] args) {

        int menu = 0, borrar = 0, imprimir = 0;

        do {
            try {
                menu = 0;

                // Mostramos el menú principal con todas las opciones
                menu = Integer.parseInt(JOptionPane.showInputDialog(
                        " *** Menú Principal *** \n" +
                        "1. Capturar Información del Empleado\n" +
                        "2. Modificar\n" +
                        "3. Borrar Información\n" +
                        "4. Imprimir Información\n" +
                        "5. Finalizar Programa\n" +
                        "Seleccione una opción [1 ~ 5]"));

                switch (menu) {

                    // ------------------------------------------
                    // CASO 1: Capturar nuevo empleado
                    // ------------------------------------------
                    // Creamos un objeto de RegistroEmpleados y llamamos
                    // a Captura(), que pedirá los datos y los guardará
                    // en los arreglos de Arreglos.java
                    case 1:
                        RegistroEmpleados captura = new RegistroEmpleados();
                        captura.Captura();
                        break;

                    // ------------------------------------------
                    // CASO 2: Modificar un empleado existente
                    // ------------------------------------------
                    // Buscará el empleado por ID y permitirá cambiar
                    // su puesto y status directamente en el arreglo
                    case 2:
                        RegistroEmpleados modificar = new RegistroEmpleados();
                        modificar.modificarEmpleado();
                        break;

                    // ------------------------------------------
                    // CASO 3: Submenú de borrado
                    // ------------------------------------------
                    // Ofrece dos tipos de borrado:
                    //   - Físico: elimina completamente el registro
                    //   - Lógico: solo lo marca como inactivo
                    case 3:
                        borrar borrarEmp = new borrar();
                        do {
                            borrar = Integer.parseInt(JOptionPane.showInputDialog(
                                    " *** Menú Borrar *** \n" +
                                    "1. Borrado Físico\n" +
                                    "2. Borrado Lógico\n" +
                                    "3. Volver\n" +
                                    "Seleccione una opción [1 ~ 3]"));

                            switch (borrar) {
                                case 1:
                                    borrarEmp.borrarFisico();
                                    break;
                                case 2:
                                    borrarEmp.borrarLogico();
                                    break;
                                case 3:
                                    break; // Volvemos al menú principal
                                default:
                                    JOptionPane.showMessageDialog(null,
                                            borrar + " no es una opción válida.");
                            }
                        } while (borrar != 3);
                        break;

                    // ------------------------------------------
                    // CASO 4: Submenú de reportes
                    // ------------------------------------------
                    // Permite ver todos los empleados o solo los activos.
                    // ReporteEmpleados hereda de RegistroEmpleados,
                    // por eso puede usar sus métodos sin repetirlos.
                    case 4:
                        ReporteEmpleados reportes = new ReporteEmpleados();
                        do {
                            imprimir = Integer.parseInt(JOptionPane.showInputDialog(
                                    " *** Menú Reportes *** \n" +
                                    "1. Todos los empleados\n" +
                                    "2. Solo los activos\n" +
                                    "3. Volver\n" +
                                    "Seleccione una opción [1 ~ 3]"));

                            switch (imprimir) {
                                case 1:
                                    reportes.imprimirTodo();
                                    break;
                                case 2:
                                    reportes.imprimirActivos();
                                    break;
                                case 3:
                                    break; // Volvemos al menú principal
                                default:
                                    JOptionPane.showMessageDialog(null,imprimir + " no es una opción válida.");
                            }
                        } while (imprimir != 3);
                        break;

                    // ------------------------------------------
                    // CASO 5: Fin del programa
                    // ------------------------------------------
                    // IMPORTANTE: Al cerrar el programa, los datos en los
                    // arreglos se pierden porque viven en memoria RAM.
                    // Si se necesitara persistencia, aquí se podría
                    // agregar la escritura a archivo como paso final.
                    case 5:
                        JOptionPane.showMessageDialog(null, "¡Hasta luego!");
                        break;

                    default:
                        JOptionPane.showMessageDialog(null,menu + " no corresponde a una opción válida.");
                }

            } catch (NumberFormatException ex) {
                // Capturamos el error si el usuario escribe letras en vez de números
                JOptionPane.showMessageDialog(null,"Por favor use solo números para interactuar con el menú.");
                menu = 0;
            }

        } while (menu != 5);
    }
}