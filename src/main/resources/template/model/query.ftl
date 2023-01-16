package ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot};

import ${basicConfig.basePageClassReference};
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
public class ${generateInfo.moduleName}Query extends ${basicConfig.basePageClassName} {

    /**
     * 查询关键字
     **/
    private String keyword;
}