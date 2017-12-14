package net.ttk1;

public class MyPosition {
    private String worldname;
    private final int x;
    private final int y;
    private final int z;

    public MyPosition(int x, int y, int z, String worldname){
        this.x = x;
        this.y = y;
        this.z = z;
        this.worldname = worldname;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getZ() {
        return z;
    }
    public String getWorldName() {
        return worldname;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof MyPosition) {
            MyPosition mp = (MyPosition) o;
            if(mp.getX() == this.x && mp.getY() == this.y && mp.getZ() == this.z && mp.getWorldName().equals(this.worldname)) {
                return true;
            }
        }
        return false;
    }
}