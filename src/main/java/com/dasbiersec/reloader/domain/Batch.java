package com.dasbiersec.reloader.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "batch")
public class Batch extends AbstractEntity
{
    private int count;

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }
}
