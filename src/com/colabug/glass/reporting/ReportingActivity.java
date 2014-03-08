package com.colabug.glass.reporting;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.glass.app.Card;

public class ReportingActivity extends Activity
{
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        // Create a card with some simple text and a footer.
        Card card1 = new Card( this );
        card1.setText( "This card has a footer." );
        card1.setFootnote( "I'm the footer!" );
        // Don't call this if you're using TimelineManager
        setContentView( card1.toView() );
    }
}
