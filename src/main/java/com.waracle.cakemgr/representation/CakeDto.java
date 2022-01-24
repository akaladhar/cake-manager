package com.waracle.cakemgr.representation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CakeDto {

    private String title;
    @JsonProperty("desc")
    private String description;
    private String image;


}
