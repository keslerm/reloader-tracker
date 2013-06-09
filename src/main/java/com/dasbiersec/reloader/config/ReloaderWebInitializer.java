package com.dasbiersec.reloader.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


public class ReloaderWebInitializer implements WebApplicationInitializer
{
	@Override
	public void onStartup(ServletContext context) throws ServletException
	{
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(ApplicationConfig.class);

		context.addListener(new ContextLoaderListener(rootContext));

		DispatcherServlet MVCServlet = new DispatcherServlet();
		ServletRegistration.Dynamic reg1 = context.addServlet("mvc", MVCServlet);
		reg1.setLoadOnStartup(1);
		reg1.addMapping("/");
	}
}
