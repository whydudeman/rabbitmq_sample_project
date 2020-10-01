package kz.whydudeman.kazdream.project.forms.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProducerForm {
    @NotNull
    @NotEmpty
    public String name;

    @NotNull
    @NotEmpty
    public String description;

    public ProducerForm(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
