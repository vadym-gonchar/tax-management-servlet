package gov.taxation.utils;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Custom tag for parsing LocalDateTime
 */
public class ParseLocalDateTimeTag extends SimpleTagSupport {
    private LocalDateTime value;

    public void setValue(LocalDateTime value) {
        this.value = value;
    }

    public void doTag() throws IOException {
        if (value != null) {
            JspWriter out = getJspContext().getOut();
            out.println(parse(value));
        }
    }

    private String parse(LocalDateTime value) {
        String[] date = value.toString().replace("T", "  ").split("\\.");
        return date[0];
    }
}

