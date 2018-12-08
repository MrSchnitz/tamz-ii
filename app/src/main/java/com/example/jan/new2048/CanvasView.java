package com.example.jan.new2048;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Model.Cell;
import listeners.OnSwipeTouchListener;

import static Model.Cell.numbOfCell;

public class CanvasView extends View {

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


    TextView scoreLabel;
    public void setScoreLabel(TextView scoreLabel){
        this.scoreLabel = scoreLabel;
        scoreLabel.setText("Score: " + score);
    }
    int score = 0;

    PopupWindow popUp = new PopupWindow(this);

    Cell[][] cells = new Cell[numbOfCell][];

    List<Cell> animatedCells = new ArrayList<>();

    Cell cell;

    boolean animate = false;

    int counter = 0;

    Bitmap[] bmp;

    int lx = 10;
    int ly = 10;

    int width;
    int height;

    int screenWidth = 0;
    int screenHeight = 0;

    int canvasX, canvasY, canvasWidth, canvasHeight;

    boolean victory = false;

    boolean init = true;


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

            animateRectScale(chosenCell);

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



//        cell = new Cell(0,0,"#FF7F50",canvasWidth,canvasX, canvasY);

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

        if(!animatedCells.isEmpty()){

            for (Cell c : animatedCells){
                c.drawCell(canvas);
                Log.d("Animate cell","ANIMATE " + animatedCells.size());
            }
        }

    }


    public void changeScore(int points){
        score += points;
        scoreLabel.setText("Score: " + score);
    }


    public void animateRectScale(Cell cell){
        float translateScale = 40;

        ObjectAnimator animateScaleLeft = ObjectAnimator.ofFloat(cell.getRect(), "left", cell.getRect().left, cell.getX()- translateScale);
        ObjectAnimator animateScaleRight = ObjectAnimator.ofFloat(cell.getRect(), "right", cell.getRect().right, cell.getW()+ translateScale);
        ObjectAnimator animateScaleTop = ObjectAnimator.ofFloat(cell.getRect(), "top", cell.getRect().top, cell.getY()- translateScale);
        ObjectAnimator animateScaleBottom = ObjectAnimator.ofFloat(cell.getRect(), "bottom", cell.getRect().bottom, cell.getH()+ translateScale);

        ObjectAnimator animateScaleLeftBack = ObjectAnimator.ofFloat(cell.getRect(), "left", cell.getRect().left- translateScale, cell.getX());
        ObjectAnimator animateScaleRightBack = ObjectAnimator.ofFloat(cell.getRect(), "right", cell.getRect().right+ translateScale, cell.getW());
        ObjectAnimator animateScaleTopBack = ObjectAnimator.ofFloat(cell.getRect(), "top", cell.getRect().top- translateScale, cell.getY());
        ObjectAnimator animateScaleBottomBack = ObjectAnimator.ofFloat(cell.getRect(), "bottom", cell.getRect().bottom+ translateScale, cell.getH());

        animateScaleLeft.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                postInvalidate();
            }
        });

        animateScaleLeftBack.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                postInvalidate();
            }
        });

        AnimatorSet scaleRectAnimation = new AnimatorSet();
        scaleRectAnimation.playTogether(animateScaleLeft, animateScaleRight, animateScaleTop, animateScaleBottom);
        scaleRectAnimation.setDuration(300).start();


        final AnimatorSet scaleRectAnimationBack = new AnimatorSet();
        scaleRectAnimationBack.playTogether(animateScaleLeftBack, animateScaleRightBack, animateScaleTopBack, animateScaleBottomBack);

        scaleRectAnimation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                scaleRectAnimationBack.setDuration(300).start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    public void animateRectMatchCell(Cell cell){
        float translateScale = 50;

        ObjectAnimator animateScaleLeft = ObjectAnimator.ofFloat(cell.getRect(), "left", cell.getRect().left+ translateScale, cell.getX());
        ObjectAnimator animateScaleRight = ObjectAnimator.ofFloat(cell.getRect(), "right", cell.getRect().right- translateScale, cell.getW());
        ObjectAnimator animateScaleTop = ObjectAnimator.ofFloat(cell.getRect(), "top", cell.getRect().top+ translateScale, cell.getY());
        ObjectAnimator animateScaleBottom = ObjectAnimator.ofFloat(cell.getRect(), "bottom", cell.getRect().bottom- translateScale, cell.getH());

        animateScaleLeft.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                postInvalidate();
            }
        });

        AnimatorSet scaleRectAnimation = new AnimatorSet();
        scaleRectAnimation.playTogether(animateScaleLeft, animateScaleRight, animateScaleTop, animateScaleBottom);
        scaleRectAnimation.setDuration(300).start();
    }

    public void animateRectVertically(Cell cell, float translateTop, float translateBottom){

        ObjectAnimator animateTop = ObjectAnimator.ofFloat(cell.getRect(), "top", cell.getRect().top, translateTop);
        ObjectAnimator animateBottom = ObjectAnimator.ofFloat(cell.getRect(), "bottom", cell.getRect().bottom, translateBottom);

        animateTop.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                postInvalidate();
                //animatedCells.clear();
            }
        });

