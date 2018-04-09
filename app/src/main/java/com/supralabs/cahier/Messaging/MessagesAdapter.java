package com.supralabs.cahier.Messaging;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.supralabs.cahier.R;

import java.util.List;

/**
 * Created by SONY on 21/09/2017.
 */

//#####################################################
//################## MENSAJES_ADAPTER ##################
//#####################################################
public class MessagesAdapter extends
        RecyclerView.Adapter<MessagesAdapter.MessagesViewHolder> {
    private Context context;
    private List<Message> messages;

    static class MessagesViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        LinearLayout linearLayoutBubble;
        TextView textViewMessage;
        TextView textViewHour;

        MessagesViewHolder(View view){
            super(view);
            cardView = (CardView) itemView.findViewById(R.id.bubbleMessages);
            linearLayoutBubble = (LinearLayout)itemView.findViewById(R.id.bubbleLinearLayout);
            textViewMessage = (TextView) itemView.findViewById(R.id.bubbleText);
            textViewHour = (TextView) itemView.findViewById(R.id.bubbleHour);
        }
    }

    public MessagesAdapter(List<Message> messages, Context context) {
         this.messages = messages;
        this.context = context;
    }



    @Override
    public MessagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bubble_view_messages, parent, false);
        return new MessagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessagesViewHolder holder, int position) {

        RelativeLayout.LayoutParams bubbleRelativeLayout = (RelativeLayout.LayoutParams) holder.cardView.getLayoutParams();
        FrameLayout.LayoutParams bubbleFrameLayout = (FrameLayout.LayoutParams) holder.linearLayoutBubble.getLayoutParams();

        LinearLayout.LayoutParams bubbleLinearLayoutMessage = (LinearLayout.LayoutParams) holder.textViewMessage.getLayoutParams();
        LinearLayout.LayoutParams bubbleLinearLayoutHour = (LinearLayout.LayoutParams) holder.textViewHour.getLayoutParams();


        if(messages.get(position).getTipoDeMensaje()== 1){//El que emite
            holder.linearLayoutBubble.setBackgroundResource(R.drawable.ou);
            bubbleRelativeLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
            bubbleRelativeLayout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            bubbleLinearLayoutMessage.gravity = Gravity.RIGHT;
            bubbleLinearLayoutHour.gravity = Gravity.RIGHT;
            bubbleFrameLayout.gravity = Gravity.RIGHT;
            holder.textViewMessage.setGravity(Gravity.RIGHT);
        } else if(messages.get(position).getTipoDeMensaje()==2) {//El que recibe
            holder.linearLayoutBubble.setBackgroundResource(R.drawable.in);
            bubbleRelativeLayout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
            bubbleRelativeLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            bubbleLinearLayoutMessage.gravity = Gravity.LEFT;
            bubbleLinearLayoutHour.gravity = Gravity.LEFT;
            bubbleFrameLayout.gravity = Gravity.LEFT;
            holder.textViewMessage.setGravity(Gravity.LEFT);
        }

        holder.cardView.setLayoutParams(bubbleRelativeLayout);
        holder.linearLayoutBubble.setLayoutParams(bubbleFrameLayout);
        holder.textViewMessage.setLayoutParams(bubbleLinearLayoutMessage);
        holder.textViewHour.setLayoutParams(bubbleLinearLayoutHour);

        holder.textViewMessage.setText(messages.get(position).getMensaje());
        holder.textViewHour.setText(messages.get(position).getHoraDelMensaje());

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){ holder.cardView.getBackground().setAlpha(0);}
        else holder.cardView.setBackgroundColor(ContextCompat.getColor(context,android.R.color.transparent));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

}