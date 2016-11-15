package com.bertrobotics.bertscout2017;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Objects;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "bert_scout.db";
    public static final String STAND_SCOUTING_TABLE_NAME = "stand_scouting";

    private HashMap hp;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "CREATE TABLE stand_scouting " +
                        "(event text, match_no integer, team integer, " +
                        "auto_high integer, auto_low integer, auto_cross integer, tele_high integer, " +
                        "tele_low integer, tele_cross integer, endgame integer, comment text, " +
                        "PRIMARY KEY (event, match_no, team))"
        );
        db.execSQL(DBContract.TablePitInfo.SQL_QUERY_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS stand_scouting");
        db.execSQL(DBContract.TablePitInfo.SQL_QUERY_DELETE_TABLE);
        onCreate(db);
    }

    public JSONArray getData(String pEvent){
        JSONArray resultSet = new JSONArray();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor results =  db.rawQuery("SELECT * FROM stand_scouting WHERE event = '" + pEvent + "'", null);
        results.moveToFirst();

        while (results.isAfterLast() == false) {
            int totalColumn = results.getColumnCount();
            JSONObject rowObject = new JSONObject();

            for(int i = 0; i < totalColumn; i++) {
                if(results.getColumnName(i) != null) {
                    try {
                        if(results.getString(i) != null) {
                            rowObject.put(results.getColumnName(i) ,  results.getString(i) );
                        } else {
                            rowObject.put(results.getColumnName(i) ,  "" );
                        }
                    } catch( Exception e ) {

                    }
                }
            }

            resultSet.put(rowObject);
            results.moveToNext();
        }

        results.close();
        return resultSet;
    }

    public JSONArray getTeamData(String pEvent, Integer pTeam){
        JSONArray resultSet = new JSONArray();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor results =  db.rawQuery("SELECT * FROM stand_scouting WHERE event = '" + pEvent + "' AND team = " + pTeam, null);
        results.moveToFirst();

        while (results.isAfterLast() == false) {
            int totalColumn = results.getColumnCount();
            JSONObject rowObject = new JSONObject();

            for(int i = 0; i < totalColumn; i++) {
                if(results.getColumnName(i) != null) {
                    try {
                        if(results.getString(i) != null) {
                            rowObject.put(results.getColumnName(i) ,  results.getString(i) );
                        } else {
                            rowObject.put(results.getColumnName(i) ,  "" );
                        }
                    } catch( Exception e ) {

                    }
                }
            }

            resultSet.put(rowObject);
            results.moveToNext();
        }

        results.close();
        return resultSet;
    }


    public JSONArray getMatchData(String pEvent, Integer match_no, Integer team){
        JSONArray resultSet = new JSONArray();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor results =  db.rawQuery("SELECT * FROM stand_scouting WHERE event = '" + pEvent +
                                "' AND match_no = " + match_no + " AND team = " + team, null);
        results.moveToFirst();

        int totalColumn = results.getColumnCount();
        JSONObject rowObject = new JSONObject();

        for(int i = 0; i < totalColumn; i++) {
            if(results.getColumnName(i) != null) {
                try {
                    if(results.getString(i) != null) {
                        rowObject.put(results.getColumnName(i) ,  results.getString(i) );
                    } else {
                        rowObject.put(results.getColumnName(i) ,  "" );
                    }
                } catch( Exception e ) {

                }
            }
        }

        rowObject.length();

        if (rowObject.length() != 0) {
            resultSet.put(rowObject);
        }

        results.close();
        return resultSet;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, STAND_SCOUTING_TABLE_NAME);
        return numRows;
    }

    public boolean insertStandScouting (String event, Integer match_no, Integer team, Integer auto_high,
                                        Integer auto_low, Integer auto_cross, Integer tele_high,
                                        Integer tele_low, Integer tele_cross, Integer endgame, String comment)
    {
        SQLiteDatabase dbr = this.getReadableDatabase();
        Cursor results =  dbr.rawQuery("SELECT * FROM stand_scouting WHERE event = '" + event +
                "' AND match_no = " + match_no + " AND team = " + team, null);

        results.moveToFirst();

        if (results.getCount() == 1) {
            updateStandScouting(event, match_no, team, auto_high, auto_low, auto_cross, tele_high,
                    tele_low, tele_cross, endgame, comment);
        } else {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("event", event);
            contentValues.put("match_no", match_no);
            contentValues.put("team", team);
            contentValues.put("auto_high", auto_high);
            contentValues.put("auto_low", auto_low);
            contentValues.put("auto_cross", auto_cross);
            contentValues.put("tele_high", tele_high);
            contentValues.put("tele_low", tele_low);
            contentValues.put("tele_cross", tele_cross);
            contentValues.put("endgame", endgame);
            contentValues.put("comment", comment);
            db.insert("stand_scouting", null, contentValues);
        }
        return true;
    }

    public boolean updateStandScouting (String event, Integer match_no, Integer team, Integer auto_high,
                                        Integer auto_low, Integer auto_cross, Integer tele_high,
                                        Integer tele_low, Integer tele_cross, Integer endgame, String comment)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("event", event);
        contentValues.put("match_no", match_no);
        contentValues.put("team", team);
        contentValues.put("auto_high", auto_high);
        contentValues.put("auto_low", auto_low);
        contentValues.put("auto_cross", auto_cross);
        contentValues.put("tele_high", tele_high);
        contentValues.put("tele_low", tele_low);
        contentValues.put("tele_cross", tele_cross);
        contentValues.put("endgame", endgame);
        contentValues.put("comment", comment);
        db.update("stand_scouting", contentValues, "event = ? and match_no = ? and team = ?",
                new String[] {event, match_no.toString(), team.toString()});
        return true;
    }

    public Integer deleteStandScouting ()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete("stand_scouting", null, null);
    }

    //
    // Pit Scouting
    //

    public JSONArray getDataAllPit(String pEvent) {
        JSONArray resultSet = new JSONArray();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor results = db.rawQuery(
                "SELECT * FROM " + DBContract.TablePitInfo.TABLE_NAME +
                " WHERE event = '" + pEvent + "'" +
                "", null);
        results.moveToFirst();

        while (results.isAfterLast() == false) {
            int totalColumn = results.getColumnCount();
            JSONObject rowObject = new JSONObject();

            for (int i = 0; i < totalColumn; i++) {
                if (results.getColumnName(i) != null) {
                    try {
                        switch (results.getColumnName(i)) {
                            case DBContract.TablePitInfo._ID:
                            case DBContract.TablePitInfo.COLUMN_NAME_TEAM:
                                rowObject.put(results.getColumnName(i), results.getInt(i));
                                break;
                            case DBContract.TablePitInfo.COLUMN_NAME_EVENT:
                                rowObject.put(results.getColumnName(i), results.getString(i));
                                break;
                            case DBContract.TablePitInfo.COLUMN_NAME_CAN_SCORE_LOW:
                            case DBContract.TablePitInfo.COLUMN_NAME_CAN_SCORE_HIGH:
                            case DBContract.TablePitInfo.COLUMN_NAME_CAN_BLOCK:
                            case DBContract.TablePitInfo.COLUMN_NAME_CAN_CLIMB:
                                if (results.getInt(i) == 0) {
                                    rowObject.put(results.getColumnName(i), false);
                                } else {
                                    rowObject.put(results.getColumnName(i), true);
                                }
                                break;
                        }
                    } catch (JSONException e) {
                    }
                }
            }
            resultSet.put(rowObject);
            results.moveToNext();
        }

        results.close();
        return resultSet;
    }

    public JSONObject getDataPit(String pEvent, int pTeam)
    {
        JSONObject rowObject = new JSONObject();

        if (!Objects.equals(pEvent, "") && !Objects.equals(pTeam, ""))
        {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor results = db.rawQuery(
                    "SELECT * FROM " + DBContract.TablePitInfo.TABLE_NAME +
                            " WHERE " + DBContract.TablePitInfo.COLUMN_NAME_EVENT + " = '" + pEvent + "'" +
                            " AND " + DBContract.TablePitInfo.COLUMN_NAME_TEAM + " = " + pTeam +
                            " ORDER BY " + DBContract.TablePitInfo.COLUMN_NAME_TEAM +
                    "", null);
            results.moveToFirst();

            int totalColumn = results.getColumnCount();

            for (int i = 0; i < totalColumn; i++) {
                if (results.getColumnName(i) != null) {
                    try {
                        switch (results.getColumnName(i)) {
                            case DBContract.TablePitInfo._ID:
                            case DBContract.TablePitInfo.COLUMN_NAME_TEAM:
                                rowObject.put(results.getColumnName(i), results.getInt(i));
                                break;
                            case DBContract.TablePitInfo.COLUMN_NAME_EVENT:
                                rowObject.put(results.getColumnName(i), results.getString(i));
                                break;
                            case DBContract.TablePitInfo.COLUMN_NAME_CAN_SCORE_LOW:
                            case DBContract.TablePitInfo.COLUMN_NAME_CAN_SCORE_HIGH:
                            case DBContract.TablePitInfo.COLUMN_NAME_CAN_BLOCK:
                            case DBContract.TablePitInfo.COLUMN_NAME_CAN_CLIMB:
                                if (results.getInt(i) == 0) {
                                    rowObject.put(results.getColumnName(i), false);
                                } else {
                                    rowObject.put(results.getColumnName(i), true);
                                }
                                break;
                        }
                    } catch (JSONException e) {
                    }
                }
            }

            results.close();
        }

        return rowObject;
    }

    public boolean updatePitInfo(JSONObject pitInfo) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        try {

            contentValues.put(DBContract.TablePitInfo.COLUMN_NAME_EVENT, pitInfo.getString(DBContract.TablePitInfo.COLUMN_NAME_EVENT));
            contentValues.put(DBContract.TablePitInfo.COLUMN_NAME_TEAM, pitInfo.getInt(DBContract.TablePitInfo.COLUMN_NAME_TEAM));

            if (pitInfo.getBoolean(DBContract.TablePitInfo.COLUMN_NAME_CAN_SCORE_LOW)) {
                contentValues.put(DBContract.TablePitInfo.COLUMN_NAME_CAN_SCORE_LOW, 1);
            } else {
                contentValues.put(DBContract.TablePitInfo.COLUMN_NAME_CAN_SCORE_LOW, 0);
            }
            if (pitInfo.getBoolean(DBContract.TablePitInfo.COLUMN_NAME_CAN_SCORE_HIGH)) {
                contentValues.put(DBContract.TablePitInfo.COLUMN_NAME_CAN_SCORE_HIGH, 1);
            } else {
                contentValues.put(DBContract.TablePitInfo.COLUMN_NAME_CAN_SCORE_HIGH, 0);
            }
            if (pitInfo.getBoolean(DBContract.TablePitInfo.COLUMN_NAME_CAN_BLOCK)) {
                contentValues.put(DBContract.TablePitInfo.COLUMN_NAME_CAN_BLOCK, 1);
            } else {
                contentValues.put(DBContract.TablePitInfo.COLUMN_NAME_CAN_BLOCK, 0);
            }
            if (pitInfo.getBoolean(DBContract.TablePitInfo.COLUMN_NAME_CAN_CLIMB)) {
                contentValues.put(DBContract.TablePitInfo.COLUMN_NAME_CAN_CLIMB, 1);
            } else {
                contentValues.put(DBContract.TablePitInfo.COLUMN_NAME_CAN_CLIMB, 0);
            }

            if (pitInfo.has(DBContract.TablePitInfo._ID)) {

                db.update(
                        DBContract.TablePitInfo.TABLE_NAME,
                        contentValues,
                        "_id = ?",
                        new String[] {String.valueOf(pitInfo.getInt(DBContract.TablePitInfo._ID))}
                );
                return true;

            } else {

                long newID = db.insert(
                        DBContract.TablePitInfo.TABLE_NAME,
                        null,
                        contentValues
                );
                if (newID > 0) {
                    pitInfo.put(DBContract.TablePitInfo._ID, newID);
                    return true;
                }

            }

        } catch (JSONException e) {
            return false;
        }

        return true;

    }

}