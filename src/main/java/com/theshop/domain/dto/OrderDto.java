package com.theshop.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto implements Serializable {

    private Long id;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate ordered;

    private String comments;

    private Long cartId;

    private Long userId;

    private BigDecimal totalValue;

    boolean isCompleted;
}
