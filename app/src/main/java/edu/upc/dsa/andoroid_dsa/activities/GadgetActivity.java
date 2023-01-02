package edu.upc.dsa.andoroid_dsa.activities;

import static edu.upc.dsa.andoroid_dsa.activities.LogInActivity.SHARED_PREFS;
import static edu.upc.dsa.andoroid_dsa.activities.LogInActivity.TEXT1;
import static edu.upc.dsa.andoroid_dsa.activities.LogInActivity.TEXT2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.List;

import edu.upc.dsa.andoroid_dsa.Api;
import edu.upc.dsa.andoroid_dsa.R;
import edu.upc.dsa.andoroid_dsa.RetrofitClient;
import edu.upc.dsa.andoroid_dsa.models.Gadget;
import edu.upc.dsa.andoroid_dsa.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GadgetActivity extends AppCompatActivity {

    //TableLayout tableLayout;

    Api APIservice;

    Button logout;

    private RecyclerView recyclerViewGadgets;
    private RecyclerViewAdapter adaptadorGadgets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.provisional_gadgetlist);
        //recyclerViewGadgets= new RecyclerView(this);
        recyclerViewGadgets=(RecyclerView)findViewById(R.id.recyclerGadget);
        Log.d("DDDD", ""+recyclerViewGadgets);
        recyclerViewGadgets.setLayoutManager(new LinearLayoutManager(this));
        //tableLayout = findViewById(R.id.tableLayout);
        //logout =findViewById(R.id.logOutBtn);
        APIservice = RetrofitClient.getInstance().getMyApi();
        Call<List<Gadget>> call = APIservice.getGadgets();
        try {
            adaptadorGadgets= new RecyclerViewAdapter(call.execute().body());
            //buildTable(call);
        } catch (IOException e) {
            e.printStackTrace();
        }
        recyclerViewGadgets.setAdapter(adaptadorGadgets);
    }
    public void btnClicked(View view) throws IOException {
        SharedPreferences preferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor =preferences.edit();
        editor.putString(TEXT1, "");
        editor.putString(TEXT2, "");
        editor.apply();
        Intent intent = new Intent(GadgetActivity.this, MainActivity.class);
        GadgetActivity.this.startActivity(intent);
    }

    @SuppressLint("SetTextI18n")
    private void buildTable(Call<List<Gadget>> call) throws IOException {
        List<Gadget> gadgets = call.execute().body();
        assert gadgets != null;

        for (Gadget gadget : gadgets) {
            View tableRow = LayoutInflater.from(this).inflate(R.layout.table_gadget_item, null, false);

            TextView gadgetId = tableRow.findViewById(R.id.gadgetId);
            TextView gadgetCost = tableRow.findViewById(R.id.gadgetCost);
            TextView gadgetDescription = tableRow.findViewById(R.id.gadgetDescription);
            TextView gadgetUnityShape = tableRow.findViewById(R.id.gadgetUnityShape);

            gadgetId.setText(gadget.getId());
            gadgetCost.setText(Integer.toString(gadget.getCost()));
            gadgetDescription.setText(gadget.getDescription());
            gadgetUnityShape.setText(gadget.getUnityShape());

            //tableLayout.addView(tableRow);
        }
    }

    public void returnFunction(View view){
        Intent intentRegister = new Intent(GadgetActivity.this, MainActivity.class);
        GadgetActivity.this.startActivity(intentRegister);
    }

}