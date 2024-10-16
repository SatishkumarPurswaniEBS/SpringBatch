package com.gog.batch;

import com.gog.model.MyFinalUser;
import com.gog.model.MyUser;
import java.util.List;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("listUserBatchProcessor")
public class ListUserBatchProcessor implements ItemProcessor<List<MyFinalUser>, List<MyFinalUser>> {

    @Override
    public List<MyFinalUser> process(List<MyFinalUser> myUser) throws Exception {
        List proccessedUsers =  myUser.stream().map(myUser1 -> {
            myUser1.setName(myUser1.getName() + " - Multi Processed");
            return myUser1;
        }).toList();
        System.out.println("Processing the data " + proccessedUsers);
        return proccessedUsers;
    }
}
