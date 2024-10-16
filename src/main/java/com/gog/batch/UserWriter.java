package com.gog.batch;

import com.gog.model.MyUser;
import com.gog.repository.UserRepo;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class UserWriter implements ItemWriter<MyUser> {

    @Autowired
    UserRepo userRepo;

    public void write(Chunk<? extends MyUser> users) throws Exception {
        for (MyUser myUser : users) {
            System.out.println("Writing the data " + myUser);
        }
            userRepo.saveAll(users);
    }
}
