package edu.project3.utils;

import lombok.Builder;

@Builder
public record Request(String type, String resource, String protocolVersion, String userAgent) {

}
