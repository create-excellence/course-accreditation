package com.excellent.accreditation.common.domain;

public class Const {
    /**
     * @Author 安羽兮
     * @Description 用户角色
     * @Date 17:18 2019/11/18
     * @Param
     * @Return
     **/
    public static final String ADMIN = "admin";

    public static final String TEACHER = "teacher";

    public static final String STUDENT = "student";

    public static final String PASSWORD = "It's a secret!";

    /**
     * @Author 安羽兮
     * @Description Token
     * @Date 19:29 2019/12/3
     **/
    public static final String TOKEN = "Authentication";

    public static final String TOKEN_CACHE_PREFIX = "excellent.cache.token";


    public enum BATCH_STUDENT_STATUS {

        /**
         * 学生考试状态
         */
        NOT_SIGN(0, "未签到"),
        HAD_SIGN(1, "已签到"),
        IN_PROGRESS(2, "正在考试"),
        FINISHED(3, "考试完成"),
        MISSING_TEST(4, "缺考"),
        GET_SCORE(5, "已出成绩");

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
