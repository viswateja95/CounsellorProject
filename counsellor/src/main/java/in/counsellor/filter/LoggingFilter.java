package in.counsellor.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Component
@Slf4j
public class LoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                   HttpServletResponse response,
                                   FilterChain filterChain) throws ServletException, IOException {

        String requestId = UUID.randomUUID().toString();

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        long startTime = System.currentTimeMillis();

        try {
            logRequest(requestId, wrappedRequest);
            filterChain.doFilter(wrappedRequest, wrappedResponse);
            logResponse(requestId, wrappedResponse, System.currentTimeMillis() - startTime);
        } finally {
            wrappedResponse.copyBodyToResponse();
        }
    }

    private void logRequest(String requestId, ContentCachingRequestWrapper request) throws UnsupportedEncodingException {
        String method = request.getMethod();
        String path = request.getRequestURI();
        String queryString = request.getQueryString();
        String body = getRequestBody(request);

        log.info("[{}] {} {} {} | Body: {}",
                requestId, method, path, queryString != null ? "?" + queryString : "", body);
    }

    private void logResponse(String requestId, ContentCachingResponseWrapper response, long duration) throws UnsupportedEncodingException {
        int status = response.getStatus();
        String body = getResponseBody(response);

        log.info("[{}] Response Status: {} | Duration: {}ms | Body: {}",
                requestId, status, duration, body);
    }

    private String getRequestBody(ContentCachingRequestWrapper request) throws UnsupportedEncodingException {
        byte[] content = request.getContentAsByteArray();
        if (content.length == 0) return "";
        return new String(content, request.getCharacterEncoding() != null ? request.getCharacterEncoding() : "UTF-8");
    }

    private String getResponseBody(ContentCachingResponseWrapper response) throws UnsupportedEncodingException {
        byte[] content = response.getContentAsByteArray();
        if (content.length == 0) return "";
        return new String(content, response.getCharacterEncoding() != null ? response.getCharacterEncoding() : "UTF-8");
    }
}