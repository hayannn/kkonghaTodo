package com.kkongha.todo.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddTodo {

    @NotEmpty(message = "제목은 필수입니다")
    private String title;

    @NotEmpty(message = "날짜를 선택해주세요.")
    private String dueDateStr;

    private String memo;

    public AddTodo() {
        // 기본 생성자
    }

    public AddTodo(TodoEntity todoEntity) {
        this.title = todoEntity.getTitle();
        this.dueDateStr = todoEntity.getDueDateStr();
        this.memo = todoEntity.getMemo();
    }

    public TodoEntity toEntity() throws ParseException {
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setTitle(this.title);

        // 날짜 변환
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dueDate = dateFormat.parse(this.dueDateStr);
        todoEntity.setDueDate(dueDate);

        todoEntity.setMemo(this.memo);
        return todoEntity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDueDateStr() {
        return dueDateStr;
    }

    public void setDueDateStr(String dueDateStr) {
        this.dueDateStr = dueDateStr;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
