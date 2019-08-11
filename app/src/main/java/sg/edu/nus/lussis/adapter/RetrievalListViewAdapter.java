package sg.edu.nus.lussis.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import sg.edu.nus.lussis.R;
import sg.edu.nus.lussis.model.RetrievalItemDTO;

public class RetrievalListViewAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<RetrievalItemDTO> retrievalItemList;

    public RetrievalListViewAdapter(Context mContext, List<RetrievalItemDTO> retrievalItemList) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        this.retrievalItemList = retrievalItemList;
    }

    public class ViewHolder{
        TextView tvBin, tvName, tvNeeded;
        EditText etRetrieved;
        public MutableWatcher mWatcher;
    }

    @Override
    public int getCount() {
        return retrievalItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return retrievalItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView==null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.retrieval_list_content, null);

            //locate the views in xml file
            holder.tvBin = convertView.findViewById(R.id.bin);
            holder.tvName = convertView.findViewById(R.id.name);
            holder.tvNeeded = convertView.findViewById(R.id.qty_needed);
            holder.mWatcher = new MutableWatcher();
            holder.etRetrieved = convertView.findViewById(R.id.qty_retrieved);

            convertView.setTag(holder);
        }
        else {
            holder = (RetrievalListViewAdapter.ViewHolder)convertView.getTag();
        }

        //set the results into textViews
        holder.tvBin.setText(retrievalItemList.get(position).getLocation());
        holder.tvName.setText(retrievalItemList.get(position).getDescription());
        holder.tvNeeded.setText(retrievalItemList.get(position).getNeededQuantity());
        holder.mWatcher.setActive(false);
        String s = retrievalItemList.get(position).getRetrievedQty();
        System.err.println(s);
        holder.etRetrieved.setText(retrievalItemList.get(position).getRetrievedQty());
        holder.mWatcher.setPosition(position);
        holder.mWatcher.setActive(true);

//        holder.etRetrieved.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                //retrievalItemList.get(position).setRetrievedQty(holder.etRetrieved.getText().toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
////                if(s.toString() != "") {
////                    if (Integer.parseInt(s.toString()) <= Integer.parseInt(retrievalItemList.get(position).getNeededQuantity()))
//                        retrievalItemList.get(position).setRetrievedQty(s.toString());
////                    else
////                        retrievalItemList.get(position).setRetrievedQty("0");
////                }
//            }
//        });

        return convertView;
    }

    class MutableWatcher implements TextWatcher {

        private int mPosition;
        private boolean mActive;

        void setPosition(int position) {
            mPosition = position;
        }

        void setActive(boolean active) {
            mActive = active;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { }

        @Override
        public void afterTextChanged(Editable s) {
            retrievalItemList.get(mPosition).setRetrievedQty(s.toString());
        }
    }
}
