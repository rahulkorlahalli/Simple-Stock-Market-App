package com.example.courseactivity;

import java.io.Serializable;

public class CompanyData implements Serializable {
    public String name;
    public String desc;
    public String symbol;
    public String currency;
    public CompanyData(String name,String symbol,String desc,String currency){
        this.name = name;
        this.symbol = symbol;
        this.desc = desc;
        this.currency = currency;
    }
}
