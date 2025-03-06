package org.islom.dars241.repo;

import org.islom.dars241.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "income")
public interface IncomeRepo extends JpaRepository<Income, Long> {
}
