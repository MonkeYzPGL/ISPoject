package proiect_spring.Proiect_IS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proiect_spring.Proiect_IS.model.Proiect;
import java.util.*;
@Repository
public interface ProiectRepository extends JpaRepository<Proiect, Integer> {
    List<Proiect> findByEchipe_Id(int echipaId);
}
