package pl.tsmiatacz.mockapi.data;

import java.io.Serializable;

public record ErrorData(int statusCode, String message) implements Serializable {
    // intentionally left blank
}
