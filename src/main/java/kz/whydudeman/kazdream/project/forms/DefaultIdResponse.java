package kz.whydudeman.kazdream.project.forms;

import org.springframework.http.HttpStatus;


public class DefaultIdResponse {
    public int status;
    public String message;
    public Long id;

    public DefaultIdResponse() {
        this.status = HttpStatus.OK.value();
        this.message = "OK";
    }

    public DefaultIdResponse(HttpStatus status, Long id) {
        this.status = status.value();
        this.message = status.getReasonPhrase();
        this.id = id;
    }
}
