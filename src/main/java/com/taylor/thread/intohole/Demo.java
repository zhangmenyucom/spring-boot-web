package com.taylor.thread.intohole;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/10/24 15:12
 */
public class Demo {


    public static void main(String[] args) {
        Stone stone = new Stone();
        Person p1 = new Person(stone, "p1");
        Person p2 = new Person(stone, "p2");
        Person p3 = new Person(stone, "p3");
        Person p4 = new Person(stone, "p4");
        Person p5 = new Person(stone, "p5");
        Person p6 = new Person(stone, "p6");
        Person p7 = new Person(stone, "p7");
        Person p8 = new Person(stone, "p7");
        Person p9 = new Person(stone, "p7");
        Person p10 = new Person(stone, "p7");
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
        p6.start();
        p7.start();
        p8.start();
        p9.start();
        p10.start();
    }
}

@Data
@EqualsAndHashCode(callSuper = false)
class Person extends Thread {
    private String personName;
    private Stone stone;

    Person(Stone stone, String personName) {
        super(personName);
        this.stone = stone;
        this.personName = personName;
    }

    public void getThrowth() {
        stone.getInto(this);
    }

    @Override
    public void run() {
        this.getThrowth();
    }
}

class Stone {
    public synchronized void getInto(Person person) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(person.getPersonName() + "通过石头啦");
    }
}
