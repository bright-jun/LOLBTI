package com.web.curation.model.lol;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Champion {
    @NotNull
    private int championId;
    @NotNull
    private String chapionName;
    @NotNull
    private String championIcon;
    @NotNull
    private int per;
}
