package com.dasbiersec.reloader;

import com.dasbiersec.reloader.dao.ComponentDAO;
import com.dasbiersec.reloader.dao.RoundDAO;
import com.dasbiersec.reloader.model.Round;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloApp
{
    public static void main(String[] args)
    {
//        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
//        HelloService helloService = context.getBean(HelloService.class);
//        System.out.println(helloService.sayHello());

		ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
	    ComponentDAO componentDAO = (ComponentDAO) context.getBean("ComponentDAO");
	    RoundDAO roundDAO = (RoundDAO) context.getBean("RoundDAO");


	    Round round = (Round) roundDAO.getById(16);


	    System.out.println(round.getBrass().getName());
    }
}
