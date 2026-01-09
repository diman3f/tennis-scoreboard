package com.diman_3f.tennis_scoreboard.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter

public class NewMatchDto {
    private UUID uuid;
    private String nameOnePlayer;
    private String nameTwoPlayer;
    private boolean isValidDto;
    private Map<String, String> errors;

    @Builder
    public NewMatchDto(String nameOnePlayer, String nameTwoPlayer) {
        this.nameOnePlayer = nameOnePlayer;
        this.nameTwoPlayer = nameTwoPlayer;
        this.isValidDto = true;
        this.errors = new HashMap<>();

    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public void saveErrors(String name, String error) {
        isValidDto = false;
        errors.put(name, error);
    }

    public String getError(String nameError) {
        if (errors.get(nameError) == null) {
            return "";
        } else
            return errors.get(nameError);
    }
}
