package com.gog.batch;

import com.gog.model.MyFinalUser;
import com.gog.model.MyUser;
import com.gog.repository.FinalUserRepo;
import java.util.List;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("listUserWriter")
public class ListUserWriter implements ItemWriter<List<MyFinalUser>> {

    @Autowired
    FinalUserRepo finalUserRepo;

    public void write(Chunk<? extends List<MyFinalUser>> users) throws Exception {
        for (List<MyFinalUser> myUserList : users) {
            for (MyFinalUser myUser : myUserList) {
                System.out.println("Writing the data " + myUser);

                finalUserRepo.save(myUser);
            }
        }
    }
}
