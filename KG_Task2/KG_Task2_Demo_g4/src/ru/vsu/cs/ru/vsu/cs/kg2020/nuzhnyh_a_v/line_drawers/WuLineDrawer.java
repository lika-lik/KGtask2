package ru.vsu.cs.ru.vsu.cs.kg2020.nuzhnyh_a_v.line_drawers;

import ru.vsu.cs.ru.vsu.cs.kg2020.nuzhnyh_a_v.LineDrawer;
import ru.vsu.cs.ru.vsu.cs.kg2020.nuzhnyh_a_v.PixelDrawer;

import java.awt.*;

public class WuLineDrawer implements LineDrawer {
    private PixelDrawer pd;
    private Color color = Color.black;

    public WuLineDrawer(PixelDrawer pd) {
        this.pd = pd;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2){
        if (x2 < x1){
            int temp = x2; x2 = x1; x1 = temp;
            temp = y2; y2 = y1; y1 = temp;
        }
        drawPartLine(x1, y1, x2, y2);

       if (y2 < y1){
            int temp = x2; x2 = x1; x1 = temp;
            temp = y2; y2 = y1; y1 = temp;
        }
        drawPartLine(x1, y1, x2, y2);
    }

    private void drawPartLine(int x1, int y1, int x2, int y2){
        int dx = x2-x1;
        int dy = y2-y1;

        ifPararel(dx, dy, x1, x2);

        if (dx > dy) {
            dXMoreDy(dx, dy, x1, y1, x2, y2);
        }else {
            dXLessDy(dx, dy, x1, y1, x2, y2);
        }
    }

    private void ifPararel(int dx, int dy, int x1, int y1){
        if (dx == 0 || dy == 0) {
            if(dy == 0) {
                for (int j = y1; j < dy; j++) {
                    pd.drawPixwl(x1, j, color);
                    return;
                }
            }else{
                for (int i = x1; i < dx; i++){
                    pd.drawPixwl(i, y1, color);
                }
            }
        }
    }

    private void dXMoreDy(int dx, int dy, int x1, int y1, int x2, int y2){
        float gradient = (float) dy / dx;
        float interY = y1 + gradient;
        for (int x = x1; x < x2 - 1; ++x) {
            pd.drawPixwl(x, (int)interY, new Color(0, 0, 0, (int) (255 - (interY - (int)interY) * 255)));
            pd.drawPixwl(x, (int)interY + 1, new Color(0, 0, 0, (int) (interY - (int)interY) * 255));
            interY += gradient;
        }
    }

    private void dXLessDy(int dx, int dy, int x1, int y1, int x2, int y2){
        float gradient = (float) dx / dy;
        float interX = x1 + gradient;
        for (int y = y1; y < y2 - 1; ++y) {
            pd.drawPixwl((int)interX, y, new Color(0, 0, 0, (int) (255 - (interX - (int)(interX)) * 255)));
            pd.drawPixwl((int)interX + 1, y, new Color(0, 0, 0, (int) (interX - (int)interX) * 255));
            interX += gradient;
        }
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
