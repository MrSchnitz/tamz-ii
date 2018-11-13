package Model;

import android.graphics.Rect;
import android.graphics.RectF;

public class AnimatedRectF extends RectF {

    public AnimatedRectF() {
        super();
    }

    public AnimatedRectF(float left, float top, float right, float bottom) {
        super(left, top, right, bottom);
    }

    public AnimatedRectF(RectF r) {
        super(r);
    }

    public AnimatedRectF(Rect r) {
        super(r);
    }


    public void setTop(float top){
        this.top = top;
    }
    public void setBottom(float bottom){
        this.bottom = bottom;
    }
    public void setRight(float right){
        this.right = right;
    }
    public void setLeft(float left){
        this.left = left;
    }


}
