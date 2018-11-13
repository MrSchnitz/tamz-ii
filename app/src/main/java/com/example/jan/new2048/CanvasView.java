package com.example.jan.new2048;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import Model.Cell;
import listeners.OnSwipeTouchListener;

import static Model.Cell.numbOfCell;

public class CanvasView extends View {

    Button button = findViewById(R.id.button);



    public CanvasView(final Context context) {
        super(context);
        setOnTouchListener(new OnSwipeTouchListener(context){
            public void onSwipeTop() {
                Toast.makeText(getContext(), "top", Toast.LENGTH_SHORT).show();
                moveUp();
            }
            public void onSwipeRight() {
                Toast.makeText(getContext(), "right", Toast.LENGTH_SHORT).show();
                moveRight();
            }
            public void onSwipeLeft() {
                Toast.makeText(getContext(), "left", Toast.LENGTH_SHORT).show();
                moveLeft();
            }
            public void onSwipeBottom() {
                Toast.makeText(getContext(), "bottom", Toast.LENGTH_SHORT).show();
                moveDown();
            }
        });
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(new OnSwipeTouchListener(context){
            public void onSwipeTop() {
                Toast.makeText(getContext(), "top", Toast.LENGTH_SHORT).show();
                moveUp();
            }
            public void onSwipeRight() {
                Toast.makeText(getContext(), "right", Toast.LENGTH_SHORT).show();
                moveRight();
            }
            public void onSwipeLeft() {
                Toast.makeText(getContext(), "left", Toast.LENGTH_SHORT).show();
                moveLeft();
            }
            public void onSwipeBottom() {
                Toast.makeText(getContext(), "bottom", Toast.LENGTH_SHORT).show();
                moveDown();
            }
        });
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnTouchListener(new OnSwipeTouchListener(context){
            public void onSwipeTop() {
                Toast.makeText(getContext(), "top", Toast.LENGTH_SHORT).show();
                moveUp();
            }
            public void onSwipeRight() {
                Toast.makeText(getContext(), "right", Toast.LENGTH_SHORT).show();
                moveRight();
            }
            public void onSwipeLeft() {
                Toast.makeText(getContext(), "left", Toast.LENGTH_SHORT).show();
                moveLeft();
            }
            public void onSwipeBottom() {
                Toast.makeText(getContext(), "bottom", Toast.LENGTH_SHORT).show();
                moveDown();
            }
        });
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    PopupWindow popUp = new PopupWindow(this);

    Cell[][] cells = new Cell[numbOfCell][];


    Bitmap[] bmp;

    int lx = 10;
    int ly = 10;

    int width;
    int height;

    int score = 0;

    int screenWidth = 0;
    int screenHeight = 0;

    int canvasX, canvasY, canvasWidth, canvasHeight;

    boolean victory = false;

    boolean init = true;




    Button.OnTouchListener buttonTouchListener = new Button.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch(event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    Toast.makeText(getContext(),"Hmmmm",Toast.LENGTH_SHORT).show();
                    addNewCell();
                    return true;
                    default:
                    Toast.makeText(getContext(),"Hmmmm",Toast.LENGTH_SHORT).show();

            }
        return false;
        }
    };


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                addNewCell();
                break;
        }

        return super.onTouchEvent(event);
    }

    public void createCells(){
        int i, j;
        for(i = 0; i < numbOfCell; i++){
            cells[i] = new Cell[numbOfCell];
            for(j = 0; j < numbOfCell; j++){
                cells[i][j] = new Cell(i,j, canvasWidth,canvasX, canvasY);
            }
        }
    }

