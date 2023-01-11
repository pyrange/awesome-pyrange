package com.pyrange.awesome.util;

import com.intellij.openapi.diagnostic.Logger;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.Map;

public class FreeMarkUtil {
    private static final Logger LOGGER = Logger.getInstance(FreeMarkUtil.class);

    public static void generateFile(Map<String, Object> root, String templateName, String fileDir, String fileName) throws Exception {
        Configuration conf = new Configuration();
        conf.setDefaultEncoding("UTF-8");
        Template template = null;
//        try {
        TemplateLoader templateLoader = new ClassTemplateLoader(FreeMarkUtil.class, "/template");
        conf.setTemplateLoader(templateLoader);
        template = conf.getTemplate(templateName, "UTF-8");
//        } catch (Exception e) {
//            LOGGER.info(e);
//            throw new MyException("Template not found!");
//        }

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

}
