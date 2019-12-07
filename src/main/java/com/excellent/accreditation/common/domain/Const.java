package com.excellent.accreditation.common.domain;

public class Const {
    /**
     * @Description 用户角色
     * @Date 17:18 2019/11/18
     **/
    public static final String ADMIN = "admin";

    public static final String TEACHER = "teacher";

    public static final String STUDENT = "student";

    public static final String PASSWORD = "It's a secret!";


    /**
     * @Description Token
     * @Date 19:29 2019/12/3
     **/
    public static final String TOKEN = "Authentication";

    public static final String TOKEN_CACHE_PREFIX = "excellent.cache.token";

    /**
     * @Description:Excel操作结果状态
     * @Author: ashe
     * @Date: 2019/12/4
     */
    public static final Integer SUCCESS_INCREASE = 0;

    public static final Integer FAIL_INCREASE = 1;

    /**
     * @Description:数据库操作
     * @Author: ashe
     * @Date: 2019/12/6
     */

    public static final Integer CREATE = 0;

    public static final Integer UPDATE = 1;



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
