package design_pattern.interpreter;

import model.Person;

public class EmailExpression extends TerminalExpression{
    protected String email;

    public EmailExpression(String name) {
        this.email = email;
    }

    @Override
    public boolean interpreter(Person context) {
        return context.getName().equalsIgnoreCase(email);
    }
}
