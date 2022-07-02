//Домашнее задание,уровень 2, урок 2: Владимир Греков
package lesson2;

public class MyArrayDataException extends Exception {
    public MyArrayDataException(int i, int j,String value){
        super(String.format("Ячейка с индексами %d,%d содержит не числовое значение: \"%s\".", i, j, value));
    }
}