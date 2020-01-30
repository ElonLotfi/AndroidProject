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
import Score.AttributionScore;
import Score.AttributionScoreSegment;
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
public class TestSegment {

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
    public IntentsTestRule<DrawingActivity> intentsTestRule = new IntentsTestRule<>(DrawingActivity.class);

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

    @Test
    public void testPerfectHorizontalSegment() throws Exception {

        int x = 200;
        final AttributionScore[] attributionScore = new AttributionScore[1];

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                attributionScore[0] =new AttributionScoreSegment(drawingCanvas.getShapePoint().getPoints());
                attributionScore[0].calculateScore();
                client.setScore(attributionScore[0].getScore());

                return null;
            }
        }).when(connexion).emitShape(any(JSONObject.class));

        onView(withId(R.id.drawingCanvas5)).perform(singleTouchAt(x, 200, MotionEvent.ACTION_DOWN));
        moveToPointX(200,400,200);

        onView(withId(R.id.button)).perform(click());
        connexion.emitShape(drawingCanvas.getShapePoint().convertToJson());

        GlobalState gs = (GlobalState) intentsTestRule.getActivity().getApplicationContext();
        assertEquals(true,gs.getClient().getScore()== attributionScore[0].getMAX_SCORE());

    }

    @Test
    public void testBadSegment() throws Exception {

        int x = 200;

        final AttributionScore[] attributionScore = new AttributionScore[1];

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                attributionScore[0] = new AttributionScoreSegment(drawingCanvas.getShapePoint().getPoints());
                attributionScore[0].calculateScore();
                client.setScore(attributionScore[0].getScore());
                return null;
            }
        }).when(connexion).emitShape(any(JSONObject.class));

        onView(withId(R.id.drawingCanvas5)).perform(singleTouchAt(x, 200, MotionEvent.ACTION_DOWN));
        moveToPointX(200,400,200);
        moveToPointY(200,250,400);

        onView(withId(R.id.button)).perform(click());
        connexion.emitShape(drawingCanvas.getShapePoint().convertToJson());

        GlobalState gs = (GlobalState) intentsTestRule.getActivity().getApplicationContext();
        assertEquals(true,gs.getClient().getScore()== -1);

    }


    @Test
    public void testPerfectVerticalSegment() throws Exception {

        int x = 200;
        int y = 200;

        final AttributionScore[] attributionScore = new AttributionScore[1];

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                attributionScore[0] = new AttributionScoreSegment(drawingCanvas.getShapePoint().getPoints());
                attributionScore[0].calculateScore();
                client.setScore(attributionScore[0].getScore());
                return null;
            }
        }).when(connexion).emitShape(any(JSONObject.class));

        onView(withId(R.id.drawingCanvas5)).perform(singleTouchAt(x, y, MotionEvent.ACTION_DOWN));
        moveToPointY(y,y+200,x);

        onView(withId(R.id.button)).perform(click());
        connexion.emitShape(drawingCanvas.getShapePoint().convertToJson());

        GlobalState gs = (GlobalState) intentsTestRule.getActivity().getApplicationContext();
        assertEquals(true,gs.getClient().getScore()== attributionScore[0].getMAX_SCORE());

    }

    @Test
    public void testHorizontalSegmentWithMarge() throws Exception {
        int x = 200;
        int y=200;
        int MARGE=2;
        final AttributionScore[] attributionScore = new AttributionScore[1];

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                attributionScore[0] =new AttributionScoreSegment(drawingCanvas.getShapePoint().getPoints());
                attributionScore[0].calculateScore();
                System.out.println(attributionScore[0].getScore());
                client.setScore(attributionScore[0].getScore());

                return null;
            }
        }).when(connexion).emitShape(any(JSONObject.class));

        onView(withId(R.id.drawingCanvas5)).perform(singleTouchAt(x, y, MotionEvent.ACTION_DOWN));
        moveToPointX(x,x+=200,y+=MARGE);
        moveToPointX(x,x+=100,y+=MARGE);

        onView(withId(R.id.button)).perform(click());
        connexion.emitShape(drawingCanvas.getShapePoint().convertToJson());

        GlobalState gs = (GlobalState) intentsTestRule.getActivity().getApplicationContext();
        assertEquals(true,gs.getClient().getScore()== attributionScore[0].getMAX_SCORE()-1);

    }


}