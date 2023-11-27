package com.pyrange.awesome.util;

import java.io.*;

public class FileUtils {

    /**
     * 获取文件内容
     *
     * @param fileDir
     * @return
     */
    public static String readFile(String fileDir) {
        StringBuilder builder = new StringBuilder();
        InputStream resourceAsStream = new FileUtils().getClass().getResourceAsStream(fileDir);
        BufferedReader br = null;
        String line;
        try {
            br = new BufferedReader(new InputStreamReader(resourceAsStream, "UTF-8"));
            while ((line = br.readLine()) != null) {
                builder.append(line).append(System.getProperty("line.separator"));
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.toString();
    }
//    /**
//     * 获取文件内容
//     * @param fileDir
//     * @return
//     */
//    public static String readFile(String fileDir) {
//        String content = "";
//        StringBuilder builder = new StringBuilder();
//
//        File file = new File(fileName);
//        InputStreamReader streamReader = null;
//        try {
//            streamReader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
//            BufferedReader bufferedReader = new BufferedReader(streamReader);
//
//            while ((content = bufferedReader.readLine()) != null) {
//                builder.append(content);
//            }
//            System.out.println(builder.toString());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (streamReader != null) {
//                try {
//                    streamReader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return builder.toString();
//    }
}
