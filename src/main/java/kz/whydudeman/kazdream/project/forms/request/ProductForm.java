package kz.whydudeman.kazdream.project.forms.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProductForm {
    @NotNull
    @NotEmpty
    public String name;

    @NotNull
    @NotEmpty
    public String description;

    public ProductForm(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
