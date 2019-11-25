package appmoviles.com.preclase13.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import appmoviles.com.preclase13.model.entity.Comment;
import appmoviles.com.preclase13.util.NotificationUtils;

public class NotificationService extends Service {
    public NotificationService() {
    }

    FirebaseAuth auth;
    FirebaseDatabase db;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        db.getReference().child("notifications").child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int c = 0;
                for(DataSnapshot comment : dataSnapshot.getChildren()){
                    Comment commentObj = comment.getValue(Comment.class);
                    NotificationUtils.createNotification(c++,"Nuevo comentatario", commentObj.getText());

                    db.getReference().child("notifications").child(auth.getCurrentUser().getUid()).setValue(null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
}
