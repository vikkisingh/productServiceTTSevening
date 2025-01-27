package dev.practice.productservicettsevening.inheritanceExample.joinedtable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRepository extends JpaRepository<Mentor,Long> {
    Mentor save(Mentor mentor);

    Mentor findMentorById(Long id);
}
