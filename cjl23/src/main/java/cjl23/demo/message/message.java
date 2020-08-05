package cjl23.demo.message;

import lombok.Data;

@Data
public class message {
    String key;
    String value;
    public void setKey(String x)
    {
        this.key=x;
    }
    public void setValue(String y)
    {
        this.value=y;
    }
    public String getKey()
    {
        return this.key;
    }
    public String getValue()
    {
        return this.value;
    }
}
