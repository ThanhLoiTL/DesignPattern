package design_pattern.interpreter;

import model.Person;

public class GenderExpression extends TerminalExpression{
    protected String gender;

    public GenderExpression(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean interpreter(Person context) {
        return context.getGender().equalsIgnoreCase(gender);
    }
}
