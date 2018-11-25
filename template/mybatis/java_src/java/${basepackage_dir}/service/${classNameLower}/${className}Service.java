<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.model;

/** 
* ${table.sqlName} 表的Model
*/
public class ${className}Model extends ${className}ModelGen {
	
}

@Service
public class ${className}Service extends BaseService {
    @Autowired
    private ${className}Mapper ${classNameLower}Mapper;

    public PageModel<${className}Model> get${className}List(Map<String, Object> searchParams) {
        startPage(searchParams);//必须放在将要分页查询方法的前面.因为只有紧跟在PageHelper.startPage方法后的第一个Mybatis的查询（Select方法）方法会被分页。
        List<${className}Model> modelList = ${classNameLower}Mapper.get${className}List(searchParams);
        PageInfo<${className}Model> pageInfo = new PageInfo<>(modelList);
        return converterPageInfo(pageInfo);
    }

    public CommonReturn insert${className}(${className}Model ${classNameLower}Model) {
        CommonReturn ret = new CommonReturn();
        MemberUtils.setInsertUser(${classNameLower}Model);
        int effect = ${classNameLower}Mapper.insert${className}(${classNameLower}Model);
        if (effect == DictConstants.EFFECT_ERROR) {
            ret.setReturnCode(ReturnCode.CODE_900);
        }
        ret.setData(${classNameLower}Model);
        return ret;
    }

    public CommonReturn delete${className}(${className}Model ${classNameLower}Model) {
        CommonReturn ret = new CommonReturn();
        if (${classNameLower}Model == null) {
            ret.setReturnCode(ReturnCode.CODE_1109);
            return ret;
        }
        ${classNameLower}Model.setDeleted(true);
        MemberUtils.setUpdateUser(${classNameLower}Model);
        int effect = ${classNameLower}Mapper.update${className}(${classNameLower}Model);
        if (effect == DictConstants.EFFECT_ERROR) {
            ret.setReturnCode(ReturnCode.CODE_900);
            return ret;
        }
        return ret;
    }

    public CommonReturn update${className}(${className}Model ${classNameLower}Model) {
        CommonReturn ret = new CommonReturn();
        MemberUtils.setUpdateUser(${classNameLower}Model);
        int effect = ${classNameLower}Mapper.update${className}(${classNameLower}Model);
        if (effect == DictConstants.EFFECT_ERROR) {
            ret.setReturnCode(ReturnCode.CODE_900);
            return ret;
        }
        return ret;
    }