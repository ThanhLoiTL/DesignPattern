package design_pattern.interpreter;

import model.Person;

public class OrExpression implements Expression{
    protected Expression left;
    protected Expression right;

    public OrExpression(Expression left, Expression right) {
        super();
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean interpreter(Person context) {
        return left.interpreter(context) || right.interpreter(context);
    }

}
