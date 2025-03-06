package org.islom.dars241.repo;

import org.islom.dars241.entity.Card;
import org.islom.dars241.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "cards")
public interface CardRepo extends JpaRepository<Card, Long> {
    Card findByNumber(String number);
    List<Card> findByUser(User user);
}
