package com.polimi.dilapp.main;


import android.os.Bundle;
import android.util.Log;
import com.polimi.dilapp.database.AppDatabase;
import com.polimi.dilapp.database.ChildEntity;
import com.polimi.dilapp.database.DatabaseInitializer;

import java.util.List;

public class CreateAccountPresenter implements  ICreateAccount.Presenter{

    private ICreateAccount.View createAccountView;
    private AppDatabase db;

    CreateAccountPresenter(ICreateAccount.View view){

        this.createAccountView= view;
        db = AppDatabase.getAppDatabase(createAccountView.getContext());
    }

    @Override
    public List<ChildEntity> getListOfChildren() {
        return DatabaseInitializer.getListOfChildren(AppDatabase.getAppDatabase(createAccountView.getContext()));
    }

    @Override
    public void resumeCurrentPlayer(Bundle savedInstanceState) {
        DatabaseInitializer.setCurrentPlayer(db, savedInstanceState.getInt("current_player"));
        DatabaseInitializer.setLevelCurrentPlayer(db, savedInstanceState.getInt("level"));
        DatabaseInitializer.setObjectCurrentPlayer(db, savedInstanceState.getString("object"));
        DatabaseInitializer.setSubStringCurrentPlayer(db, savedInstanceState.getString("subString"));
        Log.i("Current player: ", String.valueOf(DatabaseInitializer.getCurrentPlayer(db)));
        Log.i("[CREATEACC PRESENTER]", "Resuming current player " + savedInstanceState.getInt("current_player"));
        Log.i("[CREATEACC PRESENTER]", "Resuming level " +savedInstanceState.getInt("level"));

    }

    @Override
    public void storeCurrentPlayer(Bundle savedInstanceState) {
        savedInstanceState.putInt("current_player", DatabaseInitializer.getCurrentPlayer(db));
        savedInstanceState.putInt("level", DatabaseInitializer.getLevelCurrentPlayer(db));
        savedInstanceState.putString("object", DatabaseInitializer.getObjectCurrentPlayer(db));
        savedInstanceState.putString("subString", DatabaseInitializer.getSubStringCurrentPlayer(db));
        Log.i("[CREATEACC PRESENTER]", "Storing current player " +String.valueOf(DatabaseInitializer.getCurrentPlayer(db)));
        Log.i("[CREATEACC PRESENTER]", "Storing level " +String.valueOf(DatabaseInitializer.getLevelCurrentPlayer(db)));
    }

    @Override
    public void deletePlayer(ChildEntity childEntity) {
        DatabaseInitializer.deletePlayer(db, childEntity);
    }

}
