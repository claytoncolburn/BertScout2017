package com.bertrobotics.bertscout2017;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import android.os.AsyncTask;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class StandScoutingFragment extends Fragment {

    View mRootView;
    public DBHelper dbHelper;

    StatisticsFragment mStatisticsFragment;

    public StandScoutingFragment(StatisticsFragment statisticsFragment) {
        mStatisticsFragment = statisticsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.stand_scouting, container, false);

        dbHelper = new DBHelper(mRootView.getContext());

        Button saveBtn = (Button) mRootView.findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AsyncTaskURL apacheInsert = new AsyncTaskURL();
//                apacheInsert.execute();

                Spinner matchSpinner = (Spinner) mRootView.findViewById(R.id.match_spinner);
                Spinner teamSpinner = (Spinner) mRootView.findViewById(R.id.team_spinner);
                Spinner defenseSpinner = (Spinner) mRootView.findViewById(R.id.defense_spinner);
                Spinner endgameSpinner = (Spinner) mRootView.findViewById(R.id.endgame_spinner);
                TextView autoHighGoalText = (TextView) mRootView.findViewById(R.id.auto_high_goal_text);
                TextView autoLowGoalText = (TextView) mRootView.findViewById(R.id.auto_low_goal_text);
                TextView teleopHighGoalText = (TextView) mRootView.findViewById(R.id.teleop_high_goal_text);
                TextView teleopLowGoalText = (TextView) mRootView.findViewById(R.id.teleop_low_goal_text);
                TextView teleopCrossingsText = (TextView) mRootView.findViewById(R.id.teleop_crossings_text);

                String event = getActivity().getTitle().toString();
                Integer match_no = Integer.parseInt(matchSpinner.getSelectedItem().toString());
                Integer team = Integer.parseInt(teamSpinner.getSelectedItem().toString());
                Integer autoHighGoal = Integer.parseInt(autoHighGoalText.getText().toString());
                Integer autoLowGoal = Integer.parseInt(autoLowGoalText.getText().toString());
                Integer defense;
                Integer teleopHighGoal = Integer.parseInt(teleopHighGoalText.getText().toString());
                Integer teleopLowGoal = Integer.parseInt(teleopLowGoalText.getText().toString());
                Integer teleopCrossings = Integer.parseInt(teleopCrossingsText.getText().toString());
                Integer endgame;

                if (defenseSpinner.getSelectedItem().toString().equals("Reach")) {
                    defense = 2;
                } else if (defenseSpinner.getSelectedItem().toString().equals("Cross")) {
                    defense = 10;
                } else {
                    defense = 0;
                }

                if (endgameSpinner.getSelectedItem().toString().equals("Challenge")) {
                    endgame = 5;
                } else if (endgameSpinner.getSelectedItem().toString().equals("Scale")) {
                    endgame = 15;
                } else {
                    endgame = 0;
                }

                AsyncTaskSaveData insertData = new AsyncTaskSaveData(mRootView, event, match_no,
                        team, autoHighGoal, autoLowGoal, defense, teleopHighGoal, teleopLowGoal,
                        teleopCrossings, endgame);
                insertData.execute();
            }
        });

        Button autoHighGoalMinusBtn = (Button) mRootView.findViewById(R.id.auto_high_goal_minus_btn);
        autoHighGoalMinusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView autoHighGoalText = (TextView) mRootView.findViewById(R.id.auto_high_goal_text);
                int autoHighGoalInt = Integer.parseInt(autoHighGoalText.getText().toString());

                if (autoHighGoalInt > 0) {
                    autoHighGoalInt--;
                    autoHighGoalText.setText(Integer.toString(autoHighGoalInt));
                }
            }
        });

        Button autoHighGoalPlusBtn = (Button) mRootView.findViewById(R.id.auto_high_goal_plus_btn);
        autoHighGoalPlusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView autoHighGoalText = (TextView) mRootView.findViewById(R.id.auto_high_goal_text);
                int autoHighGoalInt = Integer.parseInt(autoHighGoalText.getText().toString());

                if (autoHighGoalInt < 100) {
                    autoHighGoalInt++;
                    autoHighGoalText.setText(Integer.toString(autoHighGoalInt));
                }
            }
        });

        Button autoLowGoalMinusBtn = (Button) mRootView.findViewById(R.id.auto_low_goal_minus_btn);
        autoLowGoalMinusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView autoLowGoalText = (TextView) mRootView.findViewById(R.id.auto_low_goal_text);
                int autoLowGoalInt = Integer.parseInt(autoLowGoalText.getText().toString());

                if (autoLowGoalInt > 0) {
                    autoLowGoalInt--;
                    autoLowGoalText.setText(Integer.toString(autoLowGoalInt));
                }
            }
        });

        Button autoLowGoalPlusBtn = (Button) mRootView.findViewById(R.id.auto_low_goal_plus_btn);
        autoLowGoalPlusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView autoLowGoalText = (TextView) mRootView.findViewById(R.id.auto_low_goal_text);
                int autoLowGoalInt = Integer.parseInt(autoLowGoalText.getText().toString());

                if (autoLowGoalInt < 100) {
                    autoLowGoalInt++;
                    autoLowGoalText.setText(Integer.toString(autoLowGoalInt));
                }
            }
        });

        Button teleopHighGoalMinusBtn = (Button) mRootView.findViewById(R.id.teleop_high_goal_minus_btn);
        teleopHighGoalMinusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView teleopHighGoalText = (TextView) mRootView.findViewById(R.id.teleop_high_goal_text);
                int teleopHighGoalInt = Integer.parseInt(teleopHighGoalText.getText().toString());

                if (teleopHighGoalInt > 0) {
                    teleopHighGoalInt--;
                    teleopHighGoalText.setText(Integer.toString(teleopHighGoalInt));
                }
            }
        });

        Button teleopHighGoalPlusBtn = (Button) mRootView.findViewById(R.id.teleop_high_goal_plus_btn);
        teleopHighGoalPlusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView teleopHighGoalText = (TextView) mRootView.findViewById(R.id.teleop_high_goal_text);
                int teleopHighGoalInt = Integer.parseInt(teleopHighGoalText.getText().toString());

                if (teleopHighGoalInt < 100) {
                    teleopHighGoalInt++;
                    teleopHighGoalText.setText(Integer.toString(teleopHighGoalInt));
                }
            }
        });

        Button teleopLowGoalMinusBtn = (Button) mRootView.findViewById(R.id.teleop_low_goal_minus_btn);
        teleopLowGoalMinusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView teleopLowGoalText = (TextView) mRootView.findViewById(R.id.teleop_low_goal_text);
                int teleopLowGoalInt = Integer.parseInt(teleopLowGoalText.getText().toString());

                if (teleopLowGoalInt > 0) {
                    teleopLowGoalInt--;
                    teleopLowGoalText.setText(Integer.toString(teleopLowGoalInt));
                }
            }
        });

        Button teleopLowGoalPlusBtn = (Button) mRootView.findViewById(R.id.teleop_low_goal_plus_btn);
        teleopLowGoalPlusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView teleopLowGoalText = (TextView) mRootView.findViewById(R.id.teleop_low_goal_text);
                int teleopLowGoalInt = Integer.parseInt(teleopLowGoalText.getText().toString());

                if (teleopLowGoalInt < 100) {
                    teleopLowGoalInt++;
                    teleopLowGoalText.setText(Integer.toString(teleopLowGoalInt));
                }
            }
        });

        Button teleopCrossingsMinusBtn = (Button) mRootView.findViewById(R.id.teleop_crossings_minus_btn);
        teleopCrossingsMinusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView teleopCrossingsText = (TextView) mRootView.findViewById(R.id.teleop_crossings_text);
                int teleopCrossingsInt = Integer.parseInt(teleopCrossingsText.getText().toString());

                if (teleopCrossingsInt > 0) {
                    teleopCrossingsInt--;
                    teleopCrossingsText.setText(Integer.toString(teleopCrossingsInt));
                }
            }
        });

        Button teleopCrossingsPlusBtn = (Button) mRootView.findViewById(R.id.teleop_crossings_plus_btn);
        teleopCrossingsPlusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView teleopCrossingsText = (TextView) mRootView.findViewById(R.id.teleop_crossings_text);
                int teleopCrossingsInt = Integer.parseInt(teleopCrossingsText.getText().toString());

                if (teleopCrossingsInt < 100) {
                    teleopCrossingsInt++;
                    teleopCrossingsText.setText(Integer.toString(teleopCrossingsInt));
                }
            }
        });

        Spinner matchSpinner = (Spinner) mRootView.findViewById(R.id.match_spinner);
        matchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                clearScreen();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner teamSpinner = (Spinner) mRootView.findViewById(R.id.team_spinner);
        teamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                clearScreen();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buildMatchSpinner("north_shore");
        buildTeamSpinner("north_shore");
        buildDefenseSpinner();
        buildEndgameSpinner();

        return mRootView;
    }

    public void buildMatchSpinner(String event) {
        Spinner matchSpinner = (Spinner) mRootView.findViewById(R.id.match_spinner);

        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(mRootView.getContext(),
                R.array.matches, R.layout.spinner_item);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        matchSpinner.setAdapter(dataAdapter);
    }

    public void buildTeamSpinner(String event) {
        Spinner teamSpinner = (Spinner) mRootView.findViewById(R.id.team_spinner);

        Integer teamList;

        if (event.equals("north_shore")) {
            teamList = R.array.north_shore_teams;
        } else {
            teamList = R.array.pine_tree_teams;
        }

        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(mRootView.getContext(),
                teamList, R.layout.spinner_item);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        teamSpinner.setAdapter(dataAdapter);
    }

    public void buildDefenseSpinner() {
        Spinner defenseSpinner = (Spinner) mRootView.findViewById(R.id.defense_spinner);

        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(mRootView.getContext(),
                R.array.defense, R.layout.spinner_item);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        defenseSpinner.setAdapter(dataAdapter);
    }

    public void buildEndgameSpinner() {
        Spinner endgameSpinner = (Spinner) mRootView.findViewById(R.id.endgame_spinner);

        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(mRootView.getContext(),
                R.array.endgame, R.layout.spinner_item);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        endgameSpinner.setAdapter(dataAdapter);
    }

    public void clearScreen() {
        Spinner defenseSpinner = (Spinner) mRootView.findViewById(R.id.defense_spinner);
        defenseSpinner.setSelection(0);

        Spinner endgameSpinner = (Spinner) mRootView.findViewById(R.id.endgame_spinner);
        endgameSpinner.setSelection(0);

        TextView autoHighGoalText = (TextView) mRootView.findViewById(R.id.auto_high_goal_text);
        autoHighGoalText.setText("0");

        TextView autoLowGoalText = (TextView) mRootView.findViewById(R.id.auto_low_goal_text);
        autoLowGoalText.setText("0");

        TextView teleopHighGoalText = (TextView) mRootView.findViewById(R.id.teleop_high_goal_text);
        teleopHighGoalText.setText("0");

        TextView teleopLowGoalText = (TextView) mRootView.findViewById(R.id.teleop_low_goal_text);
        teleopLowGoalText.setText("0");

        TextView teleopCrossingsText = (TextView) mRootView.findViewById(R.id.teleop_crossings_text);
        teleopCrossingsText.setText("0");
    }

    private class AsyncTaskSaveData extends AsyncTask<String, Void, String> {
        String event;
        Integer match_no;
        Integer team;
        Integer autoHighGoal;
        Integer autoLowGoal;
        Integer defense;
        Integer teleopHighGoal;
        Integer teleopLowGoal;
        Integer teleopCrossings;
        Integer endgame;

        View rootView;

        ProgressDialog progress;

        private AsyncTaskSaveData(View pRootView, String pEvent, Integer pMatch_no, Integer pTeam,
                                  Integer pAutoHighGoal, Integer pAutoLowGoal, Integer pDefense,
                                  Integer pTeleopHighGoal, Integer pTeleopLowGoal,
                                  Integer pTeleopCrossings, Integer pEndgame) {

            event = pEvent;
            match_no = pMatch_no;
            team = pTeam;
            autoHighGoal = pAutoHighGoal;
            autoLowGoal = pAutoLowGoal;
            defense = pDefense;
            teleopHighGoal = pTeleopHighGoal;
            teleopLowGoal = pTeleopLowGoal;
            teleopCrossings = pTeleopCrossings;
            endgame = pEndgame;

            rootView = pRootView;

            progress = new ProgressDialog(rootView.getContext());
        }

        @Override
        protected void onPreExecute() {
            progress.setTitle("Saving");
            progress.setMessage("Please wait...");
            progress.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                dbHelper.insertStandScouting(event, match_no, team, autoHighGoal, autoLowGoal,
                        defense, teleopHighGoal, teleopLowGoal, teleopCrossings, endgame);

            } catch (Exception e) {
                return "Failure";
            }

            return "Success";
        }

        @Override
        protected void onPostExecute(String result) {
            mStatisticsFragment.populateList();

            progress.dismiss();

            Toast.makeText(rootView.getContext(), result, Toast.LENGTH_SHORT).show();
        }
    }
}