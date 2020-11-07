package shch91.enums;

public enum Type {
    ONE(1,"one"),
    TWO(2,"two"),
    ;
    private int code;
    private String desc;

    private Type(int code,String desc){
        this.code=code;
        this.desc=desc;
    }
}
