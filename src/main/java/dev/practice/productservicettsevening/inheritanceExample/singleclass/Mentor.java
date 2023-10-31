package dev.practice.productservicettsevening.inheritanceExample.singleclass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("3")
public class Mentor extends User {
    private int noOfMentees;
}
