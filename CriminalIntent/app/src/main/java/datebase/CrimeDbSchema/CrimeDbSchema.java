package datebase.CrimeDbSchema;

/**
 * Created by Hydra on 2017/4/26.
 */

public class CrimeDbSchema {
    //内部类定义表？
    public static final class CrimeTable{
        public static final String NAME = "crimes";
        //定义数据表字段
        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";

        }
    }
}
