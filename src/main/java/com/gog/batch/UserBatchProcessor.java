package com.gog.batch;

import com.gog.model.MyUser;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class UserBatchProcessor implements ItemProcessor<MyUser, MyUser> {

    @Override
    public MyUser process(MyUser myUser) throws Exception {
        myUser.setName(myUser.getName() + " - Processed");
        return myUser;
    }
}