//
//    public void drawCells(){
//        for(int i = 0; i < Cell.numbOfCell; i++){
//            for(int j = 0; j < Cell.numbOfCell; j++){
//                cells[i][j].drawCell();
//            }
//        }
//    }


    public String matchColor(int val){
        String chosenColor;
        switch(val){
            case 0 : chosenColor = "#A9A9A9"; break;
            case 2 : chosenColor = "#D2691E"; break;
            case 4 : chosenColor = "#FF7F50"; break;
            case 8 : chosenColor = "#ffbf00"; break;
            case 16 : chosenColor = "#bfff00"; break;
            case 32 : chosenColor = "#40ff00"; break;
            case 64 : chosenColor = "#00bfff"; break;
            case 128 : chosenColor = "#FF7F50"; break;
            case 256 : chosenColor = "#0040ff"; break;
            case 512 : chosenColor = "#ff0080"; break;
            case 1024 : chosenColor = "#D2691E"; break;
            case 2048 : chosenColor = "#FF7F50"; break;
            case 4096 : chosenColor = "#ffbf00"; break;
            default : chosenColor = "#A9A9A9";
        }
        return chosenColor;
    }



    public void addNewCell(){
        ArrayList<Cell> emptyCells = new ArrayList<>();

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(cells[i][j].getValue() == 0){
                    emptyCells.add(cells[i][j]);
                }
            }
        }

//        Toast.makeText(getContext(),"hmmmm " + emptyCells.size(),Toast.LENGTH_SHORT).show();

        /*
        if(emptyCells.size() == 0){
            gameOver = true;
            alert("Hra skoncila");
            gameEnded();
        }
        */
        //scoreLabel.innerHTML = score;
        // let chosenCell = Math.floor(Math.random() * emptyCells.length);
        if(!emptyCells.isEmpty()) {
            Cell chosenCell = emptyCells.get(new Random().nextInt(emptyCells.size()));


            chosenCell.setValue(2);
            chosenCell.setColor("#D2691E");

            invalidate();
        }
    }



    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        screenWidth = w;
        screenHeight = h;

        canvasX = 0;
        canvasY = (screenHeight/2) - (screenWidth/2);
        canvasWidth = screenWidth;
        canvasHeight = (screenHeight/2) + (screenWidth/2);

        width = w / ly;
        height = h / lx;

        createCells();

        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if(init){
            init = false;
            addNewCell();
            addNewCell();
        }

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);

        // draw background
        paint.setStrokeWidth(20);
        //paint.setStyle(Paint.Style.STROKE);
        canvas.drawRoundRect(canvasX,canvasY,canvasWidth, canvasHeight,10,10,paint);

        Toast.makeText(getContext(),"Width: " + Cell.size,Toast.LENGTH_SHORT).show();
        //canvas.drawRoundRect(rect,6,6,paint);

        //canvas.drawRoundRect(new RectF(0, 100, 100, 200), 30, 30, paint);




        if(victory){
            paint.setColor(Color.RED);
            paint.setTextSize(200);
            Toast.makeText(getContext(),"Vyhral jste!!!",Toast.LENGTH_SHORT).show();
            canvas.drawText("Vyhral jste!!!",width/2,height*5, paint);
        }
        else {

            for (int i = 0; i < numbOfCell; i++) {
                for (int j = 0; j < numbOfCell; j++) {
                    cells[i][j].drawCell(canvas);
                }
            }
        }
            /*
            for (int i = 0; i < lx; i++) {
                for (int j = 0; j < ly; j++) {
                    canvas.drawBitmap(bmp[level[i][j]], null,
                            new Rect(j * width, i * height, (j + 1) * width, (i + 1) * height), null);
                }
            }
            */
    }

    public void animateRectRight(Cell cell, float translateX){
        //float translateX = 200.0f;

        ObjectAnimator animateRight = ObjectAnimator.ofFloat(cell.getRect(), "right", cell.getRect().right, cell.getRect().right+translateX);
        ObjectAnimator animateLeft = ObjectAnimator.ofFloat(cell.getRect(), "left", cell.getRect().left, cell.getRect().left+translateX);
        //ObjectAnimator animateText = ObjectAnimator.ofFloat(cell.getValue(), "text", )

        animateRight.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                postInvalidate();
            }
        });

