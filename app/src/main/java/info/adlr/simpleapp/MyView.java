package info.adlr.simpleapp;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by adlr on 9/24/15.
 */
public class MyView extends View {

    public TextView out_field;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawRGB(255, 255, 200);
    }

    TextView textView;
    Deque<String> textChunks = new LinkedList<String>();
    public void setTextView(TextView view) {
        textView = view;
    }
    private void pushText(String text) {
        textChunks.addLast(text);
        if (textChunks.size() > 7) {
            textChunks.removeFirst();
        }
        if (textView == null)
            return;
        String val = new String();
        for (Iterator i = textChunks.iterator(); i.hasNext();) {
            String piece = (String)i.next();
            val += piece;
            if (i.hasNext())
                val += "\n\n";
        }
        textView.setText(val);
    }
    void Clear() {
        textChunks.clear();
        pushText("");
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        pushText(event.toString());
        //Log.d("SimpleAppEvt", event.toString());
        return true;
        //return super.onGenericMotionEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        pushText(event.toString());
        //Log.d("SimpleAppEvt", event.toString());
        return true;
        //return super.onTouchEvent(event);
    }
}
