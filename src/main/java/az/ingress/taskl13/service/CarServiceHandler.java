package az.ingress.taskl13.service;

import az.ingress.taskl13.dao.entity.CarEntity;
import az.ingress.taskl13.dao.repository.CarRepository;
import az.ingress.taskl13.model.enums.CarStatus;
import az.ingress.taskl13.model.request.CreateCarRequest;
import az.ingress.taskl13.model.request.UpdatePriceRequest;
import az.ingress.taskl13.model.response.CarResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceHandler implements CarService {
    private final CarRepository carRepository;

    @Override
    public void saveCar(CreateCarRequest car) {
        carRepository.save(
                CarEntity.builder()
                        .brand(car.getBrand())
                        .model(car.getModel())
                        .year(car.getYear())
                        .fuelType(car.getFuelType())
                        .price(car.getPrice())
                        .status(CarStatus.ACTIVE)
                        .build()
        );
    }

    @Override
    public void deleteCar(Long id) {
        var car = fetchCarIfExits(id);
        car.setStatus(CarStatus.SOLD);
        carRepository.save(car);
    }

    @Override
    public void updateCarPrice(Long id, UpdatePriceRequest request) {
        var car = fetchCarIfExits(id);
        car.setPrice(request.getPrice());
        carRepository.save(car);
    }

    @Override
    public CarResponse getCar(Long id){
        var car = fetchCarIfExits(id);
        return new CarResponse(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getYear(),
                car.getFuelType(),
                car.getPrice()
        );
    }

    @Override
    public List<CarResponse> getAllCars(){
        List<CarResponse> carResponses = new ArrayList<>();

        carRepository.findAll().forEach(car -> {
            carResponses.add(new CarResponse(
                    car.getId(),
                    car.getBrand(),
                    car.getModel(),
                    car.getYear(),
                    car.getFuelType(),
                    car.getPrice()
            ));
        });
        return carResponses;
    }

    private CarEntity fetchCarIfExits(Long id) {
        return carRepository.findByIdAndStatusNot(id, CarStatus.SOLD)
                .orElseThrow(RuntimeException::new);
    }
}
