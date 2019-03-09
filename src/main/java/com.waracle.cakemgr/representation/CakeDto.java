package com.waracle.cakemgr.representation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;


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
