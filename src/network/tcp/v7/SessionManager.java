package network.tcp.v7;

import java.util.ArrayList;
import java.util.List;

public class SessionManager {
    private List<Session> sessions = new ArrayList<>();

    public synchronized void add(Session session){
        sessions.add(session);
    }

    public synchronized void remove(Session session){
        sessions.remove(session);
    }

    public synchronized void clearAll(){
        for (Session session : sessions) {
            remove(session);
        }
        sessions.clear();
    }


}
