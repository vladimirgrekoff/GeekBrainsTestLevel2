//Домашнее задание,уровень 2, урок 1: Владимир Греков
package lesson1;

import lesson1.interfaces.Participant;

public class RoboDog implements Participant {
    private String name = "Unitree.A1";

    private boolean ready = true;
    private static final int LIMIT_JUMPING_HEIGHT = 40; // высота препятствия в см.
    private static final int LIMIT_RUNNING_DISTANCE = 8600; // при скорости 13 км/ч за 40 мин.(один заряд)

    private int count = 0;
    private int number;


    public RoboDog() {
        this.count++;
        this.number = getCount();
    }

    public void setName(String roboName) {
        this.name = roboName;
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

    public String run(int runDistance) {
        String strRunDistance;

        if (LIMIT_RUNNING_DISTANCE >= runDistance) {
            strRunDistance = "Робо-пёс " + getName() + " пробежал " + runDistance + " м.";
            this.ready = true;
        } else {
            strRunDistance = "Робо-пёс " + getName() + " не смог пробежать " + runDistance + " м.";
            this.ready = false;
        }
        return strRunDistance;
    }

    public String jump(int jumpHeight) {
        String strJumpHeight;

        if (LIMIT_JUMPING_HEIGHT >= jumpHeight) {
            strJumpHeight = "Робо-пёс " + getName() + " взял высоту " + jumpHeight + " см.";
            this.ready = true;
        } else {
            strJumpHeight = "Робо-пёс " + getName() + " не смог взять высоту " + jumpHeight + " см.";
            this.ready = false;
        }
        return strJumpHeight;
    }
}

