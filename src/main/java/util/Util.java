package util;

import org.apache.log4j.Logger;

public class Util {
    public static Logger logger = Logger.getLogger(Util.class);

    public void test(){
        logger.info("ceshi");
    }
    public void test3(){
        logger.error("ceshi");
    }
    public void test2(){
        logger.debug("ceshi");
    }

    public static void main(String[] args) {
        Util util = new Util();
        util.test();
        util.test2();
        util.test3();
    }
}
