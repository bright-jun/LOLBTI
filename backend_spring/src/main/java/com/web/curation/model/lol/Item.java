package com.web.curation.model.lol;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @NotNull
    private int itemId;
    @NotNull
    private String itemName;
    @NotNull
    private String itemIcon;
    @NotNull
    private int per;
}
