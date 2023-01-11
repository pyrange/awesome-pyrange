package com.pyrange.awesome.util;

import com.google.common.base.CaseFormat;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonUtil {

    /**
     * 表名转小写逗号分隔
     *
     * @param tableName
     * @return 转换后的字符串
     */
    public static String str2LowercaseWithDot(String tableName) {
        if (isNullOrEmpty(tableName)) {
            return "";
        }
        // 表名下划线分割, 直接替换成小写逗号
        if (tableName.contains("_")) {
            return tableName.replace("_", ".").toLowerCase();
        }

        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, tableName)
                .replace("_", ".").toLowerCase();
    }

    /**
     * 表名转小写斜杆分隔
     *
     * @param tableName
     * @return 转换后的字符串
     */
    public static String str2LowercaseWithSlash(String tableName) {
        if (isNullOrEmpty(tableName)) {
            return "";
        }
        // 表名下划线分割, 直接替换成小写逗号
        if (tableName.contains("_")) {
            return tableName.replace("_", "/").toLowerCase();
        }

        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, tableName)
                .replace("_", "/").toLowerCase();
    }

    /**
     * 表名转小写
     *
     * @param tableName
     * @return 转换后的字符串
     */
    public static String getNameLowercase(String tableName) {
        if (isNullOrEmpty(tableName)) {
            return "";
        }
        return underScoreCaseToCamelCase(tableName).toLowerCase();
    }

    /**
     * 表名转首字母大写驼峰格式
     *
     * @param tableName
     * @return 转换后的字符串
     */
    public static String getNameUpperCamel(String tableName) {
        if (isNullOrEmpty(tableName)) {
            return "";
        }
        return toUpperCaseFirstOne(underScoreCaseToCamelCase(tableName));
    }

    /**
     * 表名转首字母小写驼峰格式
     *
     * @param tableName
     * @return 转换后的字符串
     */
    public static String getNameLowerCamel(String tableName) {
        if (isNullOrEmpty(tableName)) {
            return "";
        }
        return toLowerCaseFirstOne(underScoreCaseToCamelCase(tableName));
    }

    /**
     * 将字符串的第一位转为小写
     *
     * @param str 需要转换的字符串
     * @return 转换后的字符串
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
     * 将字符串的第一位转为大写
     *
     * @param str 需要转换的字符串
     * @return 转换后的字符串
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
     * 下划线命名转为驼峰命名
     *
     * @param str 下划线命名格式
     * @return 驼峰命名格式
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
     * 根据路径获取包名 默认Java后面跟的是包名
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
     * 对象是否为无效值
     *
     * @param obj 要判断的对象
     * @return 是否为有效值(不为null 和 ""字符串)
     */
    public static boolean isNullOrEmpty(Object obj) {
        return obj == null || "".equals(obj.toString());
    }

    private static final List importList = Arrays.asList("BigDecimal", "Date");

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
     * 获取表名的第一个单词
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
     *  指定目录用关键字查找文件
     * @param projectPath 要查找的目录
     * @param type 类型 1：查找文件 2：查找文件夹
     * @param keyWords 要查找的关键字
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
