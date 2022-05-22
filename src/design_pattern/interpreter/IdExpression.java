package design_pattern.interpreter;

import model.Person;

public class IdExpression extends TerminalExpression{
    protected String id;

    public IdExpression(String id) {
        this.id = id;
    }

    @Override
    public boolean interpreter(Person context) {
        return context.getId().equalsIgnoreCase(id);
    }
}
