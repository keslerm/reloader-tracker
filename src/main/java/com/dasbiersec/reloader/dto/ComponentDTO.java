package com.dasbiersec.reloader.dto;

import com.dasbiersec.reloader.enums.ComponentType;

import java.io.Serializable;
import java.math.BigDecimal;

public class ComponentDTO implements Serializable
{
    private Integer id;
    private String name;
    private ComponentType type;
    private BigDecimal amount;
    private BigDecimal cost;
    private BigDecimal remaining;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ComponentType getType()
    {
        return type;
    }

    public void setType(ComponentType type)
    {
        this.type = type;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public BigDecimal getCost()
    {
        return cost;
    }

    public void setCost(BigDecimal cost)
    {
        this.cost = cost;
    }

    public BigDecimal getRemaining()
    {
        return remaining;
    }

    public void setRemaining(BigDecimal remaining)
    {
        this.remaining = remaining;
    }
}
