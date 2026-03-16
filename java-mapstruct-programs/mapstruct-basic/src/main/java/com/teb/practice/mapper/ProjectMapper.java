package com.teb.practice.mapper;

import com.teb.practice.bean.Project;
import com.teb.practice.dto.ProjectDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProjectMapper {

    @Mapping(
            target = "projectId",
            source = "id",
            defaultExpression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "projectName", source = "name")
    @Mapping(target = "projectStartDate", source = "startDate", dateFormat = "dd-MM-yyyy HH:mm:ss")
    ProjectDto projectToProjectDto(Project project);

    @Mapping(target = "id", source = "projectId")
    @Mapping(target = "name", source = "projectName")
    @Mapping(target = "startDate", source = "projectStartDate", dateFormat = "dd-MM-yyyy HH:mm:ss")
    Project projectDtotoProject(ProjectDto projectDto);
}
