//Домашнее задание,уровень 2, урок 1: Владимир Греков
package lesson1;

import lesson1.interfaces.Participant;

public class Human implements Participant {
    private String name;

    private boolean ready = true;
    private static final int LIMIT_RUNNING_DISTANCE = 400;//предельное расстояние при макс. скорости
    private static final int LIMIT_JUMPING_HEIGHT = 200; // высота препятствия в см.
    private int count = 0;
    private int number;

    public Human(String name) {
        this.name = name;
        this.count++;
        this.number = getCount();
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }



    public int getNumber() {
        return number;
    }



    @Override
    public boolean Ready() {
        return ready;
    }


    @Override
    public String run(int runDistance) {
        String strRunDistance = "";

        if (LIMIT_RUNNING_DISTANCE >= runDistance) {
            strRunDistance = "Человек " + getName() + " пробежал " + runDistance + " м.";
            this.ready = true;
        } else {
            strRunDistance = "Человек " + getName() + " не смог пробежать " + runDistance + " м.";
            this.ready = false;
        }
        return strRunDistance;
    }

    @Override
    public String jump(int jumpHeight) {
        String strJumpHeight = "";

        if (LIMIT_JUMPING_HEIGHT >= jumpHeight) {
            strJumpHeight = "Человек " + getName() + " взял высоту " + jumpHeight + " см.";
            this.ready = true;
        } else {
            strJumpHeight = "Человек " + getName() + " не смог взять высоту " + jumpHeight + " см.";
            this.ready = false;
        }
        return strJumpHeight;
    }

}

