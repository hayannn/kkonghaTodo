package com.kkongha.todo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@Data
@Setter
@Table(name = "todo_list")
@NoArgsConstructor
@AllArgsConstructor
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "dueDate")
    private Date dueDate;
    @Transient
    private String dueDateStr;

    @Column(name = "completed")
    private boolean completed;

    @Column(name = "memo")
    private String memo;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @Column(name = "deleted_at")
    private String deletedAt;

    public String getDueDateStr() {
        return dueDateStr;
    }
    public void setDueDateStr(String dueDateStr) {
        this.dueDateStr = dueDateStr;
    }
}