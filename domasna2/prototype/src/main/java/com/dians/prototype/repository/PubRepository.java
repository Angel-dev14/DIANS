package com.dians.prototype.repository;

import com.dians.prototype.model.Pub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PubRepository extends JpaRepository<Pub, Long> {
}
