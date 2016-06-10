package com.medialab.android_gles_sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.medialab.android_gles_sample.renderer.BasicRenderer;

public abstract class SampleView extends Activity {
    private GLView mGLView;
    private GLViewCallback mGLViewCallback;
    protected BasicRenderer mRenderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGLView = new GLView(this);
        mGLViewCallback = new GLViewCallback(this);
        mRenderer = new BasicRenderer();


        mGLView.setRenderer(mGLViewCallback);
        setContentView(mGLView);
        addUi();

        Button forward =  (Button)findViewById(R.id.button1);
        Button back =  (Button)findViewById(R.id.button2);
        Button up =  (Button)findViewById(R.id.button3);
        Button down =  (Button)findViewById(R.id.button4);
        Button left =  (Button)findViewById(R.id.button5);
        Button right =  (Button)findViewById(R.id.button6);

        forward.setOnTouchListener(mTouchEvent);
        back.setOnTouchListener(mTouchEvent);
        up.setOnTouchListener(mTouchEvent);
        down.setOnTouchListener(mTouchEvent);
        left.setOnTouchListener(mTouchEvent);
        right.setOnTouchListener(mTouchEvent);

    }

    @Override
    protected void onPause() {
        mGLView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mGLView.onResume();
        super.onResume();
    }

    public void addUi() {
        View btnLayout = getLayoutInflater().inflate(R.layout.sample_ui, null);
        this.addContentView(btnLayout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));


        mGLViewCallback.tvFpsText  = (TextView) findViewById(R.id.tvFps);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e){

        switch (e.getAction()) {
            case MotionEvent.ACTION_UP:
                mRenderer.TouchOff();
                break;

            case MotionEvent.ACTION_MOVE:
                mRenderer.SetTouchPoint(e.getX(), e.getY());
                break;

            case MotionEvent.ACTION_DOWN:
                mRenderer.TouchOn();
                mRenderer.SetTouchPoint(e.getX(), e.getY());
                break;
        }

        return super.onTouchEvent(e);
    }

    private Button.OnTouchListener mTouchEvent = new Button.OnTouchListener()
    {
        @Override
        public boolean onTouch(View v, MotionEvent e)
        {
            if(e.getAction() == MotionEvent.ACTION_DOWN)
            {
                switch (v.getId())
                {
                    case R.id.button1:
                        mRenderer.GetCamera().MoveForward(1);
                        break;
                    case R.id.button2:
                        mRenderer.GetCamera().MoveBackward(1);
                        break;
                    case R.id.button3:
                        //mRenderer.GetCamera().MoveLeft(1);
                        mRenderer.GetCamera().RotateLeft(5);
                        break;
                    case R.id.button4:
                        //mRenderer.GetCamera().MoveRight(1);
                        mRenderer.GetCamera().RotateRight(5);
                        break;
                    case R.id.button5:
                        //mRenderer.GetCamera().MoveUp(1);
                        mRenderer.GetCamera().RotateUp(5);
                        break;
                    case R.id.button6:
                        //mRenderer.GetCamera().MoveDown(1);
                        mRenderer.GetCamera().RotateDown(5);
                        break;
                }
            }
            return true;

        }

    };






    protected abstract void OnInit();

    protected void OnWindowUpdate(int w, int h)
    {
        mRenderer.SetViewPort(w, h);
    }

    protected void OnStep()
    {
        mRenderer.RenderFrame();
    }


}
