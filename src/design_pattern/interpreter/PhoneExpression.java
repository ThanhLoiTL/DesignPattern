package design_pattern.interpreter;

import model.Person;

public class PhoneExpression extends TerminalExpression{
    protected String phone;

    public PhoneExpression(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean interpreter(Person context) {
        return context.getPhoneNumber().equalsIgnoreCase(phone);
    }
}
