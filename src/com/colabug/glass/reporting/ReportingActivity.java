package com.colabug.glass.reporting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import com.google.android.glass.app.Card;

import java.util.List;

// TODO: Implement the rest of the flow
//    * Add menu for the first card
//    * After selecting a category, prompt for more text
//    * Show a summary card with category and text
//    * Share data with the API
//    * Allow sharing of photos
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
        super.onActivityResult( requestCode, resultCode, data );

        if ( requestCode == SPEECH_REQUEST && resultCode == RESULT_OK )
        {
            List<String> results = data.getStringArrayListExtra(
            RecognizerIntent.EXTRA_RESULTS );
            String spokenText = results.get( 0 );

            // Determine what to do based on the category keyword
            Card card = new Card( this );
            // Pothole
            if ( spokenText.contains( "hole" ) )
            {
                card.setText( "You just reported a pothole." );
                card.setFootnote( "You just said \"" + spokenText + "\"" );
            }
            // Street light
            else if ( spokenText.contains( "street light" ) )
            {
                card.setText( "You just reported a broken street light." );
                card.setFootnote( "You just said \"" + spokenText + "\"" );
            }
            // Dangerous building
            else if ( spokenText.contains( "building" ) )
            {
                card.setText( "You just reported a dangerous building." );
                card.setFootnote( "You just said \"" + spokenText + "\"" );
            }
            // Unknown category
            else
            {
                card.setText( "Please use a known category: pothole, dangerous building, or street light." );
                card.setFootnote( "You just said \"" + spokenText + "\"" );
            }

            setContentView( card.toView() );
        }
    }
}
