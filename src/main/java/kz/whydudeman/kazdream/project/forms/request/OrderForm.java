package kz.whydudeman.kazdream.project.forms.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class OrderForm {
    @NotNull
    public Long customerId;

    @NotNull
    public Long productId;

    public OrderForm(Long customerId, Long productId) {
        this.customerId = customerId;
        this.productId = productId;
    }
}
