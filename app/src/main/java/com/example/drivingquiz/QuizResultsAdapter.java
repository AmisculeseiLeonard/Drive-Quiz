package com.example.drivingquiz;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivingquiz.model.QuizResult;
import com.example.drivingquiz.model.User;
import com.example.drivingquiz.utils.FirebaseUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class QuizResultsAdapter extends  RecyclerView.Adapter<QuizResultsAdapter.QuizResultViewHolder>{
    ArrayList<QuizResult> quizResults;

    public QuizResultsAdapter() {
        quizResults = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseUtils.getUser(FirebaseAuth.getInstance().getCurrentUser());
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //User user = snapshot.getValue(User.class);
                snapshot.getChildren().forEach(r -> Log.d("quiz_results", r.getValue().toString()));

                snapshot.getChildren().forEach(result -> {
                    QuizResult quizResult = result.getValue(QuizResult.class);
                    Log.d("quiz_results", quizResult.toString());
                    quizResults.add(quizResult);
                    notifyItemInserted(quizResults.size()-1);
                });
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseReference.addChildEventListener(childEventListener);
    }

    @NonNull
    @Override
    public QuizResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.rv_quiz_result_row,parent,false);
        return new QuizResultViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizResultViewHolder holder, int position) {
        QuizResult quizResult = quizResults.get(position);
        holder.bind(quizResult);
    }

    @Override
    public int getItemCount() {
        return quizResults.size();
    }

    public class QuizResultViewHolder extends RecyclerView.ViewHolder{
        TextView txtCorrectAnswers;
        TextView txtResult;
        TextView txtDate;

        public QuizResultViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCorrectAnswers = itemView.findViewById(R.id.correctAnswers);
            txtResult = itemView.findViewById(R.id.result);
            txtDate = itemView.findViewById(R.id.date);
        }

        public void bind(QuizResult quizResult) {
            txtCorrectAnswers.setText(quizResult.getCorrectAnswersCount() + " / " + quizResult.getTotalQuestions());
            txtResult.setText(quizResult.getResult());

            txtDate.setText(quizResult.getDate());
        }
    }
}
