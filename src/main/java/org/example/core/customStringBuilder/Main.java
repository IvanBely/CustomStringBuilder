package org.example.core.customStringBuilder;

public class Main {
    public static void main(String[] args) {
        CustomStringBuilder csb = new CustomStringBuilder();
        csb.append("Hello");
        System.out.println(csb); // Hello

        csb.append(" World");
        System.out.println(csb); // Hello World

        csb.undo();
        System.out.println(csb); // Hello

        csb.replace(0, 5, "Hi");
        System.out.println(csb); // Hi

        csb.undo();
        System.out.println(csb); // Hello

        csb.delete(0, 5);
        System.out.println(csb); //

        csb.undo();
        System.out.println(csb); // Hello
    }
}