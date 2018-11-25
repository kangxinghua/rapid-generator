<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ${className}Mapper extends ${className}MapperGen{
	
}
