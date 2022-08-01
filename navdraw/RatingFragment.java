
package com.example.edithapp.navdraw;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.edithapp.R;

public class RatingFragment extends Fragment {
    RatingBar ratingBar;
    Button button;
    EditText cmt;
    String emailsend ="rathodmeet.26@gmail.com";
    String emailsubject="Comments";

    public RatingFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.navdraw_fragment_rating, container, false);
        ratingBar = view.findViewById(R.id.ratingStar);
        ratingBar.setRating(5);
        ratingBar.setNumStars(5);
        button=view.findViewById(R.id.navbar_btn_rating_send);
        cmt=view.findViewById(R.id.navbar_txt_feedback);
        //Performing action on Button Click
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                //Getting the rating and displaying it on the toast
                String rating=String.valueOf(ratingBar.getRating());
                Toast.makeText(getContext(), rating, Toast.LENGTH_LONG).show();
                String emailbody = cmt.getText().toString();
                Intent intent = new Intent(Intent.ACTION_SEND);
                emailbody+="\nRate from my side is "+rating;
                // add three fiels to intent using putExtra function
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] { emailsend });
                intent.putExtra(Intent.EXTRA_SUBJECT, emailsubject);
                intent.putExtra(Intent.EXTRA_TEXT, emailbody);
                // set type of intent
                intent.setType("message/rfc822");
                // startActivity with intent with chooser
                // as Email client using createChooser function
                startActivity(
                        Intent
                                .createChooser(intent,
                                        "Choose an Email client :"));
            }
        });
        return view;
    }
}
