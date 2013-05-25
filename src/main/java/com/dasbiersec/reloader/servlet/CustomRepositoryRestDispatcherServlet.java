package com.dasbiersec.reloader.servlet;

import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class CustomRepositoryRestDispatcherServlet extends DispatcherServlet {

	private static final long serialVersionUID = 1L;

	public CustomRepositoryRestDispatcherServlet() {
		configure();
	}

	public CustomRepositoryRestDispatcherServlet(WebApplicationContext webApplicationContext) {
		super(webApplicationContext);
		configure();
	}

	private void configure() {
		setContextClass(AnnotationConfigWebApplicationContext.class);
		setContextConfigLocation(RepositoryRestMvcConfiguration.class.getName());
	}

}