//        animateTop.setDuration(500).start();
//        animateBottom.setDuration(500).start();

        AnimatorSet rectAnimation = new AnimatorSet();
        rectAnimation.playTogether(animateBottom, animateTop);
        rectAnimation.setDuration(300).start();

        rectAnimation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Toast.makeText(getContext(), "animation ended", Toast.LENGTH_LONG).show();
                animatedCells.clear();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    public void animateRectHorizontally(Cell cell, float translateLeft, float translateRight){
        //float translateX = 200.0f;


        ObjectAnimator animateRight = ObjectAnimator.ofFloat(cell.getRect(), "right", cell.getRect().right, translateRight);
        ObjectAnimator animateLeft = ObjectAnimator.ofFloat(cell.getRect(), "left", cell.getRect().left, translateLeft);

        animateRight.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                postInvalidate();
                //animatedCells.clear();
            }
        });

//        animateRight.setDuration(500).start();
//        animateLeft.setDuration(500).start();

        AnimatorSet rectAnimation = new AnimatorSet();
        rectAnimation.playTogether(animateLeft, animateRight);
        rectAnimation.setDuration(300).start();

        rectAnimation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Toast.makeText(getContext(), "animation ended", Toast.LENGTH_LONG).show();
                animatedCells.clear();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

//    public void animateRight(Cell cell, float translateX, float translateW) throws InterruptedException {
//        int incrementX = (int)(translateX - cell.getX())/20;
//        int incrementW = (int)(translateW - cell.getW())/20;
//
//        Log.d("incerement",incrementX + " " + incrementW);
//
//        for(int i = cell.getX(); i < translateX; i++){
//            cell.setX(cell.getX()+1);
//            cell.setW(cell.getW()+1);
//            invalidate();
//            SystemClock.sleep(5000);
//            //Thread.sleep(200);
//        }
//
//    }

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

                chosen = new Cell(cells[i][j]);
                changed = false;

                for(int k = j+1; k < numbOfCell;k++){
                    if(cells[i][k].getValue() == 0)
                        continue;

                    if(cells[i][k].getValue() == chosen.getValue()){
                        //animate move
                        Cell c = new Cell(i,j, cells[i][j].getColor(),canvasWidth,canvasX, canvasY);
                        animatedCells.add(c);
                        animateRectHorizontally(c, cells[i][k].getX(), cells[i][k].getW());
                        //animate new born cell
                        animateRectMatchCell(cells[i][k]);

                        math = cells[i][k].getValue() * 2;
                        cells[i][k].setValue(math);
                        cells[i][k].setColor(matchColor(math));
                        cells[i][j].resetCell();
                        changeScore(math);
                        points += math;
                        changed = true;
                        changeHappened = true;


//                        if(isSoundOn === true) {
//                            theSound.play();
//                        }
                        break;
                    }
                    else{
                        if((k-1) != j) {
                            //animate move
                            Cell c = new Cell(i,j, cells[i][j].getColor(),canvasWidth,canvasX, canvasY);
                            animatedCells.add(c);
                            animateRectHorizontally(c, cells[i][k-1].getX(), cells[i][k-1].getW());

                            cells[i][k - 1].setValue(chosen.getValue());
                            cells[i][k - 1].setColor(chosen.getColor());
                            cells[i][j].resetCell();
                            changeHappened = true;
                        }
                        changed = true;
                        break;
                    }

                }

                if(!changed){
                    //animate move
                    Cell c = new Cell(i,j, cells[i][j].getColor(),canvasWidth,canvasX, canvasY);
                    animatedCells.add(c);
                    animateRectHorizontally(c, cells[i][numbOfCell-1].getX(), cells[i][numbOfCell-1].getW());

                    cells[i][numbOfCell-1].setValue(chosen.getValue());
                    cells[i][numbOfCell-1].setColor(chosen.getColor());
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

                chosen = new Cell(cells[i][j]);
                changed = false;

                for(int k = i-1; k >= 0;k--){
                    if(cells[k][j].getValue() == 0)
                        continue;

                    if(cells[k][j].getValue() == chosen.getValue()){
                        //animate move
                        Cell c = new Cell(i,j, cells[i][j].getColor(),canvasWidth,canvasX, canvasY);
                        animatedCells.add(c);
                        animateRectVertically(c, cells[k][j].getY(), cells[k][j].getH());
                        //animate new born cell
                        animateRectMatchCell(cells[k][j]);

                        math = cells[k][j].getValue() * 2;
                        cells[k][j].setValue(math);
                        cells[k][j].setColor(matchColor(math));
                        cells[i][j].resetCell();
                        changeScore(math);
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
                            //animate move
                            Cell c = new Cell(i,j, cells[i][j].getColor(),canvasWidth,canvasX, canvasY);
                            animatedCells.add(c);
                            animateRectVertically(c, cells[k+1][j].getY(), cells[k+1][j].getH());

                            cells[k + 1][j].setValue(chosen.getValue());
                            cells[k + 1][j].setColor(chosen.getColor());
                            cells[i][j].resetCell();
                            changeHappened = true;
                        }
                        changed = true;
                        break;
                    }

                }

                if(!changed){
                    //animate move
                    Cell c = new Cell(i,j, cells[i][j].getColor(),canvasWidth,canvasX, canvasY);
                    animatedCells.add(c);
                    animateRectVertically(c, cells[0][j].getY(), cells[0][j].getH());

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

                chosen = new Cell(cells[i][j]);
                changed = false;

                for(int k = i+1; k < numbOfCell;k++){
                    if(cells[k][j].getValue() == 0)
                        continue;

                    if(cells[k][j].getValue() == chosen.getValue()){
                        //animate move
                        Cell c = new Cell(i,j, cells[i][j].getColor(),canvasWidth,canvasX, canvasY);
                        animatedCells.add(c);
                        animateRectVertically(c, cells[k][j].getY(), cells[k][j].getH());
                        //animate new born cell
                        animateRectMatchCell(cells[k][j]);

                        math = cells[k][j].getValue() * 2;
                        cells[k][j].setValue(math);
                        cells[k][j].setColor(matchColor(math));
                        cells[i][j].resetCell();
                        changeScore(math);
                        points += math;
                        changed = true;
                        changeHappened = true;
//                        if(isSoundOn === true)
//                            theSound.play();
//                        break;
                    }
                    else{
                        if((k-1) != i) {
                            //animate move
                            Cell c = new Cell(i,j, cells[i][j].getColor(),canvasWidth,canvasX, canvasY);
                            animatedCells.add(c);
                            animateRectVertically(c, cells[k-1][j].getY(), cells[k-1][j].getH());

                            cells[k - 1][j].setValue(chosen.getValue());
                            cells[k - 1][j].setColor(chosen.getColor());
                            cells[i][j].resetCell();
                            changeHappened = true;
                        }
                        changed = true;
                        break;
                    }

                }

                if(!changed){
                    //animate move
                    Cell c = new Cell(i,j, cells[i][j].getColor(),canvasWidth,canvasX, canvasY);
                    animatedCells.add(c);
                    animateRectVertically(c, cells[numbOfCell-1][j].getY(), cells[numbOfCell-1][j].getH());

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

                chosen = new Cell(cells[i][j]);
                changed = false;

                for(int k = j-1; k >= 0;k--){
                    if(cells[i][k].getValue() == 0)
                        continue;

                    if(cells[i][k].getValue() == chosen.getValue()){
                        //animate move
                        Cell c = new Cell(i,j, cells[i][j].getColor(),canvasWidth,canvasX, canvasY);
                        animatedCells.add(c);
                        animateRectHorizontally(c, cells[i][k].getX(), cells[i][k].getW());
                        //animate new born cell
                        animateRectMatchCell(cells[i][k]);

                        math = cells[i][k].getValue() * 2;
                        cells[i][k].setValue(math);
                        cells[i][k].setColor(matchColor(math));
                        cells[i][j].resetCell();
                        changeScore(math);
                        points += math;
                        changed = true;
                        changeHappened = true;
//                        if(isSoundOn === true)
//                            theSound.play();
//                        break;
                    }
                    else{
                        if((k+1) != j) {
                            //animate move
                            Cell c = new Cell(i,j, cells[i][j].getColor(),canvasWidth,canvasX, canvasY);
                            animatedCells.add(c);
                            animateRectHorizontally(c, cells[i][k + 1].getX(), cells[i][k + 1].getW());

                            cells[i][k + 1].setValue(chosen.getValue());
                            cells[i][k + 1].setColor(chosen.getColor());
                            cells[i][j].resetCell();
                            changeHappened = true;
                        }
                        changed = true;
                        break;
                    }

                }

                if(!changed){
                    //animate move
                    Cell c = new Cell(i,j, cells[i][j].getColor(),canvasWidth,canvasX, canvasY);
                    animatedCells.add(c);
                    animateRectHorizontally(c, cells[i][0].getX(), cells[i][0].getW());

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
