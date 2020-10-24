package app;

import app.config.WebConfig;
import app.model.User;
import app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class Application implements WebApplicationInitializer {

    Logger logger = LoggerFactory.getLogger(Application.class);
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        UserService.userList.add(new User(1, "Ivan", "Ivanov"));
        UserService.userList.add(new User(2, "Petr", "Petrov"));
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebConfig.class);
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        dispatcher.addMapping("/");
        dispatcher.setLoadOnStartup(1);
    }
}