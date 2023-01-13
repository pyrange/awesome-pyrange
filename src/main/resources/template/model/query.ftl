package ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot};

import ${configModel.groupId}.common.model.dto.BasePage;
import lombok.Getter;
import lombok.Setter;

/**
 * ${generateInfo.tableComment}查询
 *
 * @author ${generateInfo.author}
 * @date ${generateInfo.date}
 **/
@Getter
@Setter
public class ${generateInfo.moduleName}Query extends BasePage {

    /**
     * 查询关键字
     **/
    private String keyword;
}