package com.fengquanwei.hello.tomcat.connector2.connector;

import com.fengquanwei.hello.tomcat.connector2.util.ResponseStream;
import com.fengquanwei.hello.tomcat.connector2.util.ResponseWriter;
import com.fengquanwei.hello.tomcat.constant.Constants;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;

/**
 * HTTP 响应
 *
 * @author fengquanwei
 * @create 12/09/2017 10:16 AM
 **/
public class HttpResponse implements HttpServletResponse {
    // ------------------------------ 变量 ------------------------------
    private static final int BUFFER_SIZE = 1024;

    protected final SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);

    HttpRequest request;
    OutputStream output;
    PrintWriter writer;

    protected ArrayList cookies = new ArrayList();
    protected HashMap headers = new HashMap();

    protected byte[] buffer = new byte[BUFFER_SIZE];
    protected int bufferCount = 0;
    protected boolean committed = false;
    protected int contentCount = 0;
    protected int contentLength = -1;
    protected String contentType = null;
    protected String encoding = null;
    protected String message = getStatusMessage(HttpServletResponse.SC_OK);
    protected int status = HttpServletResponse.SC_OK;

    // ------------------------------ 构造函数 ------------------------------

    public HttpResponse(OutputStream output) {
        this.output = output;
    }

    // ------------------------------ 公有方法 ------------------------------

    /**
     * 发送静态资源
     */
    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;
        try {
            File file = new File(Constants.WEB_ROOT, request.getRequestURI());
            fis = new FileInputStream(file);
            int ch = fis.read(bytes, 0, BUFFER_SIZE);
            while (ch != -1) {
                output.write(bytes, 0, ch);
                ch = fis.read(bytes, 0, BUFFER_SIZE);
            }
        } catch (FileNotFoundException e) {
            String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Conent-Length: 23\r\n" +
                    "\r\n" +
                    "<h1>File Not Found</h1>";
            output.write(errorMessage.getBytes());
        } finally {
            if (fis != null)
                fis.close();
        }
    }

    /**
     * call this method to send headers and response to the output
     */
    public void finishResponse() {
        if (writer != null) {
            writer.flush();
            writer.close();
        }
    }

    public void write(int b) throws IOException {
        if (bufferCount >= buffer.length) {
            flushBuffer();
        }
        buffer[bufferCount++] = (byte) b;
        contentCount++;
    }

    public void write(byte b[]) throws IOException {
        write(b, 0, b.length);
    }

    public void write(byte b[], int off, int len) throws IOException {
        // If the whole thing fits in the buffer, just put it there
        if (len == 0)
            return;
        if (len <= (buffer.length - bufferCount)) {
            System.arraycopy(b, off, buffer, bufferCount, len);
            bufferCount += len;
            contentCount += len;
            return;
        }

        // Flush the buffer and start writing full-buffer-size chunks
        flushBuffer();
        int iterations = len / buffer.length;
        int leftoverStart = iterations * buffer.length;
        int leftoverLen = len - leftoverStart;
        for (int i = 0; i < iterations; i++)
            write(b, off + (i * buffer.length), buffer.length);

        // Write the remainder (guaranteed to fit in the buffer)
        if (leftoverLen > 0)
            write(b, off + leftoverStart, leftoverLen);
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    public int getContentLength() {
        return contentLength;
    }

    protected String getProtocol() {
        return request.getProtocol();
    }

    public OutputStream getStream() {
        return this.output;
    }

    // ------------------------------ 私有方法 ------------------------------

    /**
     * 根据状态码反馈描述信息
     */
    private String getStatusMessage(int status) {
        switch (status) {
            case SC_OK:
                return ("OK");
            case SC_ACCEPTED:
                return ("Accepted");
            case SC_BAD_GATEWAY:
                return ("Bad Gateway");
            case SC_BAD_REQUEST:
                return ("Bad Request");
            case SC_CONFLICT:
                return ("Conflict");
            case SC_CONTINUE:
                return ("Continue");
            case SC_CREATED:
                return ("Created");
            case SC_EXPECTATION_FAILED:
                return ("Expectation Failed");
            case SC_FORBIDDEN:
                return ("Forbidden");
            case SC_GATEWAY_TIMEOUT:
                return ("Gateway Timeout");
            case SC_GONE:
                return ("Gone");
            case SC_HTTP_VERSION_NOT_SUPPORTED:
                return ("HTTP Version Not Supported");
            case SC_INTERNAL_SERVER_ERROR:
                return ("Internal Server Error");
            case SC_LENGTH_REQUIRED:
                return ("Length Required");
            case SC_METHOD_NOT_ALLOWED:
                return ("Method Not Allowed");
            case SC_MOVED_PERMANENTLY:
                return ("Moved Permanently");
            case SC_MOVED_TEMPORARILY:
                return ("Moved Temporarily");
            case SC_MULTIPLE_CHOICES:
                return ("Multiple Choices");
            case SC_NO_CONTENT:
                return ("No Content");
            case SC_NON_AUTHORITATIVE_INFORMATION:
                return ("Non-Authoritative Information");
            case SC_NOT_ACCEPTABLE:
                return ("Not Acceptable");
            case SC_NOT_FOUND:
                return ("Not Found");
            case SC_NOT_IMPLEMENTED:
                return ("Not Implemented");
            case SC_NOT_MODIFIED:
                return ("Not Modified");
            case SC_PARTIAL_CONTENT:
                return ("Partial Content");
            case SC_PAYMENT_REQUIRED:
                return ("Payment Required");
            case SC_PRECONDITION_FAILED:
                return ("Precondition Failed");
            case SC_PROXY_AUTHENTICATION_REQUIRED:
                return ("Proxy Authentication Required");
            case SC_REQUEST_ENTITY_TOO_LARGE:
                return ("Request Entity Too Large");
            case SC_REQUEST_TIMEOUT:
                return ("Request Timeout");
            case SC_REQUEST_URI_TOO_LONG:
                return ("Request URI Too Long");
            case SC_REQUESTED_RANGE_NOT_SATISFIABLE:
                return ("Requested Range Not Satisfiable");
            case SC_RESET_CONTENT:
                return ("Reset Content");
            case SC_SEE_OTHER:
                return ("See Other");
            case SC_SERVICE_UNAVAILABLE:
                return ("Service Unavailable");
            case SC_SWITCHING_PROTOCOLS:
                return ("Switching Protocols");
            case SC_UNAUTHORIZED:
                return ("Unauthorized");
            case SC_UNSUPPORTED_MEDIA_TYPE:
                return ("Unsupported Media Type");
            case SC_USE_PROXY:
                return ("Use Proxy");
            case 207:       // WebDAV
                return ("Multi-Status");
            case 422:       // WebDAV
                return ("Unprocessable Entity");
            case 423:       // WebDAV
                return ("Locked");
            case 507:       // WebDAV
                return ("Insufficient Storage");
            default:
                return ("HTTP Response Status " + status);
        }
    }

    // ------------------------------ 覆盖方法 ------------------------------
    @Override
    public String getContentType() {
        return this.contentType;
    }

    @Override
    public void addCookie(Cookie cookie) {
        if (isCommitted()) {
            return;
        }
        synchronized (cookies) {
            cookies.add(cookie);
        }
    }

    @Override
    public void setHeader(String name, String value) {
        if (isCommitted()) {
            return;
        }
        ArrayList values = new ArrayList();
        values.add(value);
        synchronized (headers) {
            headers.put(name, values);
        }
        String match = name.toLowerCase();
        if (match.equals("content-length")) {
            int contentLength = -1;
            try {
                contentLength = Integer.parseInt(value);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            if (contentLength >= 0) {
                setContentLength(contentLength);
            }
        } else if (match.equals("content-type")) {
            setContentType(value);
        }
    }

    @Override
    public void addHeader(String name, String value) {
        if (isCommitted()) {
            return;
        }
        synchronized (headers) {
            ArrayList values = (ArrayList) headers.get(name);
            if (values == null) {
                values = new ArrayList();
                headers.put(name, values);
            }
            values.add(value);
        }
    }

    @Override
    public void flushBuffer() throws IOException {
        if (bufferCount > 0) {
            try {
                output.write(buffer, 0, bufferCount);
            } finally {
                bufferCount = 0;
            }
        }
    }

    @Override
    public String getCharacterEncoding() {
        if (encoding == null) {
            return ("UTF-8");
        } else {
            return (encoding);
        }
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        ResponseStream newStream = new ResponseStream(this);
        newStream.setCommit(false);
        OutputStreamWriter osr = new OutputStreamWriter(newStream, getCharacterEncoding());
        writer = new ResponseWriter(osr);
        return writer;
    }

    @Override
    public boolean isCommitted() {
        return this.committed;
    }

    @Override
    public void setContentLength(int length) {
        if (isCommitted()) {
            return;
        }
        this.contentLength = length;
    }

    @Override
    public void setLocale(Locale locale) {
        if (isCommitted()) {
            return;
        }
        String language = locale.getLanguage();
        if ((language != null) && (language.length() > 0)) {
            String country = locale.getCountry();
            StringBuffer value = new StringBuffer(language);
            if ((country != null) && (country.length() > 0)) {
                value.append('-');
                value.append(country);
            }
            setHeader("Content-Language", value.toString());
        }
    }

    @Override
    public boolean containsHeader(String s) {
        return false;
    }

    @Override
    public String encodeURL(String s) {
        return null;
    }

    @Override
    public String encodeRedirectURL(String s) {
        return null;
    }

    @Override
    public String encodeUrl(String s) {
        return null;
    }

    @Override
    public String encodeRedirectUrl(String s) {
        return null;
    }

    @Override
    public void sendError(int i, String s) throws IOException {

    }

    @Override
    public void sendError(int i) throws IOException {

    }

    @Override
    public void sendRedirect(String s) throws IOException {

    }

    @Override
    public void setDateHeader(String s, long l) {

    }

    @Override
    public void addDateHeader(String s, long l) {

    }

    @Override
    public void setIntHeader(String s, int i) {

    }

    @Override
    public void addIntHeader(String s, int i) {

    }

    @Override
    public void setStatus(int i) {

    }

    @Override
    public void setStatus(int i, String s) {

    }

    @Override
    public int getStatus() {
        return 0;
    }

    @Override
    public String getHeader(String s) {
        return null;
    }

    @Override
    public Collection<String> getHeaders(String s) {
        return null;
    }

    @Override
    public Collection<String> getHeaderNames() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    @Override
    public void setCharacterEncoding(String s) {

    }

    @Override
    public void setContentType(String s) {

    }

    @Override
    public void setBufferSize(int i) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public void reset() {

    }

    @Override
    public Locale getLocale() {
        return null;
    }
}
