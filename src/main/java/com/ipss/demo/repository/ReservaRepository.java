package com.ipss.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ipss.demo.model.Reserva;
import com.ipss.demo.model.Usuario;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByUsuario(Usuario usuario);
    List<Reserva> findByFecha(LocalDate fecha);
    List<Reserva> findByEstado(String estado);
}
