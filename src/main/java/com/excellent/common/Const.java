package com.excellent.common;

public class Const {
    public static String CURRENT_USER = "current_user";

    public static String Teacher = "teacher";

    public static String Manager = "manage";

    public enum  BATCH_STUDENT_STATUS{

        /**
         * 学生考试状态
         */
        NOT_SIGN(0,"未签到"),
        HAD_SIGN(1,"已签到"),
        IN_PROGRESS(2,"正在考试"),
        FINISHED(3,"考试完成"),
        MISSING_TEST(4,"缺考"),
        GET_SCORE(5,"已出成绩")

        ;

        private int status;
        private String desc;

        BATCH_STUDENT_STATUS(int status, String desc) {
            this.status = status;
            this.desc = desc;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getStatus() {
            return status;
        }

        public String getDesc() {
            return desc;
        }
    }
}