package org.teomant.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;


public class Initializer implements WebApplicationInitializer{

    @Override
    public void onStartup( ServletContext servletContext ){
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register( WebServiceConfig.class);
        servletContext.addListener( new ContextLoaderListener( ctx ) );

        ctx.setServletContext( servletContext );

        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(ctx);
        servlet.setTransformWsdlLocations(true);
        ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcher",servlet);
        dynamic.addMapping("/*");
        dynamic.setLoadOnStartup(1);
    }
}