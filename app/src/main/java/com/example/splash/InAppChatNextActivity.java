package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.zegocloud.zimkit.services.ZIMKit;

import im.zego.zim.enums.ZIMErrorCode;

public class InAppChatNextActivity extends AppCompatActivity {

    EditText userIdInput;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_app_chat_next);

        userIdInput = findViewById(R.id.userid_input);
        loginBtn = findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(v -> {
            String userId = userIdInput.getText().toString();
            connectUser(userId, userId, "");
        });

    }

    public void connectUser(String userId, String userName,String userAvatar) {
        // Logs in.
        ZIMKit.connectUser(userId,userName, userAvatar, errorInfo -> {
            if (errorInfo.code == ZIMErrorCode.SUCCESS) {
                // Operation after successful login. You will be redirected to other modules only after successful login. In this sample code, you will be redirected to the conversation module.
                toConversationActivity();
            } else {

            }
        });
    }

    // Integrate the conversation list into your Activity as a Fragment
    private void toConversationActivity() {
        // Redirect to the conversation list (Activity) you created.
        Intent intent = new Intent(this,ConversationActivity.class);
        startActivity(intent);
    }
}

