package ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot};

import com.pyrange.common.model.dto.BasePage;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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

    /**
    * 开始日期
    */
    private LocalDate startDate;

    /**
    * 截止日期
    */
    private LocalDate endDate;
}