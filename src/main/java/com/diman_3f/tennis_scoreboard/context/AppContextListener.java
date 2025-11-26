package com.diman_3f.tennis_scoreboard.context;

import com.diman_3f.tennis_scoreboard.dao.PlayerDao;
import com.diman_3f.tennis_scoreboard.services.MatchCreatorService;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServiceLocator.registerService(new PlayerDao());
        ServiceLocator.registerService(new MatchCreatorService());

    }
}
