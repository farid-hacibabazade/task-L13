package az.ingress.taskl13.dao.repository;

import az.ingress.taskl13.dao.entity.CarEntity;
import az.ingress.taskl13.model.enums.CarStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CarRepository extends CrudRepository<CarEntity, Long> {

    Optional<CarEntity> findByIdAndStatusNot(Long id, CarStatus status);
}
