<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.mapper;

import ${basepackage}.model.${className}Model;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ${className}MapperGen {

	//===========================================代码生成开始============================================

    ${className}Model get${className}(${table.idColumn.javaType} ${table.idColumn.columnName?uncap_first});

    List<${className}Model> get${className}List(Map<String, Object> searchParams);

    int insert${className}(${className}Model ${classNameLower}Model);

    int insert${className}Batch(List<${className}Model> ${classNameLower}ModelList);

    int update${className}(${className}Model ${classNameLower}Model);

    //===========================================代码生成结束============================================
}
