package pl.tsmiatacz.mockapi.data;

import java.time.LocalDateTime;

public record HistoryData(String endpoint, LocalDateTime timestamp, Object body) {

}
