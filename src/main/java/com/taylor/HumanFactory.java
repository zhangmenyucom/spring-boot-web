package com.taylor;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/10/17 18:15
 */
public class HumanFactory {
    public static <T extends Human> T createHuman(Class<T> c) {
        T human = null;
        try {
             human = (T) Class.forName(c.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return human;
    }

    public static void main(String... args) {
        BlackHuman blackHuman = HumanFactory.createHuman(BlackHuman.class);
        blackHuman.talk();
        System.out.println(blackHuman);
    }
}
