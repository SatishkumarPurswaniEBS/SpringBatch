package com.gog.batch;

import com.gog.model.MyUser;
import java.io.File;
import org.springframework.stereotype.Component;

@Component
public class UserReader /*implements ItemReader<Users>*/ {

    private final String[] names = {"Ram", "Shyam", "Mohan", "Sohan", "Rohan", "Raj", "Simran", "Anjali"};

    private int count = 0;

//    @Override
    public MyUser read() throws Exception {

        File file = new File("C:\\SatishkumarPurswani\\EngX\\learnspringbatch\\src\\main\\resources\\test.csv");
        //read content from csv file
        //return user object

        if (count < names.length) {
            MyUser myUser = new MyUser();
            myUser.setName(names[count]);
            count++;
            return myUser;
        } else {
            count = 0;
        }
        return null;
    }
}
