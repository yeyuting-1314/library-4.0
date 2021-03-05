package library.util;

import library.base.BaseEnum;

/**
 * @author yeyuting
 * @create 2021/2/26
 */
public enum BaseEnums implements BaseEnum<String , String> {

    SUCCESS("request.success" , "请求成功") ,
    FAILURE("request.failure", "请求失败");

    private String code;

    private String desc;

    BaseEnums(String code , String desc) {
        this.code = code ;
        this.desc = desc ;
    }

    @Override
    public String code() {
        return null;
    }

    @Override
    public String desc() {
        return null;
    }
}
