package com.minhaz.bookrecommendation.util;

public class ApiConstant {

    public static final String SEPARATOR = "/";
    public static final String VERSION = "v1";
    public static final String OPEN_PARENTHESIS = "{";
    public static final String CLOSE_PARENTHESIS = "}";

    public static final String IDENTIFIER = SEPARATOR + OPEN_PARENTHESIS +  "id" + CLOSE_PARENTHESIS;

    public static final String ROOT_PATH = SEPARATOR + "api" + SEPARATOR + VERSION;



    public static class Auth{
        public static final String ROOT  = ROOT_PATH + SEPARATOR + "auth";
        public static final String CREATE  = SEPARATOR + "create";
        public static final String LOGIN  = SEPARATOR + "login";
        public static final String REFRESH_TOKEN  = SEPARATOR + "refresh";
        public static final String ASSIGN_ROLE  = SEPARATOR + "assign-role";

    }

    public static class SearchHistory{
        public static final String ROOT  = ROOT_PATH + SEPARATOR + "search-history";

    }

    public static class AppUser{
        public static final String ROOT  = ROOT_PATH + SEPARATOR + "user";

    }
}
