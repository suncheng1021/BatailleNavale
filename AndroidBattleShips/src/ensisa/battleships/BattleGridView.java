package ensisa.battleships;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class BattleGridView extends View {

	private Paint paint;

	private abstract class Color {
		public static final int BLUE = 0xFF3333FF;
		public static final int WHITE = 0xFFFFFFFF;
		public static final int RED = 0xFFFF0000;
		public static final int GRAY = 0xFF333333;
	}

	public BattleGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public BattleGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public BattleGridView(Context context) {
		super(context);
		init(context);
	}

	public void init(Context context) {
		this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// Trace la mer
		this.paint.setColor(Color.BLUE);
		this.paint.setStyle(Style.FILL);
		canvas.drawRect(0, 0, this.getWidth(), this.getWidth(), this.paint);
		
		// Trace la grille
		this.paint.setColor(Color.GRAY);
		int dx = this.getWidth()/10;
		
		for (int i=0; i <= this.getWidth(); i=i+dx) {
			canvas.drawLine(i,0,i, this.getWidth(), this.paint);
			canvas.drawLine(0,i,this.getWidth(), i, this.paint);
		}

		//Trace les pions
		int i = 3;
		int j = 4;
		
		this.paint.setColor(Color.RED);
	    canvas.drawCircle(dx/2+i*dx,dx/2+j*dx, dx/5, this.paint);
	    
		i = 5;
		j = 8;
		
		this.paint.setColor(Color.WHITE);
	    canvas.drawCircle(dx/2+i*dx,dx/2+j*dx, dx/5, this.paint);

		
	}

}
