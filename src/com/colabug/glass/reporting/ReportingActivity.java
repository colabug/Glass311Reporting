package com.colabug.glass.reporting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import com.google.android.glass.app.Card;

import java.util.List;

public class ReportingActivity extends Activity
{
    private static final int SPEECH_REQUEST = 0;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        // Create a card with some simple text and a footer.
        Card card1 = new Card( this );
        card1.setText( "Sorry we can't parse what you said" );
        card1.setFootnote( "Please try again." );
        // Don't call this if you're using TimelineManager
        setContentView( card1.toView() );

        displaySpeechRecognizer();
    }

    private void displaySpeechRecognizer()
    {
        Intent intent = new Intent( RecognizerIntent.ACTION_RECOGNIZE_SPEECH );
        startActivityForResult( intent, SPEECH_REQUEST );
    }


    @Override
    protected void onActivityResult( int requestCode,
                                     int resultCode,
                                     Intent data )
    {
        if ( requestCode == SPEECH_REQUEST && resultCode == RESULT_OK )
        {
            List<String> results = data.getStringArrayListExtra(
            RecognizerIntent.EXTRA_RESULTS );
            String spokenText = results.get( 0 );
            // Do something with spokenText.
            Card card2;

            if (spokenText.contains("hole")){
                     card2 = new Card( this );
                     card2.setText( "You just reported a Pothole" );
                     card2.setFootnote("you just said \"" + spokenText +"\"");
                     setContentView( card2.toView() );
            } else if (spokenText.contains("street light")){
                     card2 = new Card( this );
                     card2.setText( "You just reported a broken street light" );
                     card2.setFootnote("you just said \"" + spokenText +"\"");
                     setContentView( card2.toView() );
            }


        }
        super.onActivityResult( requestCode, resultCode, data );
    }
}
