import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import static util.ResponseAnalysis.*;
import static util.LogFileOperator.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainApplication {
    public static void main(String[] args){
        // 预先存储的文件路径
        String targetFile = "responseTarget.json";

        // 要对比的文件路径
        String toComparedFile = "responseCeshi.json";

        // 清除log文件内容
        clearFileContent();
        List<String> targetCardList = getCardList(targetFile);
        List<String> toComparedCardList = getCardList(toComparedFile);
        int repetitiveNums = 0;
        logger.info("------------------------------------------------");

        for (int i = 0; i < targetCardList.size(); i++){
            for (int j = 0; j < toComparedCardList.size(); j++){
                if (targetCardList.get(i).split(",")[0].equals(toComparedCardList.get(j).split(",")[0])){
                    repetitiveNums++;
                    logger.info("targetCardList 中的第 " + (i + 1) + " 条数据和 toComparedCardList 中的第 " + (j + 1) + " 条数据重复");
                }
            }
        }
        logger.info("<<<<<<<------总共有 " + repetitiveNums + " 数据重复------>>>>>>");

    }
}
