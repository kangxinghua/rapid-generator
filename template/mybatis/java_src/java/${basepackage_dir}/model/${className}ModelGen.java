<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.model;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.mycommon.modules.utils.Dates;
import org.mycommon.modules.model.DataModel;

/**
* ${table.sqlName} 表的Model
*/
public class ${className}ModelGen extends DataModel {

	//===========================================代码生成开始============================================

    //columns 开始
	<#list table.columns as column>
	<#switch column.sqlName>
	<#case "id">
	<#case "create_time">
  	<#case "create_by">
  	<#case "create_by_name">
  	<#case "create_time">
  	<#case "update_by">
  	<#case "update_by_name">
  	<#case "update_time">
  	<#case "deleted">
  	<#case "version">
  	<#break>
  	<#default>
    private ${column.javaType} ${column.columnNameLower};//${column.remarks}
    <#break>
	</#switch>
	</#list>
	//columns 结束


<@generateJavaColumns/>
<@generateJavaOneToMany/>
<@generateJavaManyToOne/>

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
		<#list table.columns as column>
			.append("${column.columnNameLower}",get${column.columnNameLower?cap_first}())
		</#list>
			.toString();
	}
	//===========================================代码生成结束============================================
}

<#macro generateJavaColumns>
	<#list table.columns as column>
	<#switch column.sqlName>
	<#case "id">
  	<#case "create_time">
  	<#case "create_by">
  	<#case "create_by_name">
  	<#case "create_time">
  	<#case "update_by">
  	<#case "update_by_name">
  	<#case "update_time">
  	<#case "deleted">
  	<#case "version">
  	<#break>
  	<#default>

  	/**
     * ${column.remarks}
     *
     * @param value
     */
	public void set${column.columnNameLower?cap_first}(${column.javaType} value) {
		this.${column.columnNameLower} = value;
	}

	/**
     * ${column.remarks}
     */
	<#if column.isDateTimeColumn>
	@JSONField(format = Dates.PATTERN_CLASSICAL)
	</#if>
	public ${column.javaType} get${column.columnNameLower?cap_first}() {
		return this.${column.columnNameLower};
	}
	<#break>
	</#switch>
	</#list>
</#macro>

<#macro generateJavaOneToMany>
	<#list table.exportedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>

	private Set ${fkPojoClassVar}s = new HashSet(0);
	public void set${fkPojoClass}s(Set<${fkPojoClass}> ${fkPojoClassVar}){
		this.${fkPojoClassVar}s = ${fkPojoClassVar};
	}

	public Set<${fkPojoClass}> get${fkPojoClass}s() {
		return ${fkPojoClassVar}s;
	}
	</#list>
</#macro>

<#macro generateJavaManyToOne>
	<#list table.importedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>

	private ${fkPojoClass} ${fkPojoClassVar};

	public void set${fkPojoClass}(${fkPojoClass} ${fkPojoClassVar}){
		this.${fkPojoClassVar} = ${fkPojoClassVar};
	}

	public ${fkPojoClass} get${fkPojoClass}() {
		return ${fkPojoClassVar};
	}
	</#list>
</#macro>
