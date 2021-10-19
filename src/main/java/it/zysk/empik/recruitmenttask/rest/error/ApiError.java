package it.zysk.empik.recruitmenttask.rest.error;

import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.Map;

@Value
@Builder
public class ApiError {
    int httpStatusCode;
    String message;
    Map<String, List<String>> errors = Map.of();
}
