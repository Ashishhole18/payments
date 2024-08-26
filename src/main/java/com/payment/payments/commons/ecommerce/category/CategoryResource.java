package com.payment.payments.commons.ecommerce.category;

import lombok.*;

@Data
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResource {
    private int categoryId;
    private String name;
}
