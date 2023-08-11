package com.example.house.fakecall_2.live_chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.house.fakecall_2.R;

import java.util.List;

public class chat_adapter extends RecyclerView.Adapter<chat_adapter.chatViewHolder> {

    private List<chatMessageModel> chatMessages;

    public chat_adapter(List<chatMessageModel> chatMessages) {
        this.chatMessages = chatMessages;
    }

    @NonNull
    @Override
    public chat_adapter.chatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_message, parent, false);
        return new chatViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull chat_adapter.chatViewHolder holder, int position) {

        chatMessageModel chatMessage = chatMessages.get(position);
        holder.bind(chatMessage);

    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }


    static class chatViewHolder extends RecyclerView.ViewHolder {
        TextView messageTextView,label;
        public chatViewHolder(@NonNull View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(R.id.messageTextView);
            label = itemView.findViewById(R.id.label);
        }
            void bind(chatMessageModel chatMessage) {
                messageTextView.setText(chatMessage.getMessage());

                if (chatMessage.isUserMessage()) {

                    messageTextView.setBackgroundResource(R.drawable.btns_exit);
                    label.setText("You: ");

                } else {

                    messageTextView.setBackgroundResource(R.drawable.oranger_round_bg);
                    label.setText("Bot: ");

                }
        }
    }
}
