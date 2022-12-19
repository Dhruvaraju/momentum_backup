package com.alpha.momentum.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "task", schema = "public")
public class Task {
    private Long id;
    private String name;
    private String description;
    private String taskType;
    private String status;
    private Date startTime;
    private Date completedTime;
    private Date createdDate;
    private UserStory userStory;
}
