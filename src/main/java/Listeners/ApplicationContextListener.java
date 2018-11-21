package Listeners;

        import MySession.MySessionContainer;

        import javax.servlet.ServletContext;
        import javax.servlet.ServletContextEvent;
        import javax.servlet.ServletContextListener;

public class ApplicationContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext= sce.getServletContext();
        servletContext.setAttribute("sessionContainer",new MySessionContainer(10000));
        servletContext.setAttribute("maxHttpHeaderSize",16384);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
