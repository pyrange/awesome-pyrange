package com.inbyte.cg.util;

import com.google.common.base.CaseFormat;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonUtil {

    /**
     * 
     *
     * @param tableName
     * @return 
     */
    public static String str2LowercaseWithDot(String tableName) {
        if (isNullOrEmpty(tableName)) {
            return "";
        }
        // , 
        if (tableName.contains("_")) {
            return tableName.replace("_", ".").toLowerCase();
        }

        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, tableName)
                .replace("_", ".").toLowerCase();
    }

    /**
     * 
     *
     * @param tableName
     * @return 
     */
    public static String str2LowercaseWithSlash(String tableName) {
        if (isNullOrEmpty(tableName)) {
            return "";
        }
        // , 
        if (tableName.contains("_")) {
            return tableName.replace("_", "/").toLowerCase();
        }

        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, tableName)
                .replace("_", "/").toLowerCase();
    }

    /**
     *  
     *
     * @param tableName
     * @return 
     */
    public static String str2LowercaseWithHyphen(String tableName) {
        if (isNullOrEmpty(tableName)) {
            return "";
        }
        // , 
        if (tableName.contains("_")) {
            return tableName.replace("_", "-").toLowerCase();
        }

        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, tableName)
                .replace("_", "-").toLowerCase();
    }

    /**
     *  
     *
     * @param tableName
     * @return 
     */
    public static String str2LowercaseWithUnderscore(String tableName) {
        if (isNullOrEmpty(tableName)) {
            return "";
        }

        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, tableName).toLowerCase();
    }

    /**
     * 
     *
     * @param tableName
     * @return 
     */
    public static String getNameLowercase(String tableName) {
        if (isNullOrEmpty(tableName)) {
            return "";
        }
        return underScoreCaseToCamelCase(tableName).toLowerCase();
    }

    /**
     * 
     *
     * @param tableName
     * @return 
     */
    public static String getNameUpperCamel(String tableName) {
        if (isNullOrEmpty(tableName)) {
            return "";
        }
        return toUpperCaseFirstOne(underScoreCaseToCamelCase(tableName));
    }

    /**
     * 
     *
     * @param tableName
     * @return 
     */
    public static String getNameLowerCamel(String tableName) {
        if (isNullOrEmpty(tableName)) {
            return "";
        }
        return toLowerCaseFirstOne(underScoreCaseToCamelCase(tableName));
    }

    /**
     * 
     *
     * @param str 
     * @return 
     */
    private static String toLowerCaseFirstOne(String str) {
        if (Character.isLowerCase(str.charAt(0))) {
            return str;
        } else {
            char[] chars = str.toCharArray();
            chars[0] = Character.toLowerCase(chars[0]);
            return new String(chars);
        }
    }

    /**
     * 
     *
     * @param str 
     * @return 
     */
    private static String toUpperCaseFirstOne(String str) {
        if (Character.isUpperCase(str.charAt(0))) {
            return str;
        } else {
            char[] chars = str.toCharArray();
            chars[0] = Character.toUpperCase(chars[0]);
            return new String(chars);
        }
    }

    /**
     * 
     *
     * @param str 
     * @return 
     */
    private static String underScoreCaseToCamelCase(String str) {
        if (!str.contains("_")) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        char[] chars = str.toCharArray();
        boolean flag = false;
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (ch == '_') {
                flag = true;
            } else {
                if (flag) {
                    sb.append(Character.toUpperCase(ch));
                    flag = false;
                } else {
                    sb.append(ch);
                }
            }
        }
        return sb.toString();
    }

    /**
     *  Java
     * @param path
     * @return
     */
    public static String getPackageNameByPath(String path) {
        path = path.replace("\\","/");
        if (path.contains("/java/")) {
            String[] split = path.split("/java/");
            String packagePath = split[split.length - 1];
            if (packagePath.endsWith("/")) {
                packagePath = packagePath.substring(0, packagePath.length() - 1);
            }
            return packagePath.replaceAll("/", ".");
        }else if(path.contains("/src/")){
            String[] split = path.split("/src/");
            String packagePath = split[split.length - 1];
            if (packagePath.endsWith("/")) {
                packagePath = packagePath.substring(0, packagePath.length() - 1);
            }
            return packagePath.replaceAll("/", ".");
        }
        return "Not Found Package! Please add by your self";
    }

    /**
     * 
     *
     * @param obj 
     * @return (null  "")
     */
    public static boolean isNullOrEmpty(Object obj) {
        return obj == null || "".equals(obj.toString());
    }

    private static final List importList = Arrays.asList("BigDecimal", "Date", "LocalDateTime", "LocalDate", "LocalTime");

    public static boolean isNeedImport(String type) {
        return importList.contains(type);
    }

    public static String getBaseRequestMapping(String tableName) {
        if (isNullOrEmpty(tableName)) {
            return "";
        }
        if (!tableName.contains("_")) {
            return tableName;
        }
        return tableName.replace("_","/");
    }

    /**
     * 
     * @param tableName
     * @return
     */
    public static String getSign(String tableName) {
        if (isNullOrEmpty(tableName)) {
            return "";
        }
        if (!tableName.contains("_")) {
            return tableName;
        }
        String sign = tableName.split("_")[0];
        return toLowerCaseFirstOne(sign);
    }

    public static String fomatPath(String path) {
        if(isNullOrEmpty(path)){
            return "";
        }
        if(!path.endsWith("/")){
            path = path+ '/';
        }
        return path.replace("\\","/");
    }


    /**
     *  
     * @param projectPath 
     * @param type  1: 2:
     * @param keyWords 
     */
    public static List<File> searchFiles(String projectPath, int type, String keyWords){
        File fileDir = new File(projectPath);
        File[] files = fileDir.listFiles();
        if(files == null || files.length == 0){
            return new ArrayList<>();
        }
        List<File> fileResult = new ArrayList<>();
        for (File file: files) {
            if(file.isDirectory()){
                if(type == 2 && file.getName().equals(keyWords) &&
                        !file.getAbsolutePath().matches(".*?target.*")){
                    fileResult.add(file);
                }
                fileResult.addAll(searchFiles(file.getAbsolutePath(), type, keyWords));
            }
            if(type == 1 && file.isFile() && file.getName().equals(keyWords)){
                fileResult.add(file);
            }
        }
        return fileResult;
    }

    public static List<File> searchDirectory(String projectPath, String key){
        return searchFiles(projectPath, 2, key);
    }

}
