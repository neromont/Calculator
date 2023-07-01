import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    private static NumericType numType;
    private static int firstOperand;
    private static int secondOperand;
    private enum OperationType {
        Addition,
        Subtraction,
        Multiplication,
        Division
    }
    private enum NumericType {
        Rome,
        Arabic,
        Undefined
    }


    public static void main(String[] args) {

            // Небольшое приветствие
            System.out.println("----------------------------------------------------------------\n•                       Калькулятор 1.0                        •\n----------------------------------------------------------------");
            System.out.println("Введите арифметическое выражение используя числа от 0 до 10 \nарабскими или римскими цифрами и нажмите Enter: ");

            // Сканируем строку и передаем в калькулятор
            Scanner scanner = new Scanner ( System.in );
            do {
                System.out.println(calc(scanner.nextLine()));
            } while (true);
    }

    // Вычисляем результат
    public static String calc ( String input )
    {
        OperationType currentOperation;
        String[] currentOperands;
        currentOperation = getOperation(input);
        currentOperands = getOperators(input, currentOperation);

        if ( isCorrectValue ( currentOperands ) )
        {
            return showResult ( calculate ( currentOperation ) );
        }

        return "";
    }

    static OperationType getOperation ( String expression )
    {
            if (symbolMatch(expression, '+') == 1 && !expression.contains("-") && !expression.contains("*") && !expression.contains("/"))
                return OperationType.Addition;
            if (symbolMatch(expression, '-') == 1 && !expression.contains("+") && !expression.contains("*") && !expression.contains("/"))
                return OperationType.Subtraction;
            if (symbolMatch(expression, '*') == 1 && !expression.contains("-") && !expression.contains("+") && !expression.contains("/"))
                return OperationType.Multiplication;
            if (symbolMatch(expression, '/') == 1 && !expression.contains("+") && !expression.contains("-") && !expression.contains("*"))
                return OperationType.Division;
        try {
            throw new IOException();
        } catch (IOException e) {
            System.out.println("Строка не является математической операцией.");
            System.exit(0);
            throw new RuntimeException(e);
        }
    }

    static String[] getOperators ( String expression , OperationType operationType )
    {
        String[] operators = { "", "" };
        String firstOperand, secondOperand;
        String searchSymbol = getOperatorSymbol(operationType);
        firstOperand = expression.substring( 0 , expression.indexOf(searchSymbol) );
        secondOperand = expression.substring( expression.lastIndexOf(searchSymbol) + 1 );
        if ( firstOperand.trim().isEmpty() &&  secondOperand.trim().isEmpty() ) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Строка не является математической операцией. Отсутствуют операнды.");
                System.exit(0);
                throw new RuntimeException(e);
            }
        }
        if ( firstOperand.trim().isEmpty() ) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Строка не является математической операцией. Отсутствует первый операнд.");
                System.exit(0);
                throw new RuntimeException(e);
            }
        }
        if ( secondOperand.trim().isEmpty() ) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Строка не является математической операцией. Отсутствует второй операнд.");
                System.exit(0);
                throw new RuntimeException(e);
            }
        }
        operators[0] =  firstOperand.trim();
        operators[1] =  secondOperand.trim();
        return operators;
    }

    static boolean isCorrectValue ( String[] operands )
    {
        int operand;
        NumericType ntFirstOperand = NumericType.Undefined;
        NumericType ntSecondOperand = NumericType.Undefined;

        for ( int i = 0 ; i < operands.length ; i++ )
        {
            try {
                // Пробуем извлечь число
                operand = Integer.parseInt(operands[i]);
                if ( operand > 0 && operand < 11 )
                {
                    if ( i == 0 ) {
                        ntFirstOperand = NumericType.Arabic;
                        firstOperand = operand;
                    }
                    else {
                        ntSecondOperand = NumericType.Arabic;
                        secondOperand = operand;
                    }
                } else {
                    System.out.println("Число слишком большое. Используйте от 1 до 10.");
                    return false;
                }

            } catch ( Exception e )
            {
               try {
                   // Пробуем извлечь римское число
                   operand = convertFromRome ( operands[i] );
                   if ( operand > 0 && operand < 11 )
                   {
                       if ( i == 0 ) {
                           ntFirstOperand = NumericType.Rome;
                           firstOperand = operand;
                       }
                       else {
                           ntSecondOperand = NumericType.Rome;
                           secondOperand = operand;
                       }
                   } else {
                       System.out.println("Число слишком большое. Используйте от I до X.");
                       return false;
                   }
               } catch (Exception ev ) {

                   // Не получается извлечь даже римское число
                   System.out.println("Введенные значения не являются числами.");
                   System.exit(0);
               }
            }
        }

        if (ntFirstOperand == ntSecondOperand && ntFirstOperand != NumericType.Undefined)
        {
            numType = ntFirstOperand;
            return true;
        } else {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Используются разные системы счисления.");
                System.exit(0);
            }
        }
        return false;
    }

    static int calculate (  OperationType operationType )
    {
        if ( operationType == OperationType.Addition ) return firstOperand + secondOperand;
        if ( operationType == OperationType.Division) return firstOperand / secondOperand;
        if ( operationType == OperationType.Subtraction) return firstOperand - secondOperand;
        if ( operationType == OperationType.Multiplication ) return firstOperand * secondOperand;
        return 0;
    }

    static String showResult ( int result )
    {
        if (numType == NumericType.Arabic)
        {
            return Integer.toString(result);
        } else {
            return convertFromArabic( result );
        }
    }

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

    static String getOperatorSymbol ( OperationType operationType )
    {
        if ( operationType == OperationType.Addition ) return "+";
        if ( operationType == OperationType.Subtraction) return "-";
        if ( operationType == OperationType.Multiplication ) return "*";
        return "/";
    }

    static int convertFromRome ( String operand )
    {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I' , 1);
        map.put('V' , 5);
        map.put('X' , 10);
        map.put('L' , 50);
        map.put('C' , 100);

        int end = operand.length() - 1;
        char[] arr = operand.toCharArray();
        int arabian;
        int result = map.get(arr[end]);

        for ( int i = end-1 ; i >=0 ; i-- )
        {
            arabian = map.get(arr[i]);

            if ( arabian < map.get(arr[i+1])) {
                result -= arabian;
            } else {
                result += arabian;
            }
        }
        return result;
    }

    static String convertFromArabic ( int operand )
    {
        if (operand <= 0) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Римская система счисления не поддерживает нулевые и отрицательные значения.");
                System.exit(0);
            }
        }
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(100 , "C");
        map.put(90 , "XC");
        map.put(50 , "L");
        map.put(40 , "XL");
        map.put(10 , "X");
        map.put(9 , "IX");
        map.put(5 , "V");
        map.put(4 , "IV");
        map.put(1 , "I");

        String roman = "";
        int arabianKey;
        do {
            arabianKey = map.floorKey(operand);
            roman+= map.get(arabianKey);
            operand -= arabianKey;
        } while ( operand != 0 );
        return roman;
    }
}
