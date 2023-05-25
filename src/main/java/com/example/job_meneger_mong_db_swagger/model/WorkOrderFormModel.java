package com.example.job_meneger_mong_db_swagger.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class WorkOrderFormModel {
    private String gobType;
    private String responsibleJob;
    private String responsibleSign;
    private String startDateWork;
}
