package MySession;

        import java.util.HashMap;
        import java.util.Map;

public class MyHttpSessionImpl implements MyHttpSession{

    private long creationTime;

    private String SESSION_ID;

    private Map<String,Object> attributes;

    public MyHttpSessionImpl(String SESSION_ID) {
        this.SESSION_ID = SESSION_ID;
        creationTime=System.currentTimeMillis();
        attributes=new HashMap<>();
    }

    public synchronized void addAttribute(String name,Object value){
        creationTime=System.currentTimeMillis();
        attributes.put(name,value);
    }
    public synchronized Object getAttribute(String name){
        creationTime=System.currentTimeMillis();
        return attributes.get(name);
    }
    public synchronized void deleteAttribute(String name){
        creationTime=System.currentTimeMillis();
        attributes.remove(name);
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public String getSESSION_ID() {
        return SESSION_ID;
    }

    public void setSESSION_ID(String SESSION_ID) {
        this.SESSION_ID = SESSION_ID;
    }
}
