package Controllers;

        import DAO.ProductDAO;
        import DAO.impl.MemoryProductDAO;

        import javax.servlet.ServletException;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;

public class HelloController extends HttpServlet {
    private ProductDAO productDAO=new MemoryProductDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {


            req.setAttribute("products", productDAO.selectAllProducts());
        }catch (Exception ex){
            resp.sendRedirect("/pages/error.jsp");
        }
        req.getRequestDispatcher("/pages/hello.jsp").forward(req,resp);
    }
}
