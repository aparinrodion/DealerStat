package org.leverx.dealerstat.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
@Builder
public class GameObjectsPaginationDto {
    private final Integer traderId;
    private final Integer gameId;
    private final Pageable pageable;
}
