package com.zwemmen.psv.api.security.writer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zwemmen.psv.api.message.AuthenticationSuccessResponse;
import com.zwemmen.psv.api.message.error.Error;
import com.zwemmen.psv.api.message.error.ErrorResponse;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Static class to write HTTP messages to the client from spring security handlers.
 *
 * @author afernandez
 */
public final class MessageWriter {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void sendError(HttpServletResponse response, Exception ex, int status, String message) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(status);

        PrintWriter writer = response.getWriter();
        Error error = new Error(message, ex.getMessage());
        writer.write(mapper.writeValueAsString(new ErrorResponse(status, error)));

        writer.flush();
        writer.close();
    }

    public static void sendResponse(HttpServletResponse response, int status, AuthenticationSuccessResponse message) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(status);

        PrintWriter writer = response.getWriter();
        writer.write(mapper.writeValueAsString(message));

        writer.flush();
        writer.close();
    }
}