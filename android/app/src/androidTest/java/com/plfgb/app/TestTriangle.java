package com.plfgb.app;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import org.hamcrest.Matcher;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import com.plfgb.app.Client.Client;
import com.plfgb.app.Client.Connexion;
import Score.AttributionScoreTriangle;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;


@RunWith(MockitoJUnitRunner.class)
public class TestTriangle {

    private Client client;

    @Mock
    Connexion connexion = Mockito.mock(Connexion.class);


    DrawingCanvas drawingCanvas;

    private static final boolean PAUSE =false ;

    public static ViewAction singleTouchAt(final int x, final int y, final int action) {
        return new ViewAction() {
            @Override
            public void perform(UiController uiController, View view) {
                long date = SystemClock.uptimeMillis();
                MotionEvent me = MotionEvent.obtain(date, date, action, x, y, 0);
                view.onTouchEvent(me);
            }

            @Override
            public String getDescription() {
                return "touch ";
            }

            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(View.class);
            }};}



    @Rule
    public IntentsTestRule<DrawingTriangle> intentsTestRule = new IntentsTestRule<>(DrawingTriangle.class);

    @Before
    public void setUp() throws Exception {
        client = new Client();
        client.setConnexion(connexion);
        client.setLogin("testLogin");
        client.setScore(0);
        GlobalState gs = (GlobalState) intentsTestRule.getActivity().getApplicationContext();
        gs.setClient(client);
        drawingCanvas = intentsTestRule.getActivity().findViewById(R.id.drawingCanvas5);

    }

    public void moveToPointX(int oldX, int newX, int y){
        for (int i=oldX;i<=newX;i++){
            onView(withId(R.id.drawingCanvas5)).perform(singleTouchAt(i, y, MotionEvent.ACTION_MOVE));

        }

    }

    public void moveToPointXDown(int oldX, int newX, int y){
        for (int i=oldX;i>=newX;i--){
            onView(withId(R.id.drawingCanvas5)).perform(singleTouchAt(i, y, MotionEvent.ACTION_MOVE));

        }

    }

    public void moveToPointY(int oldY,int newY, int x){
        for (int i=oldY;i<=newY;i++){
            onView(withId(R.id.drawingCanvas5)).perform(singleTouchAt(x, i, MotionEvent.ACTION_MOVE));

        }
    }

    public void moveToPointYDown(int oldY,int newY, int x){
        for (int i=oldY;i>=newY;i--){
            onView(withId(R.id.drawingCanvas5)).perform(singleTouchAt(x, i, MotionEvent.ACTION_MOVE));

        }
    }

    public void movePointXAndY(int oldY,int oldX, int newX){
        int j=oldY;
        for (int i=oldX;i<=newX;i++){
            onView(withId(R.id.drawingCanvas5)).perform(singleTouchAt(i, j, MotionEvent.ACTION_MOVE));
            j++;

        }
    }

    public void movePointXAndYDown(int oldY,int oldX, int newX){
        int j=oldY;
        for (int i=oldX;i>=newX;i--){
            onView(withId(R.id.drawingCanvas5)).perform(singleTouchAt(i, j, MotionEvent.ACTION_MOVE));
            j--;

        }
    }



    @Test
    public void perfectTriangle() throws Exception {
        int x=100;
        int y=100;

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                AttributionScoreTriangle triangleScore= new AttributionScoreTriangle(drawingCanvas.getShapePoint().getPoints());
                triangleScore.calculateScore();
                client.setScore(triangleScore.getScore());
                return null;
            }
        }).when(connexion).emitShape(any(JSONObject.class));


        onView(withId(R.id.drawingCanvas5)).perform(singleTouchAt(x, y, MotionEvent.ACTION_DOWN));
        moveToPointX(x,x*2,y);
        onView(withId(R.id.drawingCanvas5)).perform(singleTouchAt(x, y, MotionEvent.ACTION_DOWN));
        movePointXAndY(y,x,x+50);
        int j=y+50;
        for (int i=x+50;i<=x*2;i++){
            onView(withId(R.id.drawingCanvas5)).perform(singleTouchAt(i, j--, MotionEvent.ACTION_MOVE));
        }

        connexion.emitShape(drawingCanvas.getShapePoint().convertToJson());
        assertEquals(true,client.getScore()==3);


    }

    @Test
    public void perfectTriangleScaleChanged() throws Exception {
        int x=50;
        int y=50;

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                AttributionScoreTriangle triangleScore= new AttributionScoreTriangle(drawingCanvas.getShapePoint().getPoints());
                triangleScore.calculateScore();
                client.setScore(triangleScore.getScore());
                return null;
            }
        }).when(connexion).emitShape(any(JSONObject.class));


        onView(withId(R.id.drawingCanvas5)).perform(singleTouchAt(x, y, MotionEvent.ACTION_DOWN));
        moveToPointX(x,x*2,y);
        onView(withId(R.id.drawingCanvas5)).perform(singleTouchAt(x, y, MotionEvent.ACTION_DOWN));
        movePointXAndY(y,x,x+25);
        int j=y+25;
        for (int i=x+25;i<=x*2;i++){
            onView(withId(R.id.drawingCanvas5)).perform(singleTouchAt(i, j--, MotionEvent.ACTION_MOVE));
        }

        connexion.emitShape(drawingCanvas.getShapePoint().convertToJson());
        assertEquals(true,client.getScore()==3);


    }

    @Test
    public void badTriangle() throws Exception {
        int x=100;
        int y=100;

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                AttributionScoreTriangle triangleScore= new AttributionScoreTriangle(drawingCanvas.getShapePoint().getPoints());
                triangleScore.calculateScore();
                client.setScore(triangleScore.getScore());
                return null;
            }
        }).when(connexion).emitShape(any(JSONObject.class));


        onView(withId(R.id.drawingCanvas5)).perform(singleTouchAt(x, y, MotionEvent.ACTION_DOWN));
        moveToPointX(x,x*2,y);
        onView(withId(R.id.drawingCanvas5)).perform(singleTouchAt(x, y, MotionEvent.ACTION_DOWN));
        movePointXAndY(y,x,x+50);


        connexion.emitShape(drawingCanvas.getShapePoint().convertToJson());
        assertEquals(true,client.getScore()==-1);


    }



}