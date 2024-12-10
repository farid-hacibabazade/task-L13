package az.ingress.taskl13.service;

import az.ingress.taskl13.model.request.CreateCarRequest;
import az.ingress.taskl13.model.request.UpdatePriceRequest;
import az.ingress.taskl13.model.response.CarResponse;

import java.math.BigDecimal;
import java.util.List;

public interface CarService {

    void saveCar(CreateCarRequest car);

    void deleteCar(Long id);

    void updateCarPrice(Long id, UpdatePriceRequest request);

    CarResponse getCar(Long id);

    List<CarResponse> getAllCars();
}
