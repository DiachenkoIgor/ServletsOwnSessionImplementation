package Controllers;

import DAO.ProductDAO;
import DAO.impl.MemoryProductDAO;
import Entities.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class ProductPageController extends HttpServlet {
    private String BUCKET_NAME="bucket";
    private ProductDAO productDAO=new MemoryProductDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameterId=req.getParameter("id");
        if(parameterId==null){
            resp.sendRedirect("/pages/error.jsp");
            return;
        }
        try{
            Product pr=productDAO.selectProductById(Long.valueOf(parameterId));
            req.setAttribute("product",pr);


                req.getRequestDispatcher("/pages/product.jsp").forward(req,resp);


        } catch (Exception ex){
            resp.sendRedirect("/pages/error.jsp");
        }
    }

}
