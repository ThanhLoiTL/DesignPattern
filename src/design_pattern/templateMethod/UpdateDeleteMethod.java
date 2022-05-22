package design_pattern.templateMethod;
public abstract class UpdateDeleteMethod {
    public void execute() {
        String id = input();
        int index = findById(id);
        boolean isSuccess = action(index);
        announce(isSuccess);
    }

    protected abstract String input();
    protected abstract int findById(String id);
    protected abstract boolean action(int index);
    protected abstract void announce(boolean isSuccess);
}
