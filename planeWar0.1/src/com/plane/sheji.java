package com.plane;

import java.awt.*;

public class sheji extends Plane{

    public sheji( double x, double y) {
        super(x, y);
        width = 40;
        height = 40;
    }

    @Override
    public void drawMyself(Graphics g) {
        super.drawMyself(g);
        Color c=g.getColor();
        g.setColor(Color.yellow);
        g.drawLine((int)this.x,(int)this.y,(int)this.x,(int)this.y-1000);
        g.drawLine((int)this.x+15,(int)this.y,(int)this.x+15,(int)this.y-1000);
        g.setColor(c);
    }
}
