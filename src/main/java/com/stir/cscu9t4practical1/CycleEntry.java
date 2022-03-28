package com.stir.cscu9t4practical1;

public class CycleEntry extends Entry {
    private String terrain;
    private String tempo;
    public CycleEntry(String n, int d, int m, int y, int h, int min, int s, float dist, String ter, String tem) {
        super(n, d, m, y, h, min, s, dist);
        this.terrain = ter;
        this.tempo = tem;
    }

    public String getTempo() {
        return tempo;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    @Override
    public String getEntry () {
        String result = getName()+" cycled " + getDistance() + " km in "
                +getHour()+":"+getMin()+":"+ getSec() + " on " + getTerrain() + " with tempo of "+ getTempo() +
                " on " + getDay()+"/"+getMonth()+"/"+getYear() + "\n";
        return result;
    }

}