//        animateRight.setDuration(500).start();
//        animateLeft.setDuration(500).start();

        AnimatorSet rectAnimation = new AnimatorSet();
        rectAnimation.playTogether(animateLeft, animateRight);
        rectAnimation.setDuration(1000).start();
    }

    public void moveRight(){
        int i, j;
        Cell chosen;
        int math;
        boolean changed;
        boolean changeHappened = false;
        int points = 0;

        for(j = numbOfCell-2; j >= 0; j--){
            for(i = 0; i < numbOfCell; i++){
                if(cells[i][j].getValue() == 0)
                    continue;

                chosen = cells[i][j];
                changed = false;

                for(int k = j+1; k < numbOfCell;k++){
                    if(cells[i][k].getValue() == 0)
                        continue;

                    if(cells[i][k].getValue() == chosen.getValue()){
                        math = cells[i][k].getValue() * 2;
                        cells[i][k].setValue(math);
                        cells[i][k].setColor(matchColor(math));
                        cells[i][j].resetCell();
                        score += math;
                        points += math;
                        changed = true;
                        changeHappened = true;
//                        if(isSoundOn === true) {
//                            theSound.play();
//                        }
//                        break;
                    }
                    else{
                        if((k-1) != j) {
                            animateRectRight(cells[i][j],cells[i][k - 1].getRect().left);
//                            cells[i][j].resetCell();
//                            cells[i][k - 1].resetCell();
//                            cells[i][k - 1].setValue(chosen.getValue());
//                            cells[i][k - 1].setColor(chosen.getColor());
                            changeHappened = true;
                        }
                        changed = true;
                        break;
                    }

                }

                if(changed == false){
                    // console.log("Vpravo: ")
                    animateRectRight(cells[i][j],cells[i][numbOfCell-1].getRect().left);
                    //cells[i][j].resetCell();
//                    cells[i][numbOfCell-1].setValue(chosen.getValue());
//                    cells[i][numbOfCell-1].setColor(chosen.getColor());
                    //cells[i][numbOfCell-1].resetCell();
                    changeHappened = true;
                }



            }
        }
//
//        if(points !== 0)
//            popupTheText(points);

//        if(changeHappened)
//            addNewCell();
//        else
//            checkLoose();
    }


    public void moveUp(){
        int i, j;
        Cell chosen;
        int math;
        boolean changed;
        boolean changeHappened = false;
        int points = 0;

        for(i = 1; i < numbOfCell; i++){
            for(j = 0; j < numbOfCell; j++){
                if(cells[i][j].getValue() == 0)
                    continue;

                chosen = cells[i][j];
                changed = false;

                for(int k = i-1; k >= 0;k--){
                    if(cells[k][j].getValue() == 0)
                        continue;

                    if(cells[k][j].getValue() == chosen.getValue()){
                        math = cells[k][j].getValue() * 2;
                        cells[k][j].setValue(math);
                        cells[k][j].setColor(matchColor(math));
                        cells[i][j].resetCell();
                        score += math;
                        points += math;
                        changed = true;
                        changeHappened = true;
                        /*
                        if(isSoundOn === true) {
                            theSound.play();
                        }
                        */
                        break;
                    }
                    else{
                        if((k+1) != i) {
                            cells[k + 1][j].setValue(chosen.getValue());
                            cells[k + 1][j].setColor(chosen.getColor());
                            cells[i][j].resetCell();
                            changeHappened = true;
                        }
                        changed = true;
                        break;
                    }

                }

                if(changed == false){
                    cells[0][j].setValue(chosen.getValue());
                    cells[0][j].setColor(chosen.getColor());
                    changeHappened = true;
                    cells[i][j].resetCell();
                }



            }
        }
//
//        if(points !== 0)
//            popupTheText(points);

        if(changeHappened)
            addNewCell();
//        else
//            checkLoose();
    }


    public void moveDown() {
        int i, j;
        Cell chosen;
        int math;
        boolean changed;
        boolean changeHappened = false;
        int points = 0;

        for(i = numbOfCell-2; i >= 0; i--){
            for(j = 0; j < numbOfCell; j++){
                if(cells[i][j].getValue() == 0)
                    continue;

                chosen = cells[i][j];
                changed = false;

                for(int k = i+1; k < numbOfCell;k++){
                    if(cells[k][j].getValue() == 0)
                        continue;

                    if(cells[k][j].getValue() == chosen.getValue()){
                        math = cells[k][j].getValue() * 2;
                        cells[k][j].setValue(math);
                        cells[k][j].setColor(matchColor(math));
                        cells[i][j].resetCell();
                        score += math;
                        points += math;
                        changed = true;
                        changeHappened = true;
//                        if(isSoundOn === true)
//                            theSound.play();
//                        break;
                    }
                    else{
                        if((k-1) != i) {
                            cells[k - 1][j].setValue(chosen.getValue());
                            cells[k - 1][j].setColor(chosen.getColor());
                            cells[i][j].resetCell();
                            changeHappened = true;
                        }
                        changed = true;
                        break;
                    }

                }

                if(changed == false){
                    // console.log("Nahore: ")
                    cells[numbOfCell-1][j].setValue(chosen.getValue());
                    cells[numbOfCell-1][j].setColor(chosen.getColor());
                    changeHappened = true;
                    cells[i][j].resetCell();
                }



            }
        }

//        if(points !== 0)
//            popupTheText(points);

        if(changeHappened)
            addNewCell();
//        else
//            checkLoose();
    }


    public void moveLeft(){
        int i, j;
        Cell chosen;
        int math;
        boolean changed;
        boolean changeHappened = false;
        int points = 0;

        for(j = 1; j < numbOfCell; j++){
            for(i = 0; i < numbOfCell; i++){
                if(cells[i][j].getValue() == 0)
                    continue;

                chosen = cells[i][j];
                changed = false;

                for(int k = j-1; k >= 0;k--){
                    if(cells[i][k].getValue() == 0)
                        continue;

                    if(cells[i][k].getValue() == chosen.getValue()){
                        math = cells[i][k].getValue() * 2;
                        cells[i][k].setValue(math);
                        cells[i][k].setColor(matchColor(math));
                        cells[i][j].resetCell();
                        score += math;
                        points += math;
                        changed = true;
                        changeHappened = true;
//                        if(isSoundOn === true)
//                            theSound.play();
//                        break;
                    }
                    else{
                        if((k+1) != j) {
                            cells[i][k + 1].setValue(chosen.getValue());
                            cells[i][k + 1].setColor(chosen.getColor());
                            cells[i][j].resetCell();
                            changeHappened = true;
                        }
                        changed = true;
                        break;
                    }

                }

                if(changed == false){
                    cells[i][0].setValue(chosen.getValue());
                    cells[i][0].setColor(chosen.getColor());
                    changeHappened = true;
                    cells[i][j].resetCell();
                }



            }
        }

//        if(points !== 0)
//            popupTheText(points);

        if(changeHappened)
            addNewCell();
//        else
//            checkLoose();
    }
