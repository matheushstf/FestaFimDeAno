package br.com.matheushstf.festafimdeano.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import br.com.matheushstf.festafimdeano.R;
import br.com.matheushstf.festafimdeano.constants.FimDeAnoConstants;
import br.com.matheushstf.festafimdeano.data.SecurityPreferences;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.checkbox = findViewById(R.id.checkbox);
        this.mViewHolder.checkbox.setOnClickListener(this);

        this.loadDataFroActivity();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.checkbox) {
            if (this.mViewHolder.checkbox.isChecked()) {
                //salvar presença
                this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE_KEY, FimDeAnoConstants.CONFIRMATION_YES);
            } else {
                //salva ausencia
                this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE_KEY, FimDeAnoConstants.CONFIRMATION_NO);
            }
        }

    }

    void loadDataFroActivity() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String presence = extras.getString(FimDeAnoConstants.PRESENCE_KEY);
            if (presence != null && presence.equals(FimDeAnoConstants.CONFIRMATION_YES)) {
                this.mViewHolder.checkbox.setChecked(true);
            } else {
                this.mViewHolder.checkbox.setChecked(false);
            }
        }
    }

    private static class ViewHolder {
        CheckBox checkbox;
    }


}