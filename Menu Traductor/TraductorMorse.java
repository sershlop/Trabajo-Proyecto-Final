import javax.swing.JOptionPane;

public class TraductorMorse{
    public static void main(String[] args) {
        char[] letras = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ' ' };
        String[] morse = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", "-----", ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", "/" };

        String input = JOptionPane.showInputDialog(null, 
            "Ingresa el código Morse:\n(Separa letras con espacio y palabras con '/')", 
            "Traductor Morse", 
            JOptionPane.QUESTION_MESSAGE);

        if (input != null && !input.isEmpty()) {
            String[] caracteresMorse = input.split(" ");
            StringBuilder resultado = new StringBuilder();

            for (String caracter : caracteresMorse) {
                boolean encontrado = false;
                for (int i = 0; i < morse.length; i++) {
                    if (caracter.equals(morse[i])) {
                        resultado.append(letras[i]);
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    resultado.append("?");
                }
            }
            JOptionPane.showMessageDialog(null, 
                "El texto traducido es:\n" + resultado.toString(), 
                "Resultado", 
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No ingresaste ningún código.");
        }
    }
}