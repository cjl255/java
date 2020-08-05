package com.cjl25.cjl25.Message;

import lombok.Data;

@Data
public class message {
    String  key;
    String value;
    int pos;
    String id;
    public void setid(int x)
    {
        x=0;
    }
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