/*

    public void moveRight(){
        int i, j;
        Cell chosen;
        int math;
        boolean changed;
        boolean changeHappened = false;
        int points = 0;

        for(j = numbOfCell-2; j >= 0; j--){
            for(i = 0; i < numbOfCell; i++){
                if(cells[i][j].getValue() == 0)
                    continue;

                chosen = cells[i][j];
                changed = false;

                for(int k = j+1; k < numbOfCell;k++){
                    if(cells[i][k].getValue() == 0)
                        continue;

                    if(cells[i][k].getValue() == chosen.getValue()){
                        math = cells[i][k].getValue() * 2;
                        cells[i][k].setValue(math);
                        cells[i][k].setColor(matchColor(math));
                        cells[i][j].resetCell();
                        score += math;
                        points += math;
                        changed = true;
                        changeHappened = true;
//                        if(isSoundOn === true) {
//                            theSound.play();
//                        }
//                        break;
                    }
                    else{
                        if((k-1) != j) {
                            cells[i][k - 1].setValue(chosen.getValue());
                            cells[i][k - 1].setColor(chosen.getColor());
                            cells[i][j].resetCell();
                            changeHappened = true;
                        }
                        changed = true;
                        break;
                    }

                }

                if(changed == false){
                    // console.log("Vpravo: ")
                    cells[i][numbOfCell-1].setValue(chosen.getValue());
                    cells[i][numbOfCell-1].setColor(chosen.getColor());
                    changeHappened = true;
                    cells[i][j].resetCell();
                }



            }
        }
*/


}
