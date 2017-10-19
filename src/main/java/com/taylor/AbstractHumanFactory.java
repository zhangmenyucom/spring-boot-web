package com.taylor;

public interface AbstractHumanFactory {

    <T extends Human> T createHuman(Class<T> c);
}