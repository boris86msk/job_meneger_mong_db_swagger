package com.example.job_meneger_mong_db_swagger.controller;

import com.example.job_meneger_mong_db_swagger.model.EmployeeModel;
import com.example.job_meneger_mong_db_swagger.model.JobPlanetModel;
import com.example.job_meneger_mong_db_swagger.model.WorkOrderFormModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name="users", description = "API для управления заявками/нарядами")
public class JobController {
    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping("/save")
    @Operation(summary = "Сохранение документов")
    @Parameter(name = "doc", description = "Название документа", example = "Обслуживание 03-05-2023")
    public String saveDB(@RequestBody JobPlanetModel jp, @RequestParam String doc) {
        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setName(jp.getName());
        employeeModel.setJobTitle(jp.getJobTitle());
        employeeModel.setTabeleNum(jp.getTabeleNum());

        WorkOrderFormModel workOrderFormModel = new WorkOrderFormModel();
        workOrderFormModel.setResponsibleJob(jp.getResponsibleJob());
        workOrderFormModel.setStartDateWork(jp.getStartDateWork());
        workOrderFormModel.setGobType(jp.getGobType());
        workOrderFormModel.setResponsibleSign(jp.getResponsibleSign());

        mongoTemplate.save(jp);
        mongoTemplate.save(employeeModel, doc);
        mongoTemplate.save(workOrderFormModel, doc);

        return "Ok";
    }

    @GetMapping("/fin_job")
    @Operation(summary = "Поиск документа")
    @Parameter(name = "nameEmployeeSign", description = "Имя проверяющего", example = "Иванов И.И.")
    @Parameter(name = "gobType", description = "Вид работ", example = "Гарантийное обслуживание")
    public List<JobPlanetModel> findJob(@RequestParam String nameEmployeeSign, @RequestParam String gobType) {
        Query query = new Query(Criteria.where("responsibleSign")
                .is(nameEmployeeSign)
                .and("gobType")
                .is(gobType));
        List<JobPlanetModel> jobPlanetModels = mongoTemplate.find(query, JobPlanetModel.class);

        return jobPlanetModels;
    }

}
