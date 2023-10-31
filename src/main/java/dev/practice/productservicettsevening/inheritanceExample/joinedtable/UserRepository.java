package dev.practice.productservicettsevening.inheritanceExample.joinedtable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User save(User user);
    User findUserById(Long id);
}
