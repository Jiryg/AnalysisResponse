package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogFileOperator extends Util{

    public static void clearFileContent(){
        String directoryPath = "logs" + File.separator;
        File directory = new File(directoryPath);
        if (directory.exists()){
            String[] fileList = directory.list();
            for (String fileName : fileList){
                String filePath = "logs" + File.separator + fileName;
                File file = new File(filePath);
                try {
                    if (!file.exists()){
                        file.createNewFile();
                        logger.info("创建新文件: " + fileName);
                    } else {
                        FileWriter fileWriter = new FileWriter(filePath);
                        fileWriter.write("");
                        fileWriter.flush();
                        fileWriter.close();
                        logger.info("清除文件 " + fileName + " 的内容");
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
