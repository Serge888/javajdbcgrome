package jdbc.lesson4.hw.model;

import jdbc.lesson4.hw.enums.OperationType;
import jdbc.lesson4.hw.enums.Status;

public class History {
    private Long id;
    private OperationType operationType;
    private Long timeProcessed;
    Status status;

    public History(Long id, OperationType operationType, Long timeProcessed, Status status) {
        this.id = id;
        this.operationType = operationType;
        this.timeProcessed = timeProcessed;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public Long getTimeProcessed() {
        return timeProcessed;
    }

    public void setTimeProcessed(Long timeProcessed) {
        this.timeProcessed = timeProcessed;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", operationType=" + operationType +
                ", timeProcessed=" + timeProcessed +
                ", status=" + status +
                '}';
    }
}
