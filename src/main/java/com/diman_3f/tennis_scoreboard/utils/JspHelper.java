package com.diman_3f.tennis_scoreboard.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JspHelper {

    private static final String JSP_FORMAT = "/WEB-INF/jsp/%s.jsp";

    public static String getPath(String jspName) {
        return String.format(JSP_FORMAT,jspName);
    }
}
