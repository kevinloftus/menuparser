package assignable;

public class Yugo implements Car {

    @Override
    public boolean start() {
        System.out.println("sputter...cough....cough....");
        return false;
    }

}
