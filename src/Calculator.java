import javax.swing.*;
import java.awt.*;

public class Calculator {
    // Объявление всех компонентов калькулятора.
    JPanel windowContent;
    JTextField displayField;
    JButton[] numButtons;  // Массив кнопок с цифрами
    JButton buttonPoint;
    JButton buttonEqual;
    JButton buttonPlus;
    JButton buttonMinus;
    JButton buttonDivide;
    JButton buttonMultiply;
    JButton buttonSquareRoot; // Кнопка для извлечения квадратного корня
    JButton buttonSin; // Кнопка для вычисления sin x
    JButton buttonCos; // Кнопка для вычисления cos x
    JPanel p1;
    JPanel p2;

    // В конструкторе создаются все компоненты
    // и добавляются на фрейм с помощью комбинации
    // BorderLayout и GridLayout
    Calculator() {
        windowContent = new JPanel();
        // Задаём схему для этой панели
        BorderLayout bl = new BorderLayout();
        windowContent.setLayout(bl);

        // Создаём и отображаем поле
        // Добавляем его в Северную область окна
        displayField = new JTextField(30);
        windowContent.add("North", displayField);

        // Создаём панель с GridLayout для цифровых кнопок
        p1 = new JPanel();
        GridLayout gl = new GridLayout(4, 3);
        p1.setLayout(gl);
        // Создаём объект CalculatorEngine
        CalculatorEngine calcEngine = new CalculatorEngine(this);

        // Создаём массив кнопок с цифрами
        numButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numButtons[i] = new JButton(Integer.toString(i));
            p1.add(numButtons[i]);  // Add the button to the panel here
            numButtons[i].addActionListener(calcEngine);
        }

        // Создаём кнопки операций
        buttonPoint = new JButton(".");
        buttonEqual = new JButton("=");
        buttonPlus = new JButton("+");
        buttonMinus = new JButton("-");
        buttonDivide = new JButton("/");
        buttonMultiply = new JButton("*");
        buttonSquareRoot = new JButton("√"); // Извлечение квадратного корня
        buttonSin = new JButton("sin"); // Вычисление sin x
        buttonCos = new JButton("cos"); // Вычисление cos x

        p1.add(buttonPoint);
        p1.add(buttonEqual);

        // Создаём панель для операций
        p2 = new JPanel();
        GridLayout gl2 = new GridLayout(7, 1);
        p2.setLayout(gl2);

        // Добавляем кнопки операций на панель p2
        p2.add(buttonPlus);
        p2.add(buttonMinus);
        p2.add(buttonMultiply);
        p2.add(buttonDivide);
        p2.add(buttonSquareRoot);
        p2.add(buttonSin);
        p2.add(buttonCos);

        // Помещаем панель p1 в центральную область окна
        windowContent.add("Center", p1);

        // Помещаем панель p2 в восточную область окна
        windowContent.add("East", p2);

        // Создаём фрейм и задаём его основную панель
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(windowContent);

        buttonPoint.addActionListener(calcEngine);
        buttonEqual.addActionListener(calcEngine);
        buttonPlus.addActionListener(calcEngine);
        buttonMinus.addActionListener(calcEngine);
        buttonMultiply.addActionListener(calcEngine);
        buttonDivide.addActionListener(calcEngine);
        buttonSquareRoot.addActionListener(calcEngine);
        buttonSin.addActionListener(calcEngine);
        buttonCos.addActionListener(calcEngine);

        // делаем размер окна достаточным
        // для того, чтобы вместить все компоненты
        frame.pack();

        // Наконец, отображаем окно
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }
}