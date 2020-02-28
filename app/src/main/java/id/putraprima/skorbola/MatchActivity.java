package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchActivity extends AppCompatActivity {
    private TextView homeTeamText;
    private TextView awayTeamText;
    private TextView homeScore;
    private TextView awayScore;
    private ImageView homeImage;
    private ImageView awayImage;
    private TextView result;
    int home, away;

    public static final String WIN_KEY = "win";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
        homeTeamText = findViewById(R.id.txt_home);
        awayTeamText = findViewById(R.id.txt_away);
        homeScore = findViewById(R.id.score_home);
        awayScore = findViewById(R.id.score_away);
        homeImage = findViewById(R.id.home_logo);
        awayImage = findViewById(R.id.away_logo);


        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String home = extras.getString(MainActivity.HOMETEAM_KEY);
            homeTeamText.setText(home);
            String away = extras.getString(MainActivity.AWAYTEAM_KEY);
            awayTeamText.setText(away);

            Bitmap bm1 = (Bitmap) extras.get("home_logo");
            homeImage.setImageBitmap(bm1);

            Bitmap bm2 = (Bitmap) extras.get("away_logo");
            awayImage.setImageBitmap(bm2);


//            Bitmap bmp = extra.getParcelable("homeImg");
//            Bitmap bmp2 = extra.getParcelable("awayImg");
//
//            homeTeamText.setText(extras.getString(MainActivity.HOMETEAM_KEY));
//            awayTeamText.setText(extras.getString(MainActivity.AWAYTEAM_KEY));
//            avatar1.setImageBitmap(bmp);
//            avatar2.setImageBitmap(bmp2);
        }
    }

    public void HandleAddAway(View view) {
        away = Integer.parseInt(awayScore.getText().toString());
        away++;
        awayScore.setText(""+away);
    }

    public void HandleAddHome(View view) {
        home = Integer.parseInt(homeScore.getText().toString());
        home++;
        homeScore.setText(""+home);
    }

    public void handleResult(View view) {
        Intent intent = new Intent(this, ResultActivity.class);

        if(away > home){
            intent.putExtra(WIN_KEY, "Selamat "+awayTeamText.getText().toString());
        }else if(away < home){
            intent.putExtra(WIN_KEY, "Selamat "+homeTeamText.getText().toString());
        }else{
            intent.putExtra(WIN_KEY, "Seri!");
        }
        startActivity(intent);
    }
}
