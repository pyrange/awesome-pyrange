package com.pyrange.awesome.util;

import com.intellij.openapi.diagnostic.Logger;
import com.pyrange.awesome.model.ConfigModel;
import com.pyrange.awesome.model.GenerateInfo;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FreeMarkUtil {
    private static final Logger LOGGER = Logger.getInstance(FreeMarkUtil.class);

    public static void generateFile(Map<String, Object> root, String templateName, String fileDir, String fileName) throws Exception {
        Configuration conf = new Configuration();
        conf.setDefaultEncoding("UTF-8");
        Template template = null;
        TemplateLoader templateLoader = new ClassTemplateLoader(FreeMarkUtil.class, "/template");
        conf.setTemplateLoader(templateLoader);
        template = conf.getTemplate(templateName, "UTF-8");

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


    public static String getFileStr(ConfigModel configModel, GenerateInfo generateInfo, String templateName) throws Exception {
        Map<String, Object> root = new HashMap<>(2);
        root.put("generateInfo", generateInfo);
        root.put("configModel", configModel);

        Configuration conf = new Configuration();
        conf.setDefaultEncoding("UTF-8");
        TemplateLoader templateLoader = new ClassTemplateLoader(FreeMarkUtil.class, "/template");
        conf.setTemplateLoader(templateLoader);
        Template template = conf.getTemplate(templateName, "UTF-8");

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
