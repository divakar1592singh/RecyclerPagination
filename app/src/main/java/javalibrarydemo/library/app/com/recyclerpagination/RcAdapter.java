package javalibrarydemo.library.app.com.recyclerpagination;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by senzec on 23/2/18.
 */

public class RcAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Context mContext;
    LayoutInflater inflater;
    List<String> list;
    int pageCount = 0;
    RcAdapter rcAdapter;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;


    RcAdapter(Context mContext, List<String> list, int pageCount){

        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        this.list = list;
        this.pageCount = pageCount;
    }

    // The ViewHolders for Header, Item and Footer
    class HeaderViewHolder extends RecyclerView.ViewHolder {
        public View View;
        private final TextView txtHeader;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            View = itemView;

            // add your ui components here like this below
            txtHeader = (TextView) View.findViewById(R.id.idTv1);
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        private final TextView txtItem;
        public ItemViewHolder(View itemView) {
            super(itemView);
            txtItem = (TextView) itemView.findViewById(R.id.idTv3);
        }
    }


    public class FooterViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtfooter;
        public FooterViewHolder(View v) {
            super(v);

            // add your ui components here like this below
            txtfooter = (TextView) v.findViewById(R.id.idTv2);
        }

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
        if (viewType == TYPE_ITEM) {
            View itemView = inflater.inflate(R.layout.image_layout, null);
            return new ItemViewHolder(itemView);

        } else if (viewType == TYPE_HEADER) {
            View itemView = inflater.inflate(R.layout.header_layout, null);
            return new HeaderViewHolder(itemView);

        } else if (viewType == TYPE_FOOTER) {
            View itemView = inflater.inflate(R.layout.footer_layout, null);
            return new FooterViewHolder(itemView);

        }
//        return null;
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {



        if (holder instanceof HeaderViewHolder) {

            //set the Value from List to corresponding UI component as shown below.
            ((HeaderViewHolder) holder).txtHeader.setText("This is Header");

            //similarly bind other UI components or perform operations

        }else if (holder instanceof ItemViewHolder) {

            ((ItemViewHolder) holder).txtItem.setText("Count : "+position);
            // Your code here

        }else if (holder instanceof FooterViewHolder) {

            //your code here
            ((FooterViewHolder) holder).txtfooter.setText("This is Footer");
        }

    }


    @Override
    public int getItemCount() {

        return pageCount < list.size() ? pageCount+2 : list.size()+1 ;

    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;

        } else if (isPositionFooter(position)) {
            return TYPE_FOOTER;
        }

        return TYPE_ITEM;
    }


    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    private boolean isPositionFooter(int position) {
//        return position > list.size();
        return position > pageCount;

    }



}
