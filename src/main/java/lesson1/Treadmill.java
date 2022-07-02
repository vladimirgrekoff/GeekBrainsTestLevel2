//Домашнее задание,уровень 2, урок 1: Владимир Греков
package lesson1;

import lesson1.interfaces.Obstacle;
import lesson1.interfaces.Participant;

public class Treadmill implements Obstacle {
    private String name = "Дорожка";
    private int length;

    public Treadmill() {
    }

    public Treadmill(int treadmillLength) {
        this.length = treadmillLength;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int treadmillLength) {
        this.length = treadmillLength;
    }

    public void info() {
        System.out.println(getName() + " длиной: " + getLength() + " м.");
    }


    @Override
    public void passed(Participant p) {
        if (p.Ready()) {
            System.out.println(p.run(getLength()));
        }
    }
}
