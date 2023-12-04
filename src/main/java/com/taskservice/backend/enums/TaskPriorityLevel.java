package com.taskservice.backend.enums;

public enum TaskPriorityLevel {
    LOW(0), MEDIUM(1), HIGH(2);
    private int level;
    private TaskPriorityLevel(Integer level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
