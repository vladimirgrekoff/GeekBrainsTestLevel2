//Домашнее задание,уровень 2, урок 4: Владимир Греков
package lesson4;

public class Main {
    public static void main(String[] args) {
        TwoThread twoThread = new TwoThread();

        OneThread.runOneThread(); //обработать массив одним потоком

        TwoThread.runTwoThread(); //обработать массив двумя потоками
        //два ппотока для 2-х равных массивов в половину размера большого
        Thread thread1 = new Thread(twoThread::calculateForArrLeft, "A");
        Thread thread2 = new Thread(twoThread::calculateForArrRight, "B");

        thread1.setPriority(10);
        thread1.start();
        thread2.setPriority(10);
        thread2.start();

        //соединение 2-х частей в большой массив
        TwoThread.joinArrayAndPrintTime();

    }
}
