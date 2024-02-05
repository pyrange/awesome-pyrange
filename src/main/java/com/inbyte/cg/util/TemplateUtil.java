package com.inbyte.cg.util;

import com.inbyte.cg.PyrangeException;
import com.inbyte.cg.model.BasicConfig;
import com.inbyte.cg.model.Result;
import groovy.util.logging.Slf4j;

import com.google.common.base.Throwables;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.diagnostic.Logger;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 
 *
 * @author chenjw
 * @date 2023-2-3
 */
@Slf4j
public class TemplateUtil {

    private static final Logger log = Logger.getInstance(TemplateUtil.class);

    public static final String PYRANGE_TEMPLATE_PREFIX = "Pyrange-TEMPLATE-STORAGE-";
    public static final List<String> PYRANGE_TEMPLATE_LIST = new ArrayList();
    static {
        PYRANGE_TEMPLATE_LIST.add("controller.ftl");
        PYRANGE_TEMPLATE_LIST.add("service.ftl");
        PYRANGE_TEMPLATE_LIST.add("service-impl.ftl");
        PYRANGE_TEMPLATE_LIST.add("mapper.ftl");
        PYRANGE_TEMPLATE_LIST.add("mapperxml.ftl");
        PYRANGE_TEMPLATE_LIST.add("model/insert.ftl");
        PYRANGE_TEMPLATE_LIST.add("model/update.ftl");
        PYRANGE_TEMPLATE_LIST.add("model/po.ftl");
        PYRANGE_TEMPLATE_LIST.add("model/query.ftl");
        PYRANGE_TEMPLATE_LIST.add("model/brief.ftl");
        PYRANGE_TEMPLATE_LIST.add("model/detail.ftl");
        PYRANGE_TEMPLATE_LIST.add("fe/index.ftl");
        PYRANGE_TEMPLATE_LIST.add("fe/addDrawer.ftl");
        PYRANGE_TEMPLATE_LIST.add("fe/editDialog.ftl");
        PYRANGE_TEMPLATE_LIST.add("fe/detailDialog.ftl");
        PYRANGE_TEMPLATE_LIST.add("test.ftl");
        PYRANGE_TEMPLATE_LIST.add("test-constant.ftl");
    }

    /**
     * 
     *
     * @param templateCollectionName
     * @param templateName
     */
    public static void saveTemplate(String templateCollectionName, String templateName, String templateContent) {
        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();
        propertiesComponent.setValue(PYRANGE_TEMPLATE_PREFIX + templateCollectionName + "-" + templateName,
                templateContent);
        log.info("template collection:" + templateName + "saved success");
        log.info("template collection: {} saved success" + templateName);
    }

