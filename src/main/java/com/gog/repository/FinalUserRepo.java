package com.gog.repository;

import com.gog.model.MyFinalUser;
import com.gog.model.MyUser;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinalUserRepo extends JpaRepository<MyFinalUser, Integer> {
}
