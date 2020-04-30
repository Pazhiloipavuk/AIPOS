package lab3.model;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

    public static final String DESCRIPTION = "description";
    public static final String DESCRIPTION_ID = "id";
    public static final String DESCRIPTION_TITLE = "title";
    public static final String DESCRIPTION_CONTENT = "content";
    public static final String DESCRIPTION_TASK = "task";
    public static final String DESCRIPTION_AUTHOR = "author";
    public static final String DESCRIPTION_COMMENTS = "comments";

    public static final String COMMENT = "comment";
    public static final String COMMENT_ID = "id";
    public static final String COMMENT_CONTENT = "content";
    public static final String COMMENT_DESCRIPTION = "description";
    public static final String COMMENT_AUTHOR = "author";

    public static final String TASK = "task";
    public static final String TASK_ID = "id";
    public static final String TASK_NAME = "name";
    public static final String TASK_DESCRIPTION = "description";

    public static final String AUTHOR = "author";
    public static final String USER_ID = "id";
    public static final String USER_USERNAME = "username";
    public static final String USER_DESCRIPTIONS = "descriptions";
    public static final String USER_COMMENTS = "comments";

    private static final String PATH_DELIMITER = ".";

    public static String combine(String... paths) {
        return String.join(PATH_DELIMITER, paths);
    }
}

