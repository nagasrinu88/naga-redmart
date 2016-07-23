/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers.forms;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author NavNag
 */
public class LogForm {

    @NotNull
    @Length(max = 32)
    @JsonProperty("application_id")
    private String applicationId;
    @NotNull
    @Length(max = 256)
    @JsonProperty("logger")
    private String logger;
    @NotNull
    @Length(max = 256)
    @JsonProperty("level")
    private String level;
    @NotNull
    @Length(max = 2048)
    @JsonProperty("message")
    private String message;

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getLogger() {
        return logger;
    }

    public void setLogger(String logger) {
        this.logger = logger;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
