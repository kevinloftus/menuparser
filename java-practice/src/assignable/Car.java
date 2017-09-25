package assignable;

import java.lang.reflect.InvocationTargetException;

public interface Car {
    
    public static Car createNewCar(Class<?> carClass) {
        if (carClass != null && !carClass.isInterface() && Car.class.isAssignableFrom(carClass)) {
            try {
                return (Car) Class.forName(carClass.getName()).getConstructor().newInstance();
            }
            catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    public boolean start();
}
