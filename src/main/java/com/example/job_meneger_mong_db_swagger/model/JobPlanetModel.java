package com.example.job_meneger_mong_db_swagger.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection="ООО ТОСК")
public class JobPlanetModel {
    private String gobType;
    private String responsibleJob;
    private String responsibleSign;
    private String startDateWork;
    private String name;
    private String jobTitle;
    private String tabeleNum;
}
