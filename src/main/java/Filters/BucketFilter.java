package Filters;

import DAO.ProductDAO;
import DAO.impl.MemoryProductDAO;
import MySession.MyHttpSession;
import MySession.MySessionContainer;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BucketFilter extends BaseFilter {
    private String BUCKET_NAME="bucket";
    private ProductDAO productDAO=new MemoryProductDAO();
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        MySessionContainer mySessionContainer=(MySessionContainer) getFilterConfig().getServletContext().getAttribute("sessionContainer");
        MyHttpSession session=mySessionContainer.getSession((HttpServletRequest) request,(HttpServletResponse) response,false);

        if(session==null){
           chain.doFilter(request,response);
return;
        }
        Map<String,Integer> bucket=(Map<String, Integer>)session.getAttribute(BUCKET_NAME);

        if(bucket!=null){
            try {
                Map<String, Integer> stringMap = new HashMap<>();
                Iterator<String> iterator = bucket.keySet().iterator();
                while (iterator.hasNext()) {
                    String id = iterator.next();
                    stringMap.put(productDAO.selectProductById(Long.valueOf(id)).getName(), bucket.get(id));
                }
                request.removeAttribute("products");
                request.setAttribute("products",stringMap);
            }catch (Exception ex){
                request.getRequestDispatcher("/pages/error.jsp").forward(request,response);
            }
        }
        chain.doFilter(request,response);


    }
}
