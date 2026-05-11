import javax.swing.JOptionPane;

public class TextoAMorse {
    public static void main(String[] args) {
        char[] letras = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ' ' };
        String[] morse = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", "-----", ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", "/" };

        String input = JOptionPane.showInputDialog(null, 
            "Ingresa el texto que deseas convertir a Morse:", 
            "Convertidor Texto a Morse", 
            JOptionPane.QUESTION_MESSAGE);

        if (input != null && !input.isEmpty()) {
            input = input.toUpperCase();
            StringBuilder resultado = new StringBuilder();
            for (int i = 0; i < input.length(); i++) {
                char letraActual = input.charAt(i);
                boolean encontrado = false;

                for (int j = 0; j < letras.length; j++) {
                    if (letraActual == letras[j]) {
                        resultado.append(morse[j]).append(" ");
                        encontrado = true;
                        break;
                    }
                }

                if (!encontrado) {
                    resultado.append("? ");
                }
            }

            JOptionPane.showMessageDialog(null, 
                "El texto en Morse es:\n" + resultado.toString(), 
                "Resultado de Traducción", 
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No ingresaste ningún texto.");
        }
    }
}