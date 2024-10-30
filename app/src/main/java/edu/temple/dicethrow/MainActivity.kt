package edu.temple.dicethrow

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


/*
Our DieThrow application has been refactored to move the dieRoll() logic
into the ViewModel instead of the Fragment.
Study the DieViewModel, ButtonFragment, and DieFragment classes to
see the changes.

Follow the requirements below to have this app function
in both portrait and landscape configurations.
The Activity layout files for both Portrait and Landscape are already provided
*/

class MainActivity : AppCompatActivity(), ButtonFragment.ButtonInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* TODO 1: Load fragment(s)
            - Show only Button Fragment if portrait
            - show both fragments if Landscape
          */

        if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            val buttonFrag = ButtonFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container1, buttonFrag)
                .commit()
        }
        else {
            val buttonFrag = ButtonFragment()
            val dieFrag = DieFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container1, buttonFrag)
                .add(R.id.container2, dieFrag)
                .commit()
        }

    }

    /* TODO 2: switch fragments if portrait (no need to switch fragments if Landscape)
        */
    // Remember to place Fragment transactions on BackStack so then can be reversed
    override fun buttonClicked()
    {
        //current fragment
        val currentFrag = supportFragmentManager.findFragmentById(R.id.container2)
        //compare
        if(currentFrag is ButtonFragment)
        {
            val dieFrag = DieFragment()

            supportFragmentManager
                .beginTransaction()
                .add(R.id.container2, dieFrag)
                .addToBackStack(null)
                .commit()
        }
        else
        {
            val buttonFrag = ButtonFragment()

            supportFragmentManager
                .beginTransaction()
                .add(R.id.container1, buttonFrag)
                .addToBackStack(null)
                .commit()
        }
    }


}