package sg.edu.nus.lussis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import sg.edu.nus.lussis.Model.Requisitions;

public class MyRequisitionsListViewAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<Requisitions> requisitionList;
    private ArrayList<Requisitions> arrayList;

    public MyRequisitionsListViewAdapter(Context context, List<Requisitions> requisitionList) {
        mContext = context;
        this.requisitionList = requisitionList;
        inflater = LayoutInflater.from(mContext);
        arrayList = new ArrayList<>();
        arrayList.addAll(requisitionList);
    }

    public class ViewHolder{
        TextView mIdTv, mDateTv, mStatusTv;
    }

    @Override
    public int getCount() {
        return requisitionList.size();
    }

    @Override
    public Object getItem(int position) {
        return requisitionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
 //       if (view==null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.my_requisitions_list_content, null);

            //locate the views in row.xml
            holder.mIdTv = view.findViewById(R.id.requisition_order);
            holder.mDateTv = view.findViewById(R.id.requisition_date);
            holder.mStatusTv = view.findViewById(R.id.requisition_status);

            view.setTag(holder);

//        }
//        else {
//            holder = (ViewHolder)view.getTag();
//        }

        //set the results into textViews
        holder.mIdTv.setText(requisitionList.get(position).getId());
        holder.mDateTv.setText(requisitionList.get(position).getDateTime());
        holder.mStatusTv.setText(requisitionList.get(position).getStatus());

        //listView item clicks
        view.setTag(position);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
                //code later
//                Intent intent = new Intent(mContext, RequisitionDetailsActivity.class);
//                Intent jsonObj = ((DepartmentActivity)view.getContext()).getIntent();

//                intent.putExtra("requisitionList", jsonObj.toString());
//                intent.putExtra("requisitionDetail", requisitionList.get((Integer)view.getTag()).getRequisitionDetail().toString());

//                mContext.startActivity(intent);

//                Fragment detail = new RequisitionDetailsActivity();
//                FragmentManager fragmentManager = mContext.getSupportFragmentManager();
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        new RequisitionDetailsActivity()).commit();

//            }
//        });

        return view;
    }

    //filter
    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        requisitionList.clear();
        if (charText.length()==0){
            requisitionList.addAll(arrayList);
        }
        else{
            for(Requisitions requisition : arrayList) {
                if(requisition.getId().toLowerCase(Locale.getDefault()).contains(charText) ||
                        requisition.getDateTime().toLowerCase(Locale.getDefault()).contains(charText)){
                    requisitionList.add(requisition);
                }
            }
        }
        notifyDataSetChanged();
    }
}
