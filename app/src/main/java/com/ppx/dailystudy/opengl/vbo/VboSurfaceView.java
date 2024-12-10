package com.ppx.dailystudy.opengl.vbo;

import android.content.Context;
import android.util.AttributeSet;

<<<<<<< HEAD
=======
import com.ppx.dailystudy.opengl.FboUseRender;
import com.ppx.dailystudy.opengl.MyBitmapOrthogonalRender;
>>>>>>> 2539aee (add usage of bitmapOrthogonal)
import com.ppx.dailystudy.opengl.shape.CustomGLSurfaceView;

/**
 * @Author Shirley
 * @Date：2023/10/27
 * @Desc：
 */
public class VboSurfaceView extends CustomGLSurfaceView {

    public VboSurfaceView(Context context) {
        super(context);
    }

    public VboSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        VboRender render = new VboRender(context);
<<<<<<< HEAD
        setRender(render);
=======
        FboUseRender fboUseRender = new FboUseRender(context,900,1920);
        MyBitmapOrthogonalRender myBitmapOrthogonalRender = new MyBitmapOrthogonalRender(context);
        setRender(myBitmapOrthogonalRender);
>>>>>>> 2539aee (add usage of bitmapOrthogonal)
    }
}

