import javax.swing.JOptionPane;

public class Latin_Cerdo {

    public static void main(String[] args) {
        String palabra;

        do {
            // Muestra una ventana para ingresar la palabra
            palabra = JOptionPane.showInputDialog(null, 
                    "Introduce una palabra\n(o escribe 'salir' para terminar):", 
                    "Traductor Latin Cerdo", 
                    JOptionPane.QUESTION_MESSAGE);

            // Valida si el usuario presionó "Cancelar" o cerró la ventana
            if (palabra == null) {
                break; 
            }

            // Elimina espacios en blanco accidentales al inicio o final
            palabra = palabra.trim();

            // Verifica que no esté vacío y que no sea la palabra de salida
            if (!palabra.isEmpty() && !palabra.equalsIgnoreCase("salir")) {
                String resultado = convertirAPigLatin(palabra);
                
                // Muestra el resultado en una ventana emergente
                JOptionPane.showMessageDialog(null,  "Palabra original: " + palabra + "\nEn Latin Cerdo: " + resultado,  "Resultado", JOptionPane.INFORMATION_MESSAGE);
            }

        } while (palabra != null && !palabra.equalsIgnoreCase("salir"));

        // Mensaje de despedida al cerrar el programa
        JOptionPane.showMessageDialog(null, "Programa terminado.", "Adiós", JOptionPane.INFORMATION_MESSAGE);
    }

    public static String convertirAPigLatin(String palabra) {
        palabra = palabra.toLowerCase();
        String vocales = "aeiouáéíóú";

        if (vocales.contains(String.valueOf(palabra.charAt(0)))) {
            return palabra + "way";
        } else {
            int primerVocal = -1;
            for (int i = 0; i < palabra.length(); i++) {
                if (vocales.contains(String.valueOf(palabra.charAt(i)))) {
                    primerVocal = i;
                    break;
                }
            }
            if (primerVocal == -1) return palabra + "ay";

            String consonantes = palabra.substring(0, primerVocal);
            String resto = palabra.substring(primerVocal);
            return resto + consonantes + "ay";
        }
    }
}
