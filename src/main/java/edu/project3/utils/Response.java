package edu.project3.utils;

import lombok.Builder;

@Builder
public record Response(int statusCode, int bodyBytesSend) {

}
