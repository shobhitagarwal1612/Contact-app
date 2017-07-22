package android.com.kisannetwork.adapters;

import android.com.kisannetwork.R;
import android.com.kisannetwork.model.MessageHistory;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by shobhit on 21/7/17.
 */

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class MessagesAdapter extends
        RecyclerView.Adapter<MessagesAdapter.ViewHolder> {

    private ArrayList<MessageHistory> arrayList;
    private Context context;

    // Pass in the contact array into the constructor
    public MessagesAdapter(Context context, ArrayList<MessageHistory> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public void setList(ArrayList<MessageHistory> list) {
        arrayList = list;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return context;
    }

    @Override
    public MessagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_message_history, parent, false);

        // Return a new holder instance
        return new ViewHolder(contactView);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(MessagesAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        MessageHistory messageHistory = arrayList.get(position);

        // Set item views based on your views and data model
        viewHolder.name.setText(messageHistory.getName());
        viewHolder.otp.setText(messageHistory.getOtp());
        viewHolder.timeStamp.setText(messageHistory.getTimeStamp());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        TextView name;
        TextView otp;
        TextView timeStamp;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            otp = (TextView) itemView.findViewById(R.id.otp);
            timeStamp = (TextView) itemView.findViewById(R.id.time);

        }
    }
}