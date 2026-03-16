package com.teb.practice.mapper;

import com.teb.practice.bean.Skill;
import com.teb.practice.dto.SkillDto;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SkillMapper {

    @Named("skillToSkillDto")
    @Mapping(target = "skillName", source = "name")
    @Mapping(target = "skillExperienceInYears", source = "experienceInYears")
    SkillDto skillToSkillDto(Skill skill);

    @Named("skillDtoToSkill")
    @Mapping(target = "name", source = "skillName")
    @Mapping(target = "experienceInYears", source = "skillExperienceInYears")
    Skill skillDtoToSkill(SkillDto skillDto);

    @IterableMapping(qualifiedByName = "skillToSkillDto")
    List<SkillDto> skillsToSkillsDto(List<Skill> skills);

    @IterableMapping(qualifiedByName = "skillDtoToSkill")
    List<Skill> skillsDtoToSkills(List<SkillDto> skillsDto);
}
