package ru.clevertec.logging.starter.entity;

public class MyPrototype {
    private String arg;

    public MyPrototype(String arg) {
        this.arg = arg;
    }

    public void action() {
        System.out.println(arg);
    }
}