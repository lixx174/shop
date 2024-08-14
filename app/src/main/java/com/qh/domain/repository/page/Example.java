package com.qh.domain.repository.page;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.Objects;

/**
 * @author Jinx
 */
@Getter
@Setter
public class Example {
    private String column;
    private Object value;
    private Operation operation;

    public Example(String column, Object value, Operation operation) {
        Assert.hasText(column, "column must have value");
        Assert.notNull(operation, "operation must have value");

        this.column = column;
        this.value = value;
        this.operation = operation;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Example example = (Example) obj;
        return Objects.equals(column, example.column) &&
                Objects.equals(value, example.value) &&
                operation == example.operation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, value, operation);
    }
}