    /**
     * 
     *
     * @param templateCollectionName
     */
    public static void initialDefaultTemplate(String templateCollectionName) {
        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();
        for (String template: PYRANGE_TEMPLATE_LIST) {
            propertiesComponent.unsetValue(PYRANGE_TEMPLATE_PREFIX + templateCollectionName + "-" + template);
        }
//        propertiesComponent.unsetValue(PYRANGE_TEMPLATE_PREFIX + templateCollectionName + "-controller.ftl");
//        propertiesComponent.unsetValue(PYRANGE_TEMPLATE_PREFIX + templateCollectionName + "-service.ftl");
//        propertiesComponent.unsetValue(PYRANGE_TEMPLATE_PREFIX + templateCollectionName + "-service-impl.ftl");
//        propertiesComponent.unsetValue(PYRANGE_TEMPLATE_PREFIX + templateCollectionName + "-mapper.ftl");
//        propertiesComponent.unsetValue(PYRANGE_TEMPLATE_PREFIX + templateCollectionName + "-mapperxml.ftl");
//        propertiesComponent.unsetValue(PYRANGE_TEMPLATE_PREFIX + templateCollectionName + "-model/insert.ftl");
//        propertiesComponent.unsetValue(PYRANGE_TEMPLATE_PREFIX + templateCollectionName + "-model/update.ftl");
//        propertiesComponent.unsetValue(PYRANGE_TEMPLATE_PREFIX + templateCollectionName + "-model/po.ftl");
//        propertiesComponent.unsetValue(PYRANGE_TEMPLATE_PREFIX + templateCollectionName + "-model/query.ftl");
//        propertiesComponent.unsetValue(PYRANGE_TEMPLATE_PREFIX + templateCollectionName + "-model/brief.ftl");
//        propertiesComponent.unsetValue(PYRANGE_TEMPLATE_PREFIX + templateCollectionName + "-model/detail.ftl");
//        propertiesComponent.unsetValue(PYRANGE_TEMPLATE_PREFIX + templateCollectionName + "-fe/index.ftl");
//        propertiesComponent.unsetValue(PYRANGE_TEMPLATE_PREFIX + templateCollectionName + "-fe/editDialog.ftl");
//        propertiesComponent.unsetValue(PYRANGE_TEMPLATE_PREFIX + templateCollectionName + "-fe/detailDialog.ftl");
//        propertiesComponent.unsetValue(PYRANGE_TEMPLATE_PREFIX + templateCollectionName + "-test.ftl");
//        propertiesComponent.unsetValue(PYRANGE_TEMPLATE_PREFIX + templateCollectionName + "-test-constant.ftl");
    }

    /**
     * 
     *
     * @param templateName
     * @return
     * @throws IOException
     */
    public static Template getTemplate(String templateName) throws IOException {
        String templateCollectionName = BasicConfig.getTemplateCollectionName();
        Configuration conf = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        // 
        String templateContent = getTemplateContent(templateCollectionName, templateName);
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        stringTemplateLoader.putTemplate(templateName, templateContent);
        conf.setTemplateLoader(stringTemplateLoader);
        return conf.getTemplate(templateName, "utf-8");
    }

    /**
     * 
     * 
     *
     * @param templateCollectionName
     * @param templateName
     * @return
     */
    public static String getTemplateContent(String templateCollectionName, String templateName) {
        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();
        String value = propertiesComponent.getValue(PYRANGE_TEMPLATE_PREFIX + templateCollectionName + "-" + templateName);
        if (StringUtils.isEmpty(value)) {
            return FileUtils.readFile("/template/" + templateName);
        }

        return value;
    }

    /**
     * 
     * 
     *
     * @param cloudConfigUrl
     * @return
     */
    public static Result loadCloudTemplate(String cloudConfigUrl) {
        for (String template: PYRANGE_TEMPLATE_LIST) {
            doLoadCloudTemplate(cloudConfigUrl, template);
        }

        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();
        propertiesComponent.setValue("PYRANGE-SETTINGS-haveCloudConfigSet", true);
        return Result.success();
    }

    /**
     * 
     * 
     *
     * @param cloudConfigUrl
     * @return
     */
    public static void doLoadCloudTemplate(String cloudConfigUrl, String templateName) {
        try {
            String url;
            if ("/".equals(cloudConfigUrl.substring(cloudConfigUrl.length() - 1))) {
                url = cloudConfigUrl + templateName;
            } else {
                url = cloudConfigUrl + "/" + templateName;
            }
            HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(url))
                    .GET()
                    .build();
            CompletableFuture<String> result = HttpClient.newHttpClient()
                    .sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .exceptionally(err -> {
                        log.error("", err);
                        throw new PyrangeException(", " + url);
                    });
            String templateContent = result.get();
            if (StringUtils.isEmpty(templateContent)) {
                throw new PyrangeException(", " + url);
            }
            saveTemplate("cloud", templateName, templateContent);
        } catch (Exception e) {
            log.error("", e);
            throw new PyrangeException(", :" + Throwables.getStackTraceAsString(e));
        }
    }

}
