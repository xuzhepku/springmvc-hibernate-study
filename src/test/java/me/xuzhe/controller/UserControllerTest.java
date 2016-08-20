package me.xuzhe.controller;

import me.xuzhe.model.HibernateUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.Date;

/**
 * Created by XuZhe on 2016/8/19.
 */
public class UserControllerTest {
    @Test
    public void test01() {
        System.out.println("testtesttest");
        /* hibernate 5中不可用
        Configuration cfg = new Configuration().configure();
        ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
        SessionFactory factory = cfg.buildSessionFactory(sr);
        */
        SessionFactory sf = new Configuration().configure().buildSessionFactory();

        // 创建session
        Session session = sf.openSession();
        // 开启事务
        session.beginTransaction();

        HibernateUser hu = new HibernateUser();
        hu.setUsername("username");
        hu.setPassword("123");
        hu.setNickname("nickname");
        hu.setBornDate(new Date());
        session.save(hu);
        // 提交事务
        session.getTransaction().commit();

    }
}