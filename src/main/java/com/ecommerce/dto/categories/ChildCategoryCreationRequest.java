package com.ecommerce.dto.categories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChildCategoryCreationRequest {
    private String name;
    private Long parentCategoryId;
}
