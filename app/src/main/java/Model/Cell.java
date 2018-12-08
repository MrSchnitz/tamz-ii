package Model;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Cell {

    public static int ID = 0;
    public static int numbOfCell = 4;
    public static int size = 0;
    //public static int size = canvas.width / numbOfCell - 6;



    int id, x,y,w,h;

    float textX, textSize;

    String color;

    Paint paintRect, paintValue;

    int value;

    AnimatedRectF rect;

    Text text;

    public Cell(){

    }

    public Cell(Cell c){
        this.id = ID++;
        this.x = c.getX();
        this.y = c.getY();
        this.w = c.getW();
        this.h = c.getH();
        this.textX = c.getTextX();
        this.textSize = c.getTextSize();
        this.color = c.getColor();
        this.paintRect = c.getPaintRect();
        this.paintValue = c.getPaintValue();
        this.value = c.getValue();
        this.rect = c.getRect();
        this.text = c.getText();
    }



    public Cell(int x, int y, int canvasWidth, int canvasX, int canvasY){

        this.id = ID++;

        size = canvasWidth / numbOfCell - 25;

        this.x = y * size + 20 * (y + 1);
        this.y = x * size + canvasY + 20 * (x + 1);

        this.w = this.x + size;
        this.h = this.y + size;
        this.color = "#A9A9A9";
        this.value = 0;

        paintRect = new Paint();
        paintRect.setColor(Color.parseColor(this.color));

        paintValue = new Paint();
        paintValue.setColor(Color.WHITE);
        paintValue.setTextSize(150);

        this.textX = paintValue.measureText(String.valueOf(this.value))/2;
        this.textSize = paintValue.getTextSize();

        rect = new AnimatedRectF(this.x,this.y,this.w,this.h);
    }

    public Cell(int x, int y, String c, int canvasWidth, int canvasX, int canvasY){

        this.id = ID++;

        size = canvasWidth / numbOfCell - 25;

        this.x = y * size + 20 * (y + 1);
        this.y = x * size + canvasY + 20 * (x + 1);

        this.w = this.x + size;
        this.h = this.y + size;
        this.color = c;
        this.value = 0;

        paintRect = new Paint();
        paintRect.setColor(Color.parseColor(this.color));

        paintValue = new Paint();
        paintValue.setColor(Color.WHITE);
        paintValue.setTextSize(150);

        this.textX = paintValue.measureText(String.valueOf(this.value))/2;
        this.textSize = paintValue.getTextSize();

        rect = new AnimatedRectF(this.x,this.y,this.w,this.h);
    }

    public void drawCell(Canvas canvas) {

        //RectF rect = new RectF(this.x,this.y,this.w,this.h);

        canvas.drawRoundRect(rect,30,30,this.paintRect);

        if(this.value > 8)
            canvas.drawText(String.valueOf(this.value),this.x+size/2 - this.textX*2,this.y+size/2+(this.textSize/3),this.paintValue);
        else
            canvas.drawText(String.valueOf(this.value),this.x+size/2 - this.textX,this.y+size/2+(this.textSize/3),this.paintValue);




        /*
        ctx.fillStyle = this.color;
        // ctx.fillStyle = "rgba(169, 169, 169, 0.5)";
        ctx.fillRect(this.x, this.y, this.w, this.h);
        // render(this.x,this.y,this.w,this.h);

        if(this.value !== 0) {
            fontSize = this.w / 2;
            ctx.font = fontSize + 'px Arial';
            ctx.fillStyle = 'white';
            ctx.textAlign = 'center';
            ctx.fillText(this.value, this.x + this.w / 2, this.y + this.w / 2 + this.w / 7);
        }
        */
    }


    public void resetCell(){
        this.color = "#A9A9A9";
        this.paintRect.setColor(Color.parseColor(this.color));
        this.value = 0;
    }

    public void setColor(String c){
        this.color = c;
        this.paintRect.setColor(Color.parseColor(this.color));
    }

    public String getColor(){
        return this.color;
    }

    public void setValue(int v){
        this.value = v;
    }

    public int getValue(){
        return this.value;
    }

    public AnimatedRectF getRect(){
        return this.rect;
    }

    public static int getNumbOfCell() {
        return numbOfCell;
    }

    public static void setNumbOfCell(int numbOfCell) {
        Cell.numbOfCell = numbOfCell;
    }

    public static int getSize() {
        return size;
    }

    public static void setSize(int size) {
        Cell.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public float getTextX() {
        return textX;
    }

    public void setTextX(float textX) {
        this.textX = textX;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public Paint getPaintRect() {
        return paintRect;
    }

    public void setPaintRect(Paint paintRect) {
        this.paintRect = paintRect;
    }

    public Paint getPaintValue() {
        return paintValue;
    }

    public void setPaintValue(Paint paintValue) {
        this.paintValue = paintValue;
    }

    public void setRect(AnimatedRectF rect) {
        this.rect = rect;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }
}
