package design_pattern.interpreter;

import model.Person;

public class NameExpression extends TerminalExpression{
    protected String name;

    public NameExpression(String name) {
        this.name = name;
    }

    @Override
    public boolean interpreter(Person context) {
        return context.getName().equalsIgnoreCase(name);
    }
}
