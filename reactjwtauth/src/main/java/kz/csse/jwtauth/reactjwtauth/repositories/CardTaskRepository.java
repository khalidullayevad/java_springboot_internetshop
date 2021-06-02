package kz.csse.jwtauth.reactjwtauth.repositories;


import kz.csse.jwtauth.reactjwtauth.entities.CardTasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CardTaskRepository extends JpaRepository<CardTasks, Long> {
    CardTasks findByIdEquals(Long id);

    List<CardTasks> findAllByCard_Id(Long id);
}
