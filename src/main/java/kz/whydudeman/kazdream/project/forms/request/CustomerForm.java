package kz.whydudeman.kazdream.project.forms.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CustomerForm {
    @NotNull
    @NotEmpty
    public String name;

    @NotNull
    @NotEmpty
    public String lastName;

    public CustomerForm(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }
}
