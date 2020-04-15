package com.graph;

public class Indices implements Comparable<Indices> {
    private Integer i;
    private Integer j;
    private Integer w;

    public Indices(Integer i, Integer j, Integer w){
        this.i = i;
        this.j = j;
        this.w = w;
    }

    @Override
    public int compareTo(Indices o) {
        if(this.w.equals(o.w))
            if(this.i.equals(o.i))
                return this.j - o.j;
            else
                return this.i - o.i;
        else {
            return o.w - this.w;
        }
    }

    @Override
    public boolean equals(Object obj) {
        Indices o = (Indices) obj;
        return (this.i.equals(o.i) && this.j.equals(o.j) && this.w.equals(o.w));
    }

    @Override
    public int hashCode() {
        return (this.i + "," + this.j).hashCode();
    }

    @Override
    public String toString() {
        return "[" + w + "," + i + "," + j + "]";
    }

    public Integer getI() {
        return i;
    }

    public Integer getJ() {
        return j;
    }

    public Integer getW() {
        return w;
    }
}
