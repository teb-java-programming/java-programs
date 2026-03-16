package com.teb.practice.bean;

import java.time.LocalDateTime;
import java.util.List;

public record Employee(
        String id, String name, LocalDateTime startDate, Access access, List<Skill> skills) {}
