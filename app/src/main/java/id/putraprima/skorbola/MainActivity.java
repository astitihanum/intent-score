package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final String HOMETEAM_KEY = "hometeam";
    public static final String AWAYTEAM_KEY = "awayteam";
    public static final String HLOGO_KEY = "home_logo";
    public static final String ALOGO_KEY = "away_logo";

    private static final String TAG = MainActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE_HOME = 1;
    private static final int GALLERY_REQUEST_CODE_AWAY = 2;

    @NotEmpty
    private EditText homeTeamInput;
    @NotEmpty
    private EditText awayTeamInput;
    private ImageView homeImage;
    private ImageView awayImage;
    private Bitmap home_logo;
    private Bitmap away_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeTeamInput = (EditText) findViewById(R.id.home_team);
        awayTeamInput = (EditText) findViewById(R.id.away_team);
        homeImage = findViewById(R.id.home_logo);
        awayImage = findViewById(R.id.away_logo);

        //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        //3. Ganti Logo Home Team
        //4. Ganti Logo Away Team
        //5. Next Button Pindah Ke MatchActivity
    }

    public void handleNext(View view) {
        String home = homeTeamInput.getText().toString();
        String away = awayTeamInput.getText().toString();

        if((home).equals("")||(away).equals("")){
            Toast.makeText(this, "NAMA TIDAK BOLEH KOSONG", Toast.LENGTH_SHORT).show();
        } else{
            homeImage.setDrawingCacheEnabled(true);
            home_logo = homeImage.getDrawingCache();
            awayImage.setDrawingCacheEnabled(true);
            away_logo = awayImage.getDrawingCache();

            Intent intent =  new Intent(this, MatchActivity.class);
            intent.putExtra(HOMETEAM_KEY, home);
            intent.putExtra(AWAYTEAM_KEY, away);
            intent.putExtra(HLOGO_KEY, home_logo);
            intent.putExtra(ALOGO_KEY, away_logo);

            startActivity(intent);
        }
    }

    public void handleAvatar1(View view) {
        Intent intent =  new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    public void handleAvatar2(View view) {
        Intent intent =  new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }

        if (requestCode == GALLERY_REQUEST_CODE_HOME) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    homeImage.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't upload image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }

        if (requestCode == GALLERY_REQUEST_CODE_AWAY) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    awayImage.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't upload image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }
}
