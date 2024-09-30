package repository;

import org.junit.jupiter.api.Test;

import static com.planningpoker.model.Issues.QIssue.issue;
import static com.planningpoker.repository.utility.QueryUtils.parseFieldName;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueryUtilsTest {

    @Test
    public void parseFieldName_ShouldReturnQueryFieldName() {
        String parsedFieldName1 = parseFieldName(issue.issueId);
        assertEquals("issueId", parsedFieldName1);
        String parsedFieldName2 = parseFieldName(issue.pointEstimate);
        assertEquals("pointEstimate", parsedFieldName2);
    }
}
