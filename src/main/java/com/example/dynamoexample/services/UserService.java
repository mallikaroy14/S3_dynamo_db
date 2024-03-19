package com.example.dynamoexample.services;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.example.dynamoexample.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Users create(Users users){
        dynamoDBMapper.save(users);
        return users;
    }

    public List<Users> getAll(){
       List<Users> users = dynamoDBMapper.scan(Users.class, new DynamoDBScanExpression());
       return users;
    }

    public Users get(String id){
        Users users = dynamoDBMapper.load(Users.class, id);
        return users;
    }

    public String delete(String id){
        Users users = dynamoDBMapper.load(Users.class, id);
        if(users!= null){
            dynamoDBMapper.delete(users);
            return "Deleted items";
        }
        return "User not present";
    }

    public String update(Users users, String id){
        Users singleUser = dynamoDBMapper.load(Users.class, id);
         if(singleUser!= null){
             singleUser.setName(users.getName());
             singleUser.setAge(users.getAge());
             singleUser.setCity(users.getCity());

             dynamoDBMapper.save(singleUser);

             return "updated";
         }
         return "no User found";
    }
}
