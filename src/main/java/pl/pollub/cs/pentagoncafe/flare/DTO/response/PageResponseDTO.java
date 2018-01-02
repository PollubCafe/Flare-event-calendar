package pl.pollub.cs.pentagoncafe.flare.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PageResponseDTO {
    private int totalPages;
    private int currentPageNumber;
    private List content;
}
