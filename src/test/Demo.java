package test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.Users;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        try {
            InputStream inputStream= Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory factory;
            factory = new SqlSessionFactoryBuilder().build(inputStream);

            SqlSession session=factory.openSession();
            Users user=new Users("javase","boy",30);
            session.insert("addUser",user);
            session.commit();
            session.close();

            SqlSession sessionUpdate=factory.openSession();
            Users user0=new Users("javaee","girl",20);
            sessionUpdate.update("updateUser",user0);
            sessionUpdate.commit();
            sessionUpdate.close();

            SqlSession sessionSelectAll=factory.openSession();
            List<Users> users=sessionSelectAll.selectList("findUsers");
            for(Users user1:users){
                System.out.println(user1.getName()+"\t"+user1.getSex()+"\t"+user1.getAge());
            }
            sessionSelectAll.close();

            SqlSession sessionLimite=factory.openSession();
            String name="javase";
            List<Users> usersList=sessionLimite.selectList("findUsersByName",name);
            for(Users user2:usersList){
                System.out.println(user2.getName()+"\t"+user2.getSex()+"\t"+user2.getAge());
            }
            sessionLimite.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
