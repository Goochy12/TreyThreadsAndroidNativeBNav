package au.com.liamgooch.treythreads.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import au.com.liamgooch.treythreads.R;


public class ContactFragment extends Fragment {

    private EditText name;
    private EditText subject;
    private EditText message;
    private Button sendButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstaceState){
        super.onViewCreated(view, savedInstaceState);

        name = (EditText) view.findViewById(R.id.helpNameBox);
        subject = (EditText) view.findViewById(R.id.helpSubjectBox);
        message = (EditText) view.findViewById(R.id.helpMessageBox);
        sendButton = (Button) view.findViewById(R.id.helpSendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("message/rfc822");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"treyclothingco@gmail.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
                emailIntent.putExtra(Intent.EXTRA_TEXT, message.getText().toString());

                try {
                    startActivity(Intent.createChooser(emailIntent,"Send Message"));
                }catch (android.content.ActivityNotFoundException ex){
                    Toast.makeText(getActivity(),"No email clients installed.",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
