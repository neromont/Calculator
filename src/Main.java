import java.io.IOException;
import java.util.Scanner;
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private enum OperationType {
        Undefined,
        Addition,
        Substraction,
        Multiplication,
        Divison
    }
    private enum NumericType {
        Rome,
        Arabic
    }
    public static void main(String[] args) {

            // Небольшое приветствие
            System.out.println("----------------------------------------------------------------\n•                       Калькулятор 1.0                        •\n----------------------------------------------------------------");
            System.out.println("Введите арифметическое выражение используя числа от 0 до 10 \nарабскими или римскими цифрами и нажмите Enter: ");

            // Сканируем строку и передаем в калькулятор
            Scanner scanner = new Scanner ( System.in );
            System.out.println ( calc ( scanner.nextLine () ) );
    }
    /*
    // Система счисления
    private enum numberSystem {
        Rome,
        Arabic
    }

    private enum operationType {
        Undefined,
        Addition,
        Substraction,
        Multiplication,
        Divison
    }

    private int firstOperand;
    private int secondOperand;

    static private operationType currentOperation;

    public int getFirstOperand() {
        return firstOperand;
    }

    public void setFirstOperand(int firstOperand) {
        this.firstOperand = firstOperand;
    }

    public int getSecondOperand() {
        return secondOperand;
    }

    public void setSecondOperand(int secondOperand) {
        this.secondOperand = secondOperand;
    }

*/
    // Вычисляем результат
    public static String calc ( String input )
    {
        OperationType currentOperation;
        String[] currentOperands;
        currentOperation = getOperation(input);
        currentOperands = getOperators(input, currentOperation);

        if ( isCorrectValue ( currentOperands ) )
        {
            showResult( calculate ( currentOperands ,currentOperation ) );
        }

        return input;
    }

    static OperationType getOperation ( String expression )
    {
        return OperationType.Undefined;
    }

    static String[] getOperators ( String expression , OperationType operationType )
    {
        String[] operators = { "", "" };
        return operators;
    }

    static boolean isCorrectValue ( String[] operands )
    {
        return true;
    }

    static int calculate ( String [] operands , OperationType operationType )
    {
        return 0;
    }

    static void showResult ( int result )
    {

    }

    /*
    // Разбираем строку и сохраняем данные
    static boolean tryParseExpression ( String expression )
    {
        currentOperation = getOperationIndex ( expression );
        if ( currentOperation != operationType.Undefined) {
            System.out.println("Символ найден");
        } else {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("Вы ввели слишком много операторов!");
                System.exit(0);
            }
        }

        return  true;
    }

    // Ищем вхождения символов
    static int symbolMatch ( String string , char symbol )
    {
        int matches = 0;

        for ( int i = 0 ; i < string.length() ; i++ )
        {
            if ( string.charAt(i) == symbol )
            {
                matches++;
            }
        }
        return matches;
    }

    // Определяем тип операции
    static operationType getOperationIndex ( String expression )
    {
        if ( symbolMatch ( expression , '+') < 2 && expression.indexOf("-") == -1 && expression.indexOf("*") == -1 && expression.indexOf("/") == -1) return operationType.Addition;
        if ( symbolMatch ( expression , '-') < 2 && expression.indexOf("+") == -1 && expression.indexOf("*") == -1 && expression.indexOf("/") == -1) return operationType.Substraction;
        if ( symbolMatch ( expression , '*') < 2 && expression.indexOf("-") == -1 && expression.indexOf("+") == -1 && expression.indexOf("/") == -1) return operationType.Multiplication;
        if ( symbolMatch ( expression , '/') < 2 && expression.indexOf("+") == -1 && expression.indexOf("-") == -1 && expression.indexOf("*") == -1) return operationType.Divison;

        return operationType.Undefined;
    }


    // Проверяем количество операндов
    boolean isLongExpression ( )
    {
        return true;
    }

    // Проверяем систему исчисления
    numberSystem getNumberSystem ( String number )
    {
        return numberSystem.Arabic;
    }*/
}
