package MySession;

        import javax.servlet.http.Cookie;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.util.Iterator;
        import java.util.Map;
        import java.util.concurrent.ConcurrentHashMap;

public class MySessionContainer {
    private ConcurrentHashMap<String, MyHttpSession> sessions;
    private long timeToLive;

    public MySessionContainer(long timeToLive){
        sessions=new ConcurrentHashMap<>();
        this.timeToLive=timeToLive;
        Thread thread=new Thread(new ContainerCleaner());
        thread.start();

    }

    public MyHttpSession createSession(HttpServletRequest request, HttpServletResponse response) {
        String ip = request.getRemoteAddr();
        long preSession_id = 0;
        for (int i = 0; i < ip.length(); i++) {
            preSession_id += (long)ip.charAt(i);
        }
        String session_id=String.valueOf(preSession_id);
        MyHttpSessionImpl myHttpSession=new MyHttpSessionImpl(session_id);
        Cookie cookie=new Cookie("MY_ID",session_id);

        cookie.setPath("/product");
        cookie.setMaxAge(1000);
        response.addCookie(cookie);
        sessions.put(session_id,myHttpSession);
        return myHttpSession;
    }
    public MyHttpSession getSession(HttpServletRequest request,HttpServletResponse response,boolean isCreate){
       Cookie[] cookie =request.getCookies();
       for(int i=0;i<cookie.length;i++){
           if(cookie[i].getName().equals("MY_ID")){
               MyHttpSession myHttpSession=sessions.get(cookie[i].getValue());
               if(myHttpSession==null){


                   if(isCreate) {
                       return createSession(request, response);
                   }else {
                       Cookie cooki2=new Cookie("MY_ID","1");

                       cooki2.setPath("/product");
                       cooki2.setMaxAge(0);
                       response.addCookie(cooki2);

                       return null;
                   }
               }else {
                   return myHttpSession;
               }

           }
       }
       if(isCreate) {
           return createSession(request, response);
       }else {
           return null;
       }
    }

 private class ContainerCleaner implements Runnable{



     @Override
     public void run() {
         while (true) {
             Iterator<Map.Entry<String, MyHttpSession>> iterator = sessions.entrySet().iterator();
             if(!iterator.hasNext()){
                 try {


                     Thread.sleep(4000);
                     continue;
                 }catch (Exception ex){
                     ex.printStackTrace();
                 }
             }
             long sleep = 0;
             while (iterator.hasNext()) {
                 Map.Entry<String, MyHttpSession> entry = iterator.next();
                 MyHttpSession session = entry.getValue();
                 if (System.currentTimeMillis() - session.getCreationTime() >= timeToLive) {
                     sessions.remove(session.getSESSION_ID());
                 } else {
                     if (sleep == 0) {
                         sleep = timeToLive - (System.currentTimeMillis() - session.getCreationTime());
                         continue;
                     }
                     long session_time = timeToLive - (System.currentTimeMillis() - session.getCreationTime());
                     if (session_time < sleep) {
                         sleep = session_time;
                     }

                 }
             }
             try {

                 Thread.sleep(sleep);
             } catch (Exception ex) {
                 ex.printStackTrace();
             }
         }
     }
 }



}

