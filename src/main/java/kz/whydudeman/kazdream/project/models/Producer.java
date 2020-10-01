package kz.whydudeman.kazdream.project.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Producer extends AbstractEntity {
    @NotNull
    private String name;

    private String description;
}
