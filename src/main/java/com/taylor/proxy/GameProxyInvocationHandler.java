package com.taylor.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/10/20 10:28
 */
public class GameProxyInvocationHandler implements InvocationHandler {
    Object obj;

    public GameProxyInvocationHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName() + "被调用了。。。");
        if ("login".equals(method.getName())) {
            System.out.println("有人登陆我的账号了");
        }
        return method.invoke(this.obj, args);
    }

    public static void main(String... args) {
        IGame game = new Game();
        InvocationHandler handler = new GameProxyInvocationHandler(game);
        IGame proxy = (IGame) Proxy.newProxyInstance(Game.class.getClassLoader(), Game.class.getInterfaces(), handler);
        proxy.login();
        proxy.killBoss();
        proxy.upgrade();

    }
}
