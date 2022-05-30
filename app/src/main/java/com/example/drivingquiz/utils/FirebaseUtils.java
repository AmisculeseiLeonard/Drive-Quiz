package com.example.drivingquiz.utils;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.drivingquiz.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class FirebaseUtils {
    private static FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private static DatabaseReference databaseReference = firebaseDatabase.getReference();

    private FirebaseUtils(){
        throw new java.lang.UnsupportedOperationException("Utility class and cannot be instantiated");
    };

    public static DatabaseReference getReference() {
        return databaseReference.child("questions");
    }

    public static Query getQuestions() {
        return databaseReference.child("questions").orderByChild("category");
    }

    public static Query getQuestionsCategoryBased(String category) {
        return databaseReference.child("questions").orderByChild("category").equalTo(category);
    }

    public static void insertUser(FirebaseAuth firebaseAuth) {
        String userId = firebaseAuth.getCurrentUser().getUid();
        String name = firebaseAuth.getCurrentUser().getDisplayName();
        String email = firebaseAuth.getCurrentUser().getEmail();


        Query query = databaseReference.child("users").child("uid").equalTo(firebaseAuth.getCurrentUser().getUid());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.exists()){
                    databaseReference.child("users").child(userId).child("email").setValue(email);
                    databaseReference.child("users").child(userId).child("name").setValue(name);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "insertUser:onCancelled", error.toException());
            }
        });
    }

    public static void insertQuizResult(FirebaseUser user, int totalQuestions, int incorrectAnswersCount,
                                        int correctAnswersCount, int questionsAnswered, String result, String date) {
        String key = databaseReference.push().getKey();
        databaseReference.child("users").child(user.getUid()).child("quiz_results")
                .child(key).child("totalQuestions").setValue(totalQuestions);

        databaseReference.child("users").child(user.getUid()).child("quiz_results")
                .child(key).child("incorrectAnswersCount").setValue(incorrectAnswersCount);

        databaseReference.child("users").child(user.getUid()).child("quiz_results")
                .child(key).child("correctAnswersCount").setValue(correctAnswersCount);

        databaseReference.child("users").child(user.getUid()).child("quiz_results")
                .child(key).child("questionsAnswered").setValue(questionsAnswered);

        databaseReference.child("users").child(user.getUid()).child("quiz_results")
                .child(key).child("result").setValue(result);

        databaseReference.child("users").child(user.getUid()).child("quiz_results")
                .child(key).child("date").setValue(date);

    }

    public static DatabaseReference getUser(FirebaseUser user) {
        return databaseReference.child("users").child(user.getUid());
    }


}
