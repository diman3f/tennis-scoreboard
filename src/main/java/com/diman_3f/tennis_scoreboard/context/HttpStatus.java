package com.diman_3f.tennis_scoreboard.context;

import lombok.Getter;

@Getter
public enum HttpStatus {
    NOT_FOUND(404,"Not found"),
    BAD_REQUEST (400,"Bad Request"),
    INTERNAL_SERVER_ERROR (500, "Internal Server Error"),
    UNKNOWN(-1, "Unknown Status");

    private final int code;
    private final String description;

    HttpStatus(int code, String description) {
        this.code = code;
        this.description = description;



    }
}
