package Controllers;

import DAO.ProductDAO;
import DAO.impl.MemoryProductDAO;
import Entities.Product;
import MySession.MyHttpSession;
import MySession.MySessionContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProductBuyController extends HttpServlet {
    private ProductDAO productDAO=new MemoryProductDAO();
    private String BUCKET_NAME="bucket";
    private String PAGE_200="t";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameterId=req.getParameter("id");
        if(parameterId==null){
            resp.sendRedirect("/pages/error.jsp");
            return;
        }
        MySessionContainer mySessionContainer=(MySessionContainer) getServletContext().getAttribute("sessionContainer");
        MyHttpSession session=mySessionContainer.getSession(req,resp,true);


        try {

            if (session.getAttribute(BUCKET_NAME) == null) {
                ConcurrentHashMap<String,Integer> map=new ConcurrentHashMap<>();
                map.put(parameterId,1);
                  session.addAttribute(BUCKET_NAME,map);

            }else {
                ConcurrentHashMap<String,Integer> bucket=
                        (ConcurrentHashMap<String, Integer>) session.getAttribute(BUCKET_NAME);

                Integer quantity=bucket.get(parameterId);
                if(quantity==null){
                    bucket.put(parameterId,1);
                }else {
                    bucket.put(parameterId,++quantity);
                }
                session.addAttribute(BUCKET_NAME,bucket);


            }



            resp.sendRedirect("/product?id="+parameterId);
        }catch (Exception ex){
            resp.sendRedirect("/pages/error.jsp");
        }
    }

}
