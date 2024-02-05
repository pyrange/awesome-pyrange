package com.inbyte.cg.util;

import com.inbyte.cg.model.BasicConfig;
import com.inbyte.cg.model.ConfigModel;
import com.inbyte.cg.model.GenerateInfo;
import com.intellij.openapi.diagnostic.Logger;
import freemarker.template.Template;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FreeMarkUtil {
    private static final Logger LOGGER = Logger.getInstance(FreeMarkUtil.class);

//    /**
//     * 
//     *
//     * @param root
//     * @param templateName
//     * @param fileDir
//     * @param fileName
//     * @throws Exception
//     */
//    public static void generateFile(Map<String, Object> root, String templateName, String fileDir, String fileName) throws Exception {
//        Configuration conf = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
//        conf.setDefaultEncoding("UTF-8");
//        Template template = null;
//        TemplateLoader templateLoader = new ClassTemplateLoader(FreeMarkUtil.class, "/template");
//        conf.setTemplateLoader(templateLoader);
//        template = conf.getTemplate(templateName, "UTF-8");
//
//        if (!fileDir.endsWith("/")) {
//            fileDir = fileDir + '/';
//        }
//        File file = new File(fileDir + fileName);
//        if (!file.getParentFile().exists()) {
//            file.getParentFile().mkdirs();
//        }
//        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
//        template.process(root, out);
//        out.flush();
//        out.close();
//    }


    /**
     * 
     *
     * @param root
     * @param templateName
     * @param fileDir
     * @param fileName
     * @throws Exception
     */
    public static void generateFileByTemplateContent(Map<String, Object> root, String templateName,
                                                     String fileDir, String fileName) throws Exception {
        Template template = TemplateUtil.getTemplate(templateName);
        if (!fileDir.endsWith("/")) {
            fileDir = fileDir + '/';
        }
        File file = new File(fileDir + fileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
        template.process(root, out);
        out.flush();
        out.close();
    }

    public static String getFileStr(BasicConfig basicConfig, ConfigModel configModel, GenerateInfo generateInfo, String templateName) throws Exception {
        Map<String, Object> root = new HashMap<>(3);
        root.put("generateInfo", generateInfo);
        root.put("basicConfig", basicConfig);
        root.put("configModel", configModel);

        Template template = TemplateUtil.getTemplate(templateName);

        StringWriter stringWriter = new StringWriter();
        BufferedWriter writer = new BufferedWriter(stringWriter);
        template.process(root, writer);
        StringReader reader = new StringReader(stringWriter.toString());
        writer.flush();
        writer.close();
        BufferedReader r = new BufferedReader(reader);
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = r.readLine()) != null) {
            sb.append(line);
            sb.append("\r\n");
        }
        return sb.toString();
    }


}
