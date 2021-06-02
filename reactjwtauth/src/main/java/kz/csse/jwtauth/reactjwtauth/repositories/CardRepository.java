package kz.csse.jwtauth.reactjwtauth.repositories;


import kz.csse.jwtauth.reactjwtauth.entities.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CardRepository extends JpaRepository<Cards, Long> {
    Cards findByIdEquals(Long id);
    List<Cards> findAllByName( String name) ;
}
