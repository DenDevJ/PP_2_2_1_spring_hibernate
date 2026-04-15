package hiber.service;

import hiber.model.Car;
import java.util.List;

public interface CarService {
    void saveCars(List<Car> cars);
    List<Car> getAllCars();
}