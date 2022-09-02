package pl.tsmiatacz.mockerror.data;

import java.io.Serializable;

public record ResponseData(int statusCode, String message) implements Serializable {
    // intentionally left blank
}
