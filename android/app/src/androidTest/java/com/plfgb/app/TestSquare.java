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
import Score.AttributionScoreSquare;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;


@RunWith(MockitoJUnitRunner.class)
public class TestSquare {

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
    public IntentsTestRule<DrawingSquare> intentsTestRule = new IntentsTestRule<>(DrawingSquare.class);

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

    public void moveToPointY(int oldY,int newY, int x){
        for (int i=oldY;i<=newY;i++){
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

    @Test
    public void perfectSquare() throws Exception {

        int x=25;
        int y=25;

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {

                AttributionScoreSquare squareScore= new AttributionScoreSquare(drawingCanvas.getShapePoint().getPoints());
                squareScore.calculateScore();
                client.setScore(squareScore.getScore());
                return null;
            }
        }).when(connexion).emitShape(any(JSONObject.class));


        onView(withId(R.id.drawingCanvas5)).perform(singleTouchAt(x, y, MotionEvent.ACTION_DOWN));
        moveToPointX(x,x*2,y);
        moveToPointY(y,y*2,x*2);
        onView(withId(R.id.drawingCanvas5)).perform(singleTouchAt(x, y*2, MotionEvent.ACTION_DOWN));
        moveToPointX(x,x*2,y*2);
        onView(withId(R.id.drawingCanvas5)).perform(singleTouchAt(x, y, MotionEvent.ACTION_DOWN));
        moveToPointY(y,y*2,x);

        connexion.emitShape(drawingCanvas.getShapePoint().convertToJson());

        assertEquals(true,client.getScore()==3);

    }

    @Test
    public void perfectSquareAugmentedScale() throws Exception {

        int x=75;
        int y=75;

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {

                AttributionScoreSquare squareScore= new AttributionScoreSquare(drawingCanvas.getShapePoint().getPoints());
                squareScore.calculateScore();
                client.setScore(squareScore.getScore());
                return null;
            }
        }).when(connexion).emitShape(any(JSONObject.class));


        onView(withId(R.id.drawingCanvas5)).perform(singleTouchAt(x, y, MotionEvent.ACTION_DOWN));
        moveToPointX(x,x*2,y);
        moveToPointY(y,y*2,x*2);
        onView(withId(R.id.drawingCanvas5)).perform(singleTouchAt(x, y*2, MotionEvent.ACTION_DOWN));
        moveToPointX(x,x*2,y*2);
        onView(withId(R.id.drawingCanvas5)).perform(singleTouchAt(x, y, MotionEvent.ACTION_DOWN));
        moveToPointY(y,y*2,x);

        connexion.emitShape(drawingCanvas.getShapePoint().convertToJson());

        assertEquals(true,client.getScore()==3);

    }


    @Test
    public void testPerfectSquareRotated() throws Exception {

        int x=50;
        int y=50;

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {

                AttributionScoreSquare squareScore= new AttributionScoreSquare(drawingCanvas.getShapePoint().getPoints());
                squareScore.calculateScore();
                client.setScore(squareScore.getScore());
                return null;
            }
        }).when(connexion).emitShape(any(JSONObject.class));


        onView(withId(R.id.drawingCanvas5)).perform(singleTouchAt(x, y, MotionEvent.ACTION_DOWN));
        movePointXAndY(y,x,x*2);
        onView(withId(R.id.drawingCanvas5)).perform(singleTouchAt(x, y*2, MotionEvent.ACTION_DOWN));
        movePointXAndY(y*2,x,x*2);
        onView(withId(R.id.drawingCanvas5)).perform(singleTouchAt(x*2, y*2, MotionEvent.ACTION_DOWN));
        moveToPointY(y*2,y*3,x*2);
        onView(withId(R.id.drawingCanvas5)).perform(singleTouchAt(x, y, MotionEvent.ACTION_DOWN));
        moveToPointY(y,y*2,x);

        connexion.emitShape(drawingCanvas.getShapePoint().convertToJson());

        assertEquals(true,client.getScore()==3);

    }


}