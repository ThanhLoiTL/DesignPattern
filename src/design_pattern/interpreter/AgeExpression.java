package design_pattern.interpreter;

import model.Person;

public class AgeExpression extends TerminalExpression{
    protected int age;

    public AgeExpression(int age) {
        this.age = age;
    }

    @Override
    public boolean interpreter(Person context) {
        return context.getAge() == age;
    }
}
