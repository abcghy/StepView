package me.daolema.sakura.grabdetailstepview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;

import me.daolema.sakura.stepviewlib.StepView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    StepView stepView;
    Button posted;
    Button received;
    Button cancelled;
    Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stepView  = (StepView) findViewById(R.id.sv_grab_detail_state);
        posted    = (Button) findViewById(R.id.posted);
        received  = (Button) findViewById(R.id.received);
        cancelled = (Button) findViewById(R.id.cancelled);
        done      = (Button) findViewById(R.id.done);

        posted   .setOnClickListener(this);
        received .setOnClickListener(this);
        cancelled.setOnClickListener(this);
        done     .setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.posted: {
                stepView.setState(StepView.State.POSTED);
                break;
            }
            case R.id.received: {
                stepView.setState(StepView.State.RECEIVED);
                break;
            }
            case R.id.cancelled: {
                stepView.setState(StepView.State.CANCELLED);
                break;
            }
            case R.id.done: {
                stepView.setState(StepView.State.DONE);
                break;
            }

        }
    }
}
