package com.example.baitap5_c4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;

public class GraphicsView extends View {

    private int max = 105;
    private Bitmap frames[] = new Bitmap[max];
    private int i = 0;
    private long lastTick = 0;
    private long period = 10;
    private MediaPlayer mediaPlayer;

    public GraphicsView(Context context) {
        super(context);
        setUpImageList();

        mediaPlayer = MediaPlayer.create(context, R.raw.pepe_dance);
        mediaPlayer.start();
    }

    private void setUpImageList() {
        frames[0] = BitmapFactory.decodeResource(getResources(), R.drawable.frame0);
        frames[1] = BitmapFactory.decodeResource(getResources(), R.drawable.frame1);
        frames[2] = BitmapFactory.decodeResource(getResources(), R.drawable.frame2);
        frames[3] = BitmapFactory.decodeResource(getResources(), R.drawable.frame3);
        frames[4] = BitmapFactory.decodeResource(getResources(), R.drawable.frame4);
        frames[5] = BitmapFactory.decodeResource(getResources(), R.drawable.frame5);
        frames[6] = BitmapFactory.decodeResource(getResources(), R.drawable.frame6);
        frames[7] = BitmapFactory.decodeResource(getResources(), R.drawable.frame7);
        frames[8] = BitmapFactory.decodeResource(getResources(), R.drawable.frame8);
        frames[9] = BitmapFactory.decodeResource(getResources(), R.drawable.frame9);
        frames[10] = BitmapFactory.decodeResource(getResources(), R.drawable.frame10);
        frames[11] = BitmapFactory.decodeResource(getResources(), R.drawable.frame11);
        frames[12] = BitmapFactory.decodeResource(getResources(), R.drawable.frame12);
        frames[13] = BitmapFactory.decodeResource(getResources(), R.drawable.frame13);
        frames[14] = BitmapFactory.decodeResource(getResources(), R.drawable.frame14);
        frames[15] = BitmapFactory.decodeResource(getResources(), R.drawable.frame15);
        frames[16] = BitmapFactory.decodeResource(getResources(), R.drawable.frame16);
        frames[17] = BitmapFactory.decodeResource(getResources(), R.drawable.frame17);
        frames[18] = BitmapFactory.decodeResource(getResources(), R.drawable.frame18);
        frames[19] = BitmapFactory.decodeResource(getResources(), R.drawable.frame19);
        frames[20] = BitmapFactory.decodeResource(getResources(), R.drawable.frame20);
        frames[21] = BitmapFactory.decodeResource(getResources(), R.drawable.frame21);
        frames[22] = BitmapFactory.decodeResource(getResources(), R.drawable.frame22);
        frames[23] = BitmapFactory.decodeResource(getResources(), R.drawable.frame23);
        frames[24] = BitmapFactory.decodeResource(getResources(), R.drawable.frame24);
        frames[25] = BitmapFactory.decodeResource(getResources(), R.drawable.frame25);
        frames[26] = BitmapFactory.decodeResource(getResources(), R.drawable.frame26);
        frames[27] = BitmapFactory.decodeResource(getResources(), R.drawable.frame27);
        frames[28] = BitmapFactory.decodeResource(getResources(), R.drawable.frame28);
        frames[29] = BitmapFactory.decodeResource(getResources(), R.drawable.frame29);
        frames[30] = BitmapFactory.decodeResource(getResources(), R.drawable.frame30);
        frames[31] = BitmapFactory.decodeResource(getResources(), R.drawable.frame31);
        frames[32] = BitmapFactory.decodeResource(getResources(), R.drawable.frame32);
        frames[33] = BitmapFactory.decodeResource(getResources(), R.drawable.frame33);
        frames[34] = BitmapFactory.decodeResource(getResources(), R.drawable.frame34);
        frames[35] = BitmapFactory.decodeResource(getResources(), R.drawable.frame35);
        frames[36] = BitmapFactory.decodeResource(getResources(), R.drawable.frame36);
        frames[37] = BitmapFactory.decodeResource(getResources(), R.drawable.frame37);
        frames[38] = BitmapFactory.decodeResource(getResources(), R.drawable.frame38);
        frames[39] = BitmapFactory.decodeResource(getResources(), R.drawable.frame39);
        frames[40] = BitmapFactory.decodeResource(getResources(), R.drawable.frame40);
        frames[41] = BitmapFactory.decodeResource(getResources(), R.drawable.frame41);
        frames[42] = BitmapFactory.decodeResource(getResources(), R.drawable.frame42);
        frames[43] = BitmapFactory.decodeResource(getResources(), R.drawable.frame43);
        frames[44] = BitmapFactory.decodeResource(getResources(), R.drawable.frame44);
        frames[45] = BitmapFactory.decodeResource(getResources(), R.drawable.frame45);
        frames[46] = BitmapFactory.decodeResource(getResources(), R.drawable.frame46);
        frames[47] = BitmapFactory.decodeResource(getResources(), R.drawable.frame47);
        frames[48] = BitmapFactory.decodeResource(getResources(), R.drawable.frame48);
        frames[49] = BitmapFactory.decodeResource(getResources(), R.drawable.frame49);
        frames[50] = BitmapFactory.decodeResource(getResources(), R.drawable.frame50);
        frames[51] = BitmapFactory.decodeResource(getResources(), R.drawable.frame51);
        frames[52] = BitmapFactory.decodeResource(getResources(), R.drawable.frame52);
        frames[53] = BitmapFactory.decodeResource(getResources(), R.drawable.frame53);
        frames[54] = BitmapFactory.decodeResource(getResources(), R.drawable.frame54);
        frames[55] = BitmapFactory.decodeResource(getResources(), R.drawable.frame55);
        frames[56] = BitmapFactory.decodeResource(getResources(), R.drawable.frame56);
        frames[57] = BitmapFactory.decodeResource(getResources(), R.drawable.frame57);
        frames[58] = BitmapFactory.decodeResource(getResources(), R.drawable.frame58);
        frames[59] = BitmapFactory.decodeResource(getResources(), R.drawable.frame59);
        frames[60] = BitmapFactory.decodeResource(getResources(), R.drawable.frame60);
        frames[61] = BitmapFactory.decodeResource(getResources(), R.drawable.frame61);
        frames[62] = BitmapFactory.decodeResource(getResources(), R.drawable.frame62);
        frames[63] = BitmapFactory.decodeResource(getResources(), R.drawable.frame63);
        frames[64] = BitmapFactory.decodeResource(getResources(), R.drawable.frame64);
        frames[65] = BitmapFactory.decodeResource(getResources(), R.drawable.frame65);
        frames[66] = BitmapFactory.decodeResource(getResources(), R.drawable.frame66);
        frames[67] = BitmapFactory.decodeResource(getResources(), R.drawable.frame67);
        frames[68] = BitmapFactory.decodeResource(getResources(), R.drawable.frame68);
        frames[69] = BitmapFactory.decodeResource(getResources(), R.drawable.frame69);
        frames[70] = BitmapFactory.decodeResource(getResources(), R.drawable.frame70);
        frames[71] = BitmapFactory.decodeResource(getResources(), R.drawable.frame71);
        frames[72] = BitmapFactory.decodeResource(getResources(), R.drawable.frame72);
        frames[73] = BitmapFactory.decodeResource(getResources(), R.drawable.frame73);
        frames[74] = BitmapFactory.decodeResource(getResources(), R.drawable.frame74);
        frames[75] = BitmapFactory.decodeResource(getResources(), R.drawable.frame75);
        frames[76] = BitmapFactory.decodeResource(getResources(), R.drawable.frame76);
        frames[77] = BitmapFactory.decodeResource(getResources(), R.drawable.frame77);
        frames[78] = BitmapFactory.decodeResource(getResources(), R.drawable.frame78);
        frames[79] = BitmapFactory.decodeResource(getResources(), R.drawable.frame79);
        frames[80] = BitmapFactory.decodeResource(getResources(), R.drawable.frame80);
        frames[81] = BitmapFactory.decodeResource(getResources(), R.drawable.frame81);
        frames[82] = BitmapFactory.decodeResource(getResources(), R.drawable.frame82);
        frames[83] = BitmapFactory.decodeResource(getResources(), R.drawable.frame83);
        frames[84] = BitmapFactory.decodeResource(getResources(), R.drawable.frame84);
        frames[85] = BitmapFactory.decodeResource(getResources(), R.drawable.frame85);
        frames[86] = BitmapFactory.decodeResource(getResources(), R.drawable.frame86);
        frames[87] = BitmapFactory.decodeResource(getResources(), R.drawable.frame87);
        frames[88] = BitmapFactory.decodeResource(getResources(), R.drawable.frame88);
        frames[89] = BitmapFactory.decodeResource(getResources(), R.drawable.frame89);
        frames[90] = BitmapFactory.decodeResource(getResources(), R.drawable.frame90);
        frames[91] = BitmapFactory.decodeResource(getResources(), R.drawable.frame91);
        frames[92] = BitmapFactory.decodeResource(getResources(), R.drawable.frame92);
        frames[93] = BitmapFactory.decodeResource(getResources(), R.drawable.frame93);
        frames[94] = BitmapFactory.decodeResource(getResources(), R.drawable.frame94);
        frames[95] = BitmapFactory.decodeResource(getResources(), R.drawable.frame95);
        frames[96] = BitmapFactory.decodeResource(getResources(), R.drawable.frame96);
        frames[97] = BitmapFactory.decodeResource(getResources(), R.drawable.frame97);
        frames[98] = BitmapFactory.decodeResource(getResources(), R.drawable.frame98);
        frames[99] = BitmapFactory.decodeResource(getResources(), R.drawable.frame99);
        frames[100] = BitmapFactory.decodeResource(getResources(), R.drawable.frame100);
        frames[101] = BitmapFactory.decodeResource(getResources(), R.drawable.frame101);
        frames[102] = BitmapFactory.decodeResource(getResources(), R.drawable.frame102);
        frames[103] = BitmapFactory.decodeResource(getResources(), R.drawable.frame103);
        frames[104] = BitmapFactory.decodeResource(getResources(), R.drawable.frame104);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (i < max) {
            long time = System.currentTimeMillis() - lastTick;
            if (time >= period) {
                lastTick = System.currentTimeMillis();
                canvas.drawBitmap(frames[i], -300, 0, new Paint());
                i++;
            } else {
                canvas.drawBitmap(frames[i], -300, 0, new Paint());
            }
        } else {
            i = 0;
        }
        postInvalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        i++;
        return true;
    }
}
