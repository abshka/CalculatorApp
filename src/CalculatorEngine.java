import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class CalculatorEngine implements ActionListener {
    Calculator parent; // ссылка на окно калькулятора
    char selectedAction = ' '; // +, -, /, *, √, sin, cos
    double currentResult = 0;
    boolean pointEntered = false; // флаг для отслеживания ввода точки

    CalculatorEngine(Calculator parent) {
        this.parent = parent;
    }

    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        String dispFieldText = parent.displayField.getText();
        double displayValue = 0;

        if (!"".equals(dispFieldText)) {
            displayValue = Double.parseDouble(dispFieldText);
        }

        Object src = e.getSource();

        if (src == parent.buttonPlus) {
            selectedAction = '+';
            currentResult = displayValue;
            parent.displayField.setText("");
            pointEntered = false; // сброс флага при выборе новой операции
        } else if (src == parent.buttonMinus) {
            selectedAction = '-';
            currentResult = displayValue;
            parent.displayField.setText("");
            pointEntered = false;
        } else if (src == parent.buttonDivide) {
            if (displayValue != 0) {
                selectedAction = '/';
                currentResult = displayValue;
                parent.displayField.setText("");
                pointEntered = false;
            } else {
                JOptionPane.showMessageDialog(null, "На нуль делить нельзя", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        } else if (src == parent.buttonMultiply) {
            selectedAction = '*';
            currentResult = displayValue;
            parent.displayField.setText("");
            pointEntered = false;
        } else if (src == parent.buttonSquareRoot) {
            if (displayValue >= 0) {
                currentResult = Math.sqrt(displayValue);
                parent.displayField.setText(String.valueOf(currentResult));
            } else {
                JOptionPane.showMessageDialog(null, "Нельзя извлекать корень из отрицательного числа", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
            selectedAction = ' ';
            pointEntered = false; // сброс флага при завершении вычислений
        } else if (src == parent.buttonEqual) {
            switch (selectedAction) {
                case '+':
                    currentResult += displayValue;
                    parent.displayField.setText(String.valueOf(currentResult));
                    break;
                case '-':
                    currentResult -= displayValue;
                    parent.displayField.setText(String.valueOf(currentResult));
                    break;
                case '/':
                    if (displayValue != 0) {
                        currentResult /= displayValue;
                        parent.displayField.setText(String.valueOf(currentResult));
                    } else {
                        JOptionPane.showMessageDialog(null, "На нуль делить нельзя", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case '*':
                    currentResult *= displayValue;
                    parent.displayField.setText(String.valueOf(currentResult));
                    break;
                case 's':
                    currentResult = Math.sin(Math.toRadians(displayValue));
                    parent.displayField.setText(String.valueOf(currentResult));
                    break;
                case 'c':
                    currentResult = Math.cos(Math.toRadians(displayValue));
                    parent.displayField.setText(String.valueOf(currentResult));
                    break;
                default:
                    break;
            }
            selectedAction = ' ';
            pointEntered = false; // сброс флага при завершении вычислений
        } else if (src == parent.buttonPoint) {
            if (!pointEntered) {
                parent.displayField.setText(dispFieldText + ".");
                pointEntered = true;
            }
        } else if (src == parent.buttonSin) {
            selectedAction = 's';
            currentResult = displayValue;
            parent.displayField.setText("");
            pointEntered = false;
        } else if (src == parent.buttonCos) {
            selectedAction = 'c';
            currentResult = displayValue;
            parent.displayField.setText("");
            pointEntered = false;
        } else {
            String clickedButtonLabel = clickedButton.getText();
            parent.displayField.setText(dispFieldText + clickedButtonLabel);
        }
    }
}