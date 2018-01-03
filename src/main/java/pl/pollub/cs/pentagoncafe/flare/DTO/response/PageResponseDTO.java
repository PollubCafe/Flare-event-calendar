package pl.pollub.cs.pentagoncafe.flare.DTO.response;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@Builder
public class PageResponseDTO {
    private int totalPages;
    private int currentPageNumber;
    @Singular("content")
    private List content;
}
