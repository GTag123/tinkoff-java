package edu.project3.utils;

import java.time.OffsetDateTime;
import lombok.Builder;

@Builder
public record NginxLog(String remoteAddress,
                       String remoteUser,
                       OffsetDateTime timeLocal,
                       Request request,
                       Response response,
                       String referer) {
}
