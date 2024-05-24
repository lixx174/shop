package com.qh.application.model.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

/**
 * @author Jinx
 */
@Getter
@Setter
public class TestCommand {

    @JsonCreator
    public TestCommand(@JsonProperty("userId") String userId,
                       @JsonProperty("companyId") String companyId) {

        Assert.notNull(userId, "illegal userId");
        Assert.notNull(companyId, "illegal companyId");

        this.userId = userId;
        this.companyId = companyId;
    }


    private String userId;

    private String companyId;
}
