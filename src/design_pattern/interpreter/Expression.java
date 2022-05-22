package design_pattern.interpreter;

import model.Person;

public interface Expression {
    boolean interpreter(Person context);
}
