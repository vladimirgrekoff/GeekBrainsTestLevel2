//Домашнее задание,уровень 2, урок 1: Владимир Греков
package lesson1;

import lesson1.interfaces.Obstacle;
import lesson1.interfaces.Participant;

public class Wall implements Obstacle {
    private String name = "Стена";
    private int height;

    public Wall() {
    }

    public Wall(int wallHeight) {
        this.height = wallHeight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int wallHeight) {
        this.height = wallHeight;
    }

    public void info() {
        System.out.println(getName() + " высотой: " + getHeight() + " см.");
    }

    @Override
    public void passed(Participant p) {
        if (p.Ready()) {
            System.out.println(p.jump(getHeight()));
        }
    }
}

