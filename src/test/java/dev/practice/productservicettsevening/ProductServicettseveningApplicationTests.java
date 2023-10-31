package dev.practice.productservicettsevening;

import dev.practice.productservicettsevening.inheritanceExample.joinedtable.Mentor;
import dev.practice.productservicettsevening.inheritanceExample.joinedtable.MentorRepository;
import dev.practice.productservicettsevening.inheritanceExample.joinedtable.User;
import dev.practice.productservicettsevening.inheritanceExample.joinedtable.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServicettseveningApplicationTests {

    @Autowired
    public UserRepository uRepo;

    @Autowired
    public MentorRepository mRepo;
    @Test
    void contextLoads() {
    }

    @Test
    void testJoinedTable() {
        //User user=new User();
        //user.setEmail("example@gmail.com");
       // user.setPassword("password");
        //uRepo.save(user);

        Mentor mentor=new Mentor();
        mentor.setEmail("example@gmail.com");
        mentor.setPassword("password");
        mentor.setNoOfMentees(10);
        mRepo.save(mentor);
    }

}
