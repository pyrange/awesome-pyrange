package com.pyrange.awesome.util;

import com.google.common.base.Throwables;
import com.intellij.ide.util.PropertiesComponent;
import com.pyrange.awesome.PyrangeException;
import com.pyrange.awesome.model.Result;
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
 * 模板服务
 *
 * @author chenjw
 * @date 2023-2-3
 */
public class TemplateUtil {

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
        PYRANGE_TEMPLATE_LIST.add("fe/editDialog.ftl");
        PYRANGE_TEMPLATE_LIST.add("fe/detailDialog.ftl");
        PYRANGE_TEMPLATE_LIST.add("test.ftl");
        PYRANGE_TEMPLATE_LIST.add("test-constant.ftl");
    }

    /**
     * 保存模板信息
     *
     * @param selectedCodeTemplate
     * @param templateName
     */
    public static void saveTemplate(String selectedCodeTemplate, String templateName, String templateContent) {
        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();
        propertiesComponent.setValue(PYRANGE_TEMPLATE_PREFIX + selectedCodeTemplate + "-" + templateName,
                templateContent);
    }

    /**
     * 初始化模板
     *
     * @param selectedCodeTemplate
     */
    public static void initialDefaultTemplate(String selectedCodeTemplate) {
        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();
        for (String template: PYRANGE_TEMPLATE_LIST) {
            propertiesComponent.unsetValue(PYRANGE_TEMPLATE_PREFIX + selectedCodeTemplate + "-" + template);
        }
//        propertiesComponent.unsetValue(PYRANGE_TEMPLATE_PREFIX + selectedCodeTemplate + "-controller.ftl");
//        propertiesComponent.unsetValue(PYRANGE_TEMPLATE_PREFIX + selectedCodeTemplate + "-service.ftl");
//        propertiesComponent.unsetValue(PYRANGE_TEMPLATE_PREFIX + selectedCodeTemplate + "-service-impl.ftl");
//        propertiesComponent.unsetValue(PYRANGE_TEMPLATE_PREFIX + selectedCodeTemplate + "-mapper.ftl");
//        propertiesComponent.unsetValue(PYRANGE_TEMPLATE_PREFIX + selectedCodeTemplate + "-mapperxml.ftl");
//        propertiesComponent.unsetValue(PYRANGE_TEMPLATE_PREFIX + selectedCodeTemplate + "-model/insert.ftl");
//        propertiesComponent.unsetValue(PYRANGE_TEMPLATE_PREFIX + selectedCodeTemplate + "-model/update.ftl");
//        propertiesComponent.unsetValue(PYRANGE_TEMPLATE_PREFIX + selectedCodeTemplate + "-model/po.ftl");
//        propertiesComponent.unsetValue(PYRANGE_TEMPLATE_PREFIX + selectedCodeTemplate + "-model/query.ftl");
//        propertiesComponent.unsetValue(PYRANGE_TEMPLATE_PREFIX + selectedCodeTemplate + "-model/brief.ftl");
//        propertiesComponent.unsetValue(PYRANGE_TEMPLATE_PREFIX + selectedCodeTemplate + "-model/detail.ftl");
//        propertiesComponent.unsetValue(PYRANGE_TEMPLATE_PREFIX + selectedCodeTemplate + "-fe/index.ftl");
//        propertiesComponent.unsetValue(PYRANGE_TEMPLATE_PREFIX + selectedCodeTemplate + "-fe/editDialog.ftl");
//        propertiesComponent.unsetValue(PYRANGE_TEMPLATE_PREFIX + selectedCodeTemplate + "-fe/detailDialog.ftl");
//        propertiesComponent.unsetValue(PYRANGE_TEMPLATE_PREFIX + selectedCodeTemplate + "-test.ftl");
//        propertiesComponent.unsetValue(PYRANGE_TEMPLATE_PREFIX + selectedCodeTemplate + "-test-constant.ftl");
    }

    /**
     * 创建新模板
     *
     * @param selectedCodeTemplate
     * @param templateName
     * @return
     * @throws IOException
     */
    public static Template getTemplate(String selectedCodeTemplate, String templateName) throws IOException {
        Configuration conf = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        // 获取模板加载器
        String templateContent = getTemplateContent(selectedCodeTemplate, templateName);
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        stringTemplateLoader.putTemplate(templateName, templateContent);
        conf.setTemplateLoader(stringTemplateLoader);
        return conf.getTemplate(templateName, "utf-8");
    }

    /**
     * 获取模板内容
     * 默认获取本地配置
     *
     * @param selectedCodeTemplate
     * @param templateName
     * @return
     */
    public static String getTemplateContent(String selectedCodeTemplate, String templateName) {
        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();
        String value = propertiesComponent.getValue(PYRANGE_TEMPLATE_PREFIX + selectedCodeTemplate + "-" + templateName);
        if (StringUtils.isEmpty(value)) {
            return FileUtils.readFile("/template/" + templateName);
        }

        return value;
    }

    /**
     * 获取模板内容
     * 默认获取本地配置
     *
     * @param cloudConfigUrl
     * @return
     */
    public static Result loadCloudTemplate(String cloudConfigUrl) {
        for (String template: PYRANGE_TEMPLATE_LIST) {
            doLoadCloudTemplate(cloudConfigUrl, template);
        }
        return Result.success();
    }

    /**
     * 获取模板内容
     * 默认获取本地配置
     *
     * @param cloudConfigUrl
     * @return
     */
    public static void doLoadCloudTemplate(String cloudConfigUrl, String suffix) {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(cloudConfigUrl + "/" + suffix))
                    .GET()
                    .build();
            CompletableFuture<String> result = HttpClient.newHttpClient()
                    .sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .exceptionally(err -> {
                        err.printStackTrace();
                        return null;
                    });
            String content = result.get();
            if (StringUtils.isEmpty(content)) {
                throw new PyrangeException("加载模板网络异常, 无法访问" + cloudConfigUrl + "/" + suffix);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new PyrangeException("加载模板异常, 异常信息:" + Throwables.getStackTraceAsString(e));
        }
    }

}
