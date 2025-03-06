package org.islom.dars241.repo;

import org.islom.dars241.entity.Card;
import org.islom.dars241.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    List<Transfer> findBySender(Card sender);
    List<Transfer> findByReceiver(Card receiver);
}