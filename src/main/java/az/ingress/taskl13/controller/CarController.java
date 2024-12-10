package az.ingress.taskl13.controller;

import az.ingress.taskl13.model.request.CreateCarRequest;
import az.ingress.taskl13.model.request.UpdatePriceRequest;
import az.ingress.taskl13.model.response.CarResponse;
import az.ingress.taskl13.service.CarService;
import com.fasterxml.jackson.databind.annotation.NoClass;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCar(@RequestBody CreateCarRequest car){
        carService.saveCar(car);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCar(@PathVariable Long id){
        carService.deleteCar(id);
    }

    @PatchMapping("/{id}/price")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCarPrice(@PathVariable Long id,
                               @RequestBody UpdatePriceRequest request){
        carService.updateCarPrice(id, request);
    }

    @GetMapping("/{id}")
    public CarResponse getCar(@PathVariable Long id){
        return carService.getCar(id);
    }

    @GetMapping
    public List<CarResponse> getAllCars(){
        return carService.getAllCars();
    }
}
