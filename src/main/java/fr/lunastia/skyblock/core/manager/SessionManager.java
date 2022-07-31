package fr.lunastia.skyblock.core.manager;

import fr.lunastia.skyblock.core.session.Session;

import java.util.HashMap;
import java.util.UUID;

public class SessionManager {
    private HashMap<UUID, Session> sessions;

    public SessionManager() {
        this.sessions = new HashMap<>();
    }

    public void createSession() {
        // TODO
    }

    public Session getSession(UUID uuid) {
        return this.sessions.get(uuid);
    }
}
