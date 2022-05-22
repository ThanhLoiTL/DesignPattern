package design_pattern.interpreter;

public class ExpressionParser {
    protected static final String ID = "id";
    protected static final String NAME = "name";
    protected static final String AGE = "age";
    protected static final String GENDER = "gender";
    protected static final String EMAIL = "email";
    protected static final String PHONE = "phone";

    public static Expression parseAndExpression(String contextString) {
        Expression expression = null;
        String[] keyValues = contextString.split(",");
        for (int index = 0; index < keyValues.length; index++) {
            String keyValue = keyValues[index];
            String[] words = keyValue.trim().split(":");
            if(words.length < 2) {
                System.out.println("=> Có thuộc tính không khớp! <=");
                return null;
            }
            Expression anotherExpression = getExpression(words[0].trim(), words[1].trim());
            if(anotherExpression==null) {
                System.out.println("=> Có thuộc tính không khớp! <=");
                return null;
            }
            if (expression == null) {
                expression = anotherExpression;
            } else {
                expression = new AndExpression(expression, anotherExpression);
            }
        }
        return expression;
    }

    public static Expression parseOrExpression(String contextString) {
        Expression expression = null;
        String[] keyValues = contextString.split(",");
        for (int index = 0; index < keyValues.length; index++) {
            String keyValue = keyValues[index];
            String[] words = keyValue.trim().split(":");
            if(words.length < 2) {
                System.out.println("=> Có thuộc tính không khớp! <=");
                return null;
            }
            Expression anotherExpression = getExpression(words[0].trim(), words[1].trim());
            if(anotherExpression==null) {
                System.out.println("=> Có thuộc tính không khớp! <=");
                return null;
            }
            if (expression == null) {
                expression = anotherExpression;
            } else {
                expression = new OrExpression(expression, anotherExpression);
            }
        }
        return expression;
    }

    public static Expression getExpression(String keyword, String value) {
        if (NAME.equalsIgnoreCase(keyword)) {
            return new NameExpression(value);
        }
        else if (AGE.equalsIgnoreCase(keyword)) {
            return new AgeExpression(Integer.parseInt(value));
        }
        else if (GENDER.equalsIgnoreCase(keyword)) {
            return new GenderExpression(value);
        }
        else if (EMAIL.equalsIgnoreCase(keyword)) {
            return new EmailExpression(value);
        }
        else if (ID.equalsIgnoreCase(keyword)) {
            return new IdExpression(value);
        }
        else if (PHONE.equalsIgnoreCase(keyword)) {
            return new PhoneExpression(value);
        }
        return null;
    }
}
