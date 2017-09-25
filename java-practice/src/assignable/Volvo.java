package assignable;

public class Volvo implements Car {

    @Override
    public boolean start() {
        System.out.println("Ja! vroom vroom....");
        return true;
    }

}
