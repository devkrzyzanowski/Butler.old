/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.utils;

/**
 *
 * @author Michał
 */
public class BooleanObject {
    private String name;
    private Boolean val;
    private Integer id;
    
    public BooleanObject(Integer id, String name, Boolean val){
        this.id = id;
        this.name = name;
        this.val = val;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getVal() {
        return val;
    }

    public void setVal(Boolean val) {
        this.val = val;
    }
}
