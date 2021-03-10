package util;

import com.jayway.jsonpath.JsonPath;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ResponseAnalysis extends Util{
    public static String readJsonFile(String fileName) {
        String filePath = "src" + File.separator + "main" + File.separator + "resources" + File.separator + fileName;
        String jsonStr = "";

        try {
            File jsonFile = new File(filePath);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer stringBuffer = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                stringBuffer.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = stringBuffer.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<String> getCardList(String fileName){
        // JsonPath路径根据json文件的实际内容编写
        final String infoIdJsonPath = "$.result.feedData.feedDataList[*].infoId";
        final String sourceTypeJsonPath = "$.result.feedData.feedDataList[*].sourceType";
        final String titleJsonPath = "$.result.feedData.feedDataList[*].title";
        final String localIdJsonPath = "$.result.feedData.feedDataList[*].localInfo.localId";
        final String localNameJsonPath = "$.result.feedData.feedDataList[*].localInfo.localName";
        final String pageIndexJsonPath = "$.result.feedData.pageIndex";

        String jsonStr = ResponseAnalysis.readJsonFile(fileName);
        List<String> targetCardList = new ArrayList();

        List<String> infoIdList = JsonPath.read(jsonStr, infoIdJsonPath);
        List<String> sourceTypeList = JsonPath.read(jsonStr, sourceTypeJsonPath);
        List<String> titleList = JsonPath.read(jsonStr, titleJsonPath);
        List<String> localIdList = JsonPath.read(jsonStr, localIdJsonPath);
        List<String> localNameList = JsonPath.read(jsonStr, localNameJsonPath);
        String pageNum = JsonPath.read(jsonStr, pageIndexJsonPath);
        int cardNums = infoIdList.size();

        logger.info("这是第 " + pageNum + " 页的数据，共有 " + cardNums + " 条数据");

        for (int i = 0; i < infoIdList.size(); i++){
            targetCardList.add(infoIdList.get(i) + ", " + sourceTypeList.get(i) + ", " + localIdList.get(i) + ", " +
                    localNameList.get(i) + ", " + titleList.get(i));
        }
        logger.info("获取到的卡片是 " + targetCardList.toString());
        //        System.out.println(sourceTypeList);
        //        System.out.println(titleList);
        //        System.out.println(localIdList);
        //        System.out.println(localNameList);
        //        String teacher1 = JsonPath.parse(jsonStr).read(pageIndexJsonPath);
        //        System.out.println(targetCardList.get(0));
        //        System.out.println(targetCardList.get(1));
        return targetCardList;
    }
    public static void main(String[] args) {
        String preFile = "responseTarget.json";

        String str = readJsonFile(preFile);
        List<String> targetCardList = getCardList(preFile);
        logger.info(targetCardList.get(0));

    }
}
