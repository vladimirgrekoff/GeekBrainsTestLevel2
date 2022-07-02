//Домашнее задание,уровень 2, урок 1: Владимир Греков
package lesson1;

import lesson1.interfaces.Obstacle;
import lesson1.interfaces.Participant;

public class Main {
    public static void main(String[] args) {
        Wall wall = new Wall(200);
        Wall wall1 = new Wall(40);
        Treadmill treadmill = new Treadmill(50);
        Treadmill treadmill1 = new Treadmill(50);
        Cat cat1 = new Cat("Мурзик");
        Human human = new Human("Николай");
        RoboDog dog = new RoboDog();

        Participant[] participants = {cat1, dog, human};
        Obstacle[] obstacles = {wall1, treadmill, wall, treadmill1};


        steepleChase(participants, obstacles); //бег с препятствиями
    }

    private static void steepleChase(Participant[] participants, Obstacle[] obstacles) {

        for (Participant participant : participants) {
            for (Obstacle obstacle : obstacles) {
                obstacle.passed(participant);
            }
        }
    }
}
