// 代码生成时间: 2025-09-17 22:13:12
 * It serves as a middleware to intercept and clean incoming requests.
 */
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XSSProtectionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Sanitize the parameters
        sanitizeParameters(req);

        // After sanitization, forward the request to the intended servlet
        req.getRequestDispatcher("/protectedResource").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Sanitize the parameters
        sanitizeParameters(req);

        // After sanitization, forward the request to the intended servlet
        req.getRequestDispatcher("/protectedResource\).forward(req, resp);
    }

    /**
     * Sanitizes the incoming request parameters to prevent XSS attacks.
     * @param req The HttpServletRequest object containing the request parameters.
     */
    private void sanitizeParameters(HttpServletRequest req) {
        String[] values = req.getParameterValues("param");
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                String sanitized = sanitizeInput(values[i]);
                req.getParameterMap().replace("param", sanitized);
            }
        }
    }

    /**
     * Sanitizes a single input string to remove any XSS threats.
     * @param input The input string to be sanitized.
     * @return The sanitized string.
     */
    private String sanitizeInput(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        // Remove script tags
        String noScript = input.replaceAll("<<.*?>>|<script.*?>.*?</script>|<[^>]+>