package MySession;

public interface MyHttpSession {
    void addAttribute(String name,Object value);

    Object getAttribute(String name);

    void deleteAttribute(String name);

    long getCreationTime();

    void setCreationTime(long creationTime);

    String getSESSION_ID();

    void setSESSION_ID(String SESSION_ID);
}
