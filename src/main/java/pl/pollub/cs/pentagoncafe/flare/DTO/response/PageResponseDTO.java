package pl.pollub.cs.pentagoncafe.flare.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class PageResponseDTO<T> {
    private int totalPages;
    private int currentPageNumber;
    @Singular("content")
    private List<T> content;
}
