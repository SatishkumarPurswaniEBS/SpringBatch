package com.gog.batch;

import com.gog.model.MyFinalUser;
import com.gog.model.MyUser;
import com.gog.repository.UserRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class UserReaderFromDB implements ItemReader<List<MyFinalUser>> {

    @Autowired
    UserRepo userRepo;

    @PersistenceContext
    private EntityManager entityManager;

    private boolean read = true;
    @Override
    public List<MyFinalUser> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        List<MyUser> users = userRepo.findAll();
        if(read) {
            read = false;
            return users.stream().map(MyUser::toFinalUser).toList();
        }
        /*if (CollectionUtils.isEmpty(users)) {
            return null;
        } else {
            users.stream().forEach(entityManager::detach);
        }
        return users.stream().map(MyUser::toFinalUser).toList();*/
        return null;
    }
}
