//Домашнее задание,уровень 2, урок 6: Владимир Греков
package lesson2;

public class Main {
    public static void main(String[] args) throws MyArraySizeException, MyArrayDataException, InterruptedException {
        String[][] testArray = {{"1", "2", "3", "4"}, {"5", "6", "7", "8"}, {"9", "10", "11", "12"}, {"13", "14", "15", "16"}};
        String[][] testArray1 = {{"1", "2", "3", "4"}, {"5", "6", "7", "8"}, {"9", "10", "11", "12"},{"13", "14", "15", "16"},{"13", "14", "15", "16"}};
        String[][] testArray2 = {{"1", "2", "3", "4"}, {"5", "6", "7", "8"}};
        String[][] testArray3 = {{"1", "2", "3", "4"}, {"5", "6"}, {"9", "10", "11", "12"}, {"13", "14", "15", "16"}};
        String[][] testArray4 = {{"1", "F", "3", "4"}, {"5", "6", "7", "8"}, {"9", "10", "☺", "12"}, {"13", "♥", "13", "15"}};


        System.out.println("Корректный массив testArray");
        sumCellArray(testArray);
        System.out.println();

        System.out.println("Массив testArray1, длина массива 5 строк (индекс i)");
        sumCellArray(testArray1);
        Thread.sleep(1000);
        System.out.println();

        System.out.println("Массив testArray2, длина массива 2 строки (индекс i)");
        sumCellArray(testArray2);
        Thread.sleep(1000);
        System.out.println();

        System.out.println("Массив testArray3, длина массива 4 строки (индекс i), для i=1 2 элемента");
        sumCellArray(testArray3);
        Thread.sleep(1000);
        System.out.println();

        System.out.println("Массив testArray4, длина массива корректна, есть ячейки с нечисловыми данными");
        sumCellArray(testArray4);
        Thread.sleep(1000);
        System.out.println();


    }

    private static void sumCellArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        int Sum = 0;
        String strCheck;


        if (isIllegalSizeArray(array)) {//если размеры не соответствуют
            GenerateException(array); //создаем исключение
        } else {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    try {
                        if (!isNumeric(array[i][j])) {//если строка нечисловая
                            strCheck = array[i][j];
//                            array[i][j] = "0";
                            Sum += Integer.parseInt("0"); //заменяем на 0
                            throw new MyArrayDataException(i, j, strCheck); //создаем исключение
                        } else {
                            Sum += Integer.parseInt(array[i][j]);
                        }
                    } catch (MyArrayDataException e) {
                        e.printStackTrace();
                        System.out.println("Результат получен без учета ячейки: " + i + "," + j + "!!!");
                    }
                }
            }
            System.out.println("Сумма элементов: " + Sum);
        }
    }

    private static void GenerateException(String[][] array) throws MyArraySizeException {
        //создание исключения MyArraySizeException
        try {
            String message;
            message = getMessageSizeException(array);
            throw new MyArraySizeException(message);
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        }
    }

    private static boolean isIllegalSizeArray(String[][] array) {
        //нарушены размеры массива?
        return array.length != 4 || isOutArraySizeJ(array);
    }

    private static String getMessageSizeException(String[][] array) {
        //получение строки сообщения для исключения при нарушении размеров массива
        String message;
        if (array.length != 4) {
            message = getMessageByI(array);
        } else {
            message = getMessageForJ(array);
        }
        return message;
    }
    private static boolean isOutArraySizeJ(String[][] array) {
        //проверка нарушен ли размер массивов по индексам i
        boolean value = false;
        for (int i = 0; i < array.length-1; i++) {
            if (array[i].length != 4){
                value = true;
                break;
            }else {
                value = false;
            }
        }
        return value;
    }

    private static String getMessageByI(String[][] array) {
        //строка сообщения при нарушении размеров по индексу I
        String string;
        if (array.length > 4) {
            string = String.format("Размер массива по индексу i (больше 4) равен %d", array.length);
        } else if (array.length < 4) {
            string = String.format("Критичный размер массива по индексу i (меньше 4!!!) равен %d", array.length);
        } else {
            string = "";
        }
        return string;
    }

    private static String getMessageForJ(String[][] array) {
        //строка сообщения при нарушении размеров для одного из индексов I
        String strMsg = "";
        for (int i = 0; i < array.length-1; i++) {
            if (array[i].length > 4) {
                strMsg = String.format("Размер массива для i = %d (больше 4) равен %d", i, array[i].length);
            } else if (array[i].length < 4) {
                strMsg = String.format("Критичный размер массива для i = %d (меньше 4!!!) равен %d", i, array[i].length);
            }
            if (strMsg.length()>0){
                break;
            }
        }
        return strMsg;
    }


    private static boolean isNumeric(String checkString){
        //Проверка относится ли строка к числам
        int len = checkString.length();
        int counter = 0;

        if (len > 0) {
            for (int i = 0; i < len; i++) {
                if ("01234567890".contains(checkString.substring(i,i+1))) {
                    counter++;
                }
            }
            return (counter == len);
        }
        return false;
    }
}



