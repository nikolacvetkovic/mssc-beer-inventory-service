package xyz.riocode.brewery.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeerDto implements Serializable {

    private static final long serialVersionUID = 7340279415383710020L;

    @Null
    private UUID id;
    @Null
    private Integer version;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
    @Null
    private OffsetDateTime createdDate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
    @Null
    private OffsetDateTime lastModifiedDate;
    @NotBlank
    private String beerName;
    @NotNull
    private BeerStyle beerStyle;
    @NotNull
    private String upc;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Positive
    @NotNull
    private BigDecimal price;
    private Integer quantityOnHand;

}
