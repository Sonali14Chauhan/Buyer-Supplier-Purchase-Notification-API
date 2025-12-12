package com.task.dto;

import lombok.*;

@Getter
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
public class ItemRequest {
    private Long productId;
    private Integer quantity;
}
