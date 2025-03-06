package org.islom.dars241.repo;

import org.islom.dars241.entity.OutCome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "outcome")
public interface OutComeRepo extends JpaRepository<OutCome, Long> {
}
