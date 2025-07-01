package com.github.maximslepukhin.intershop.dto;

import com.github.maximslepukhin.intershop.enums.ActionType;
import lombok.Data;

@Data
public class ActionForm {
    private ActionType action;

    public ActionForm(ActionType action) {
        this.action = action;
    }
}
