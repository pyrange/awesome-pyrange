package com.pyrange.awesome.util;

import com.intellij.openapi.diagnostic.Logger;
import com.pyrange.awesome.model.BasicConfig;
import com.pyrange.awesome.model.ConfigModel;
import com.pyrange.awesome.model.GenerateInfo;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.StringTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FreeMarkUtil {
    private static final Logger LOGGER = Logger.getInstance(FreeMarkUtil.class);

    public static void generateFile(Map<String, Object> root, String templateName, String fileDir, String fileName) throws Exception {
        Configuration conf = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
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


    public static String getFileStr(BasicConfig basicConfig, ConfigModel configModel, GenerateInfo generateInfo, String templateName) throws Exception {
        Map<String, Object> root = new HashMap<>(3);
        root.put("generateInfo", generateInfo);
        root.put("basicConfig", basicConfig);
        root.put("configModel", configModel);

        Configuration conf = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
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

    private Template createTemplate(String name, String templateContent) throws IOException {
        Configuration conf = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        conf.clearTemplateCache();
        Template template = conf.getTemplate(name, "utf-8");

        if (template != null) {
            return template;
        }
        synchronized (this) { // 以下操作不是线程安全，要加上同步
            // 获取模板加载器
            TemplateLoader templateLoader = conf.getTemplateLoader();
            if (templateLoader != null && templateLoader instanceof StringTemplateLoader) {
                // 如果加载器已经是字符串加载器，则在原来的加载器上put一个新的模板
                ((StringTemplateLoader) templateLoader).putTemplate(name, templateContent);
                conf.setTemplateLoader(templateLoader);
            } else {
                // 如果原来的模板加载器不是字符串的（默认是文件加载器），则新建
                StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
                stringTemplateLoader.putTemplate(name, templateContent);
                conf.setTemplateLoader(stringTemplateLoader);
            }
            // 这里要清一下缓存，不然下面可能会获取不到模板
            conf.clearTemplateCache();
            template = conf.getTemplate(name, "utf-8");

            return template;
        }
    }

    public static String getTemplateStr() {

        return "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
    }

}
