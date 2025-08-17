package com.ipss.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ipss.demo.model.Mesa;
import java.util.List;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {
    List<Mesa> findByDisponible(boolean disponible);
    List<Mesa> findByCapacidadGreaterThanEqual(int capacidad);
}
