package fi.crowmoore.mustakynnys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.LoginManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        String surname = bundle.getString("surname");
        String imageUrl = bundle.getString("imageUrl");

        TextView nameView = (TextView)findViewById(R.id.fullname);
        nameView.setText(String.format("%1$s %2$s", name, surname));

        new DownloadImage((ImageView)findViewById(R.id.profilePic)).execute(imageUrl);

        createLogoutButton();
    }

    private void createLogoutButton() {
        Button logout = (Button)findViewById(R.id.logout_button);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                Intent login = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(login);
                finish();
            }
        });
    }
}
