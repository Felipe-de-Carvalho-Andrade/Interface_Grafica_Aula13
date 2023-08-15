import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private Button btnDivisao;

    @FXML
    private Button btnElevado;

    @FXML
    private Button btnFatorial;

    @FXML
    private Button btnIgual;

    @FXML
    private Button btnInverteSinal;

    @FXML
    private Button btnMultiplicacao;

    @FXML
    private Button btnNumero0;

    @FXML
    private Button btnNumero1;

    @FXML
    private Button btnNumero2;

    @FXML
    private Button btnNumero3;

    @FXML
    private Button btnNumero4;

    @FXML
    private Button btnNumero5;

    @FXML
    private Button btnNumero6;

    @FXML
    private Button btnNumero7;

    @FXML
    private Button btnNumero8;

    @FXML
    private Button btnNumero9;

    @FXML
    private Button btnPorcentagem;

    @FXML
    private Button btnRaiz;

    @FXML
    private Button btnSubtracao;

    @FXML
    private Button btnVirgula;

    @FXML
    private Label visor;


    private double num1 = 0;
    private double num2 = 0;
    private String operacao = "";
    private boolean novaOperacao = true;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        Button button = (Button) event.getSource();
        String buttonText = button.getText();
    
        if (novaOperacao) {
            visor.setText("");
            novaOperacao = false;
        }
        
        switch (buttonText) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                visor.setText(visor.getText() + buttonText);
                break;
    
            case ",":
                if (!visor.getText().contains(".")) {
                    visor.setText(visor.getText() + ".");
                }
                break;
                
            case "+/-":
                if (!visor.getText().isEmpty()) {
                    double valor = Double.parseDouble(visor.getText());
                    valor *= -1;
                    visor.setText(String.valueOf(valor));
                }
                break;

            case "√":
            case "+":
            case "-":
            case "*":
            case "/":
            case "%":
            case "!":
            case "^":
                if (!operacao.isEmpty() && !visor.getText().isEmpty()) {
                    if (num1 == 0) {
                        num1 = Double.parseDouble(visor.getText());
                    } else {
                        num2 = Double.parseDouble(visor.getText());
                        num1 = realizarOperacao(num1, num2, operacao);
                        visor.setText(String.valueOf(num1));
                    }
                } else {
                    num1 = Double.parseDouble(visor.getText());
                }
                operacao = buttonText;
                novaOperacao = true;
                break;
    
            case "=":
                if (!operacao.isEmpty() && !visor.getText().isEmpty()) {
                    num2 = Double.parseDouble(visor.getText());
                    num1 = realizarOperacao(num1, num2, operacao);
                    visor.setText(String.valueOf(num1));
                    operacao = "";
                    novaOperacao = true;
                }
                break;
    
            case "AC":
                visor.setText("");
                num1 = 0;
                num2 = 0;
                operacao = "";
                novaOperacao = true;
                break;
        }
    }

    private double realizarOperacao(double num1, double num2, String operacao) {
        switch (operacao) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    return Double.POSITIVE_INFINITY;
                }
            case "√":
                return (num2 >= 0) ? Math.sqrt(num2) : Double.NaN;
            case "%":
                return num1 * (num2 / 100);
            case "!":
                return (num2 >= 0) ? calcularFatorial((int) num2) : Double.NaN;
            case "^":
                return Math.pow(num1, num2);
            default:
                return 0;
        }
    }

    private int calcularFatorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * calcularFatorial(n - 1);
    }
}
