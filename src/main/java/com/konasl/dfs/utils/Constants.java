package com.konasl.dfs.utils;

public class Constants {
    public class Url {
        public static final String DMS = "http://qa-auth.dms.kpp.com:10900/authentication-service-provider-1.0/login";
    }

    public class UserName {
        public static final String SOE = "soe@nagad.com";
        public static final String TM = "tm@nagad.com";
    }

    public class Password {
        public static final String CommonPass = "1234";
    }

    public class UITitles {
        public static final String HomePageTitle = "নগদ | Login Page";
    }

    public class HeaderTitles {
        public static final String DSODetailsHeaderTitle = "DSO DETAILS";
        public static final String AgentDetailsHeaderTitle = "AGENT DETAILS";
        public static final String DHDetailsHeaderTitle = "DISTRIBUTOR DETAILS";
        public static final String SuperDHDetailsHeaderTitle = "SUPER DISTRIBUTOR DETAILS";
        public static final String MerchantDetailsHeaderTitle = "Merchant Details";
        public static final String HomeHeader = "নগদ";
    }

    public class ErrorMessages {
        public static final String LogInFailErrorMessage = "Invalid Username or Password. Please try again.";
    }

    public class AccountNumbers {
        public static final String DSOAccountNo = "01599992222";
        public static final String DHAccountNo = "01700000000";
        public static final String SuperDHAccountNo = "01512066700";
        public static final String AgentAccountNo = "01552008600";
        public static final String MerchantAccountNo = "01511516770";
    }

    public class DHType {
        public static final String SUPER = "SUPER";
        public static final String REGULAR = "REGULAR";
    }
}
