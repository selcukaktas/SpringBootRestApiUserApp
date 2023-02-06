package com.aktas.selcuk.repository;

import com.aktas.selcuk.entity.User1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User1,Long> {
//    User findByFirstName(String firstName);
//    User findByLastName(String lastName);
//    @Query("")
//    User getUser(String user);


}
