package guru.springframework.msscbeerservice.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import guru.springframework.msscbeerservice.domain.BeerStyle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeerDto implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Null
	private UUID id;
	@Null
	private Integer version;
	@Null
	@JsonFormat(pattern = "yyyy-mm-dd'T'HH:mm:ssZ", shape = Shape.STRING)
	private OffsetDateTime createdDate;
	@Null
	@JsonFormat(pattern = "yyyy-mm-dd'T'HH:mm:ssZ", shape = Shape.STRING)
	private OffsetDateTime lastModifiedDate;
	@NotBlank
	private String beerName;
	@NotNull
	private String upc;
	@NotNull
	@Positive
	@JsonFormat(shape = Shape.STRING)
	private BigDecimal price;
	private int quantityOnHand;
	@NotNull
    private BeerStyle beerStyle;
	private int quantityToBrew;
}
