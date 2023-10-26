package xyz.riocode.common.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.riocode.common.model.BeerDto;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    @Serial
    private static final long serialVersionUID = -6977273018284813967L;

    private BeerDto beerDto;

}
