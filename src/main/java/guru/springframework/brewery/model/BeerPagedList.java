package guru.springframework.brewery.model;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class BeerPagedList extends PageImpl<BeerDto> {

	@JsonCreator(mode = Mode.PROPERTIES)
	public BeerPagedList(@JsonProperty("content")List<BeerDto> content, 
			@JsonProperty("number") int number,
			@JsonProperty("size") int size,
			@JsonProperty("totalElements") int totalElements,
			@JsonProperty("pageable") JsonNode pageable,
			@JsonProperty("last") boolean last,
			@JsonProperty("totalPages") int totalPages,
			@JsonProperty("sort") JsonNode sort,
			@JsonProperty("first") boolean first,
			@JsonProperty("numberOfElements") int numberOfElements) {
		super(content, PageRequest.of(number, size), totalElements);
	}

	public BeerPagedList(List<BeerDto> content) {
		super(content);
	}

	public BeerPagedList(List<BeerDto> list, PageRequest of, long totalElements) {
		super(list,of,totalElements);
	}

}
